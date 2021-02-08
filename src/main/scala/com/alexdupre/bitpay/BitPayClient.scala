package com.alexdupre.bitpay

import java.time.{Instant, OffsetDateTime}
import java.util.UUID
import com.alexdupre.bitpay.models._
import gigahorse._
import gigahorse.support.okhttp.Gigahorse._
import org.slf4j.LoggerFactory
import play.api.libs.json.{JsArray, JsObject, JsResultException, JsValue, Json, Reads}

import scala.collection.concurrent.TrieMap
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.control.NonFatal

class BitPayClient(identity: Identity, testNet: Boolean, http: HttpClient)(implicit ec: ExecutionContext) extends BitPay {

  val logger = LoggerFactory.getLogger(classOf[BitPayClient])

  val apiUrl = if (testNet) "https://test.bitpay.com/" else "https://bitpay.com/"

  logger.debug(s"Identity: ${identity.publicIdentity}")
  logger.debug(s"Sin: ${identity.sin}")

  lazy val tokens: TrieMap[String, String] = {
    TrieMap.empty ++= Await.result(getAccessTokens().recover({ case e if e.isInstanceOf[BitPayReturnedException] => Map.empty }), 5 seconds)
  }

  object XSignatureCalculator extends SignatureCalculator {
    override def sign(url: String, contentType: Option[String], content: Array[Byte]) =
      ("x-signature", identity.signRequest(url, content))
  }

  private def addHeaders(req: Request, authenticated: Boolean): Request = {
    if (authenticated)
      req.addHeaders("x-accept-version" -> "2.0.0", "x-identity" -> identity.publicIdentity).withSignatureOpt(Some(XSignatureCalculator))
    else
      req.addHeader("x-accept-version" -> "2.0.0")
  }

  private def addGuidAndToken(params: JsObject, token: Option[String]): JsObject = {
    params ++ Json.obj("guid" -> UUID.randomUUID(), "token" -> token)
  }

  private def getAccessToken(facades: String*): Option[String] =
    getOptionalAccessToken(facades: _*).orElse(throw BitPayLogicException(s"You do not have access to facades: $facades"))

  private def getOptionalAccessToken(facades: String*): Option[String] =
    facades.find(tokens.isDefinedAt(_)).map(tokens(_))

  private def get(
      path: String,
      params: Map[String, Any] = Map.empty,
      token: Option[String] = None,
      authenticated: Boolean = true
  ): Request = {
    val fullParams = params + ("token" -> token)
    val req = fullParams.foldLeft(url(apiUrl + path)) { (req, param) =>
      param match {
        case (key, Some(value)) => req.addQueryString(key -> value.toString)
        case (_, None)          => req
        case (key, value)       => req.addQueryString(key -> value.toString)
      }
    }
    addHeaders(req, authenticated).get
  }

  private def post(path: String, params: JsObject = Json.obj(), token: Option[String] = None, authenticated: Boolean = true) = {
    val payload = addGuidAndToken(params, token)
    val req     = url(apiUrl + path)
    implicit val jsonCodec = new HttpWrite[JsObject] {
      override def toByteArray(a: JsObject): Array[Byte] = a.toString().getBytes(utf8)
      override def contentType: Option[String]           = Some(MimeTypes.JSON)
    }
    addHeaders(req, authenticated).post(payload)
  }

  private def delete(
      path: String,
      params: Map[String, Option[Any]] = Map.empty,
      token: Option[String] = None,
      authenticated: Boolean = true
  ): Request = {
    val fullParams = params + ("token" -> token)
    val req = fullParams.foldLeft(url(apiUrl + path)) { (req, param) =>
      param match {
        case (key, Some(value)) => req.addQueryString(key -> value.toString)
        case _                  => req
      }
    }
    addHeaders(req, authenticated).delete
  }

  def execute[T](req: Request)(implicit reads: Reads[T]): Future[T] = {
    logger.debug(s"Request: ${req.method} ${req.url}")
    if (req.method == "POST" && logger.isTraceEnabled)
      logger.trace(s"Payload: ${new String(req.body.asInstanceOf[InMemoryBody].bytes, utf8)}")
    http.processFull(req).map { response =>
      logger.debug(s"Response Status: ${response.status}")
      val js =
        try {
          Json.parse(response.bodyAsString)
        } catch {
          case NonFatal(_) => throw BitPayProtocolException()
        }
      logger.trace(s"Response:\n${Json.prettyPrint(js)}")
      if (response.status / 100 == 2) {
        try {
          js match {
            case o: JsObject => (o \ "data").as[T]
            case a: JsArray  => a.as[T]
            case _           => throw BitPayProtocolException()
          }
        } catch {
          case e: JsResultException => throw BitPayProtocolException("BitPay JSON error: " + e.getMessage).initCause(e)
        }
      } else {
        throw (js \ "error").asOpt[String].map(BitPayReturnedException).getOrElse(BitPayProtocolException())
      }
    }
  }

  // Tokens

  private def getAccessTokens(): Future[Map[String, String]] = {
    execute[Seq[Map[String, String]]](get("tokens")).map(list => list.map(map => map.toSeq.head).toMap)
  }

  override def getPairingCode(label: String, facade: String): Future[Token] = {
    val params = Json.obj("label" -> label.replaceAll("[^a-zA-Z0-9_ ]", "_").take(60), "id" -> identity.sin, "facade" -> facade)
    execute[Seq[Token]](post("tokens", params, authenticated = false)) map { ts =>
      tokens ++= ts.map(t => t.facade -> t.token)
      ts.sortBy(_.dateCreated).last
    }
  }

  override def setPairingCode(label: String, pairingCode: String): Future[Token] = {
    val params = Json.obj("label" -> label.replaceAll("[^a-zA-Z0-9_ ]", "_").take(60), "id" -> identity.sin, "pairingCode" -> pairingCode)
    execute[Seq[Token]](post("tokens", params, authenticated = false)) map { ts =>
      tokens ++= ts.map(t => t.facade -> t.token)
      ts.sortBy(_.dateCreated).last
    }
  }

  // Currencies

  override def getCurrencies(): Future[Map[String, Currency]] = {
    val rates = execute[Seq[Currency]](get("currencies", authenticated = false))
    rates.map(_.foldLeft(Map[String, Currency]()) { case (m, c) =>
      m + (c.code -> c)
    })
  }

  // Rates

  override def getRates(cryptoCurrency: String): Future[Map[String, Rate]] = {
    val rates = execute[Seq[Rate]](get(s"rates/$cryptoCurrency", authenticated = false))
    rates.map(_.foldLeft(Map[String, Rate]()) { case (m, r) =>
      m + (r.code -> r)
    })
  }

  override def getRate(cryptoCurrency: String, fiatCurrency: String): Future[Rate] = {
    execute[Rate](get(s"rates/$cryptoCurrency/$fiatCurrency", authenticated = false))
  }

  // Invoices

  override def createInvoice(
      amount: BigDecimal,
      currency: String,
      ipn: IPNParams = IPNParams(),
      order: OrderInfo = OrderInfo(),
      buyer: BuyerInfo = BuyerInfo(),
      redirectURL: Option[String] = None
  ): Future[Invoice] = {
    implicit val buyerFormat = BuyerInfo.standardFormat
    val params = Json.obj("price" -> amount, "currency" -> currency, "buyer" -> buyer, "redirectURL" -> redirectURL) ++
      Json.toJsObject(order) ++ Json.toJsObject(ipn)
    execute[Invoice](post("invoices", params, getAccessToken("merchant", "pos")))
  }

  override def getInvoice(id: String): Future[Invoice] = {
    val token = getOptionalAccessToken("merchant")
    execute[Invoice](get(s"invoices/$id", Map.empty, token, token.isDefined))
  }

  private def getInvoiceToken(id: String): Future[String] = {
    val token = getAccessToken("merchant")
    execute[Invoice](get(s"invoices/$id", Map.empty, token, token.isDefined)).map(_.token)
  }

  override def getInvoices(
      dateStart: Instant,
      dateEnd: Instant,
      status: Option[InvoiceState] = None,
      orderId: Option[String] = None,
      limit: Option[Int] = None,
      offset: Option[Int] = None
  ): Future[Seq[Invoice]] = {
    val params = Map(
      "dateStart" -> dateStart,
      "dateEnd"   -> dateEnd,
      "status"    -> status.map(_.entryName),
      "orderId"   -> orderId,
      "limit"     -> limit,
      "offset"    -> offset
    )
    execute[Seq[Invoice]](get("invoices", params, getAccessToken("merchant")))
  }

  override def requestRefund(id: String, buyerEmail: String): Future[Unit] = {
    getInvoice(id).flatMap { invoice =>
      val params = Json.obj("refundEmail" -> buyerEmail, "amount" -> invoice.amountPaid, "currency" -> invoice.currency)
      execute[JsValue](post(s"invoices/$id/refunds", params, Some(invoice.token))).map(_ => ())
    }
  }

  override def getRefunds(id: String): Future[Seq[Refund]] = {
    getInvoiceToken(id).flatMap { token =>
      execute[Seq[Refund]](get(s"invoices/$id/refunds", token = Some(token)))
    }
  }

  override def resendNotification(id: String): Future[Unit] = {
    getInvoiceToken(id).flatMap { token =>
      execute[String](post(s"invoices/$id/notifications", token = Some(token))).map(_ => ())
    }
  }

  // Ledger

  override def getLedgers(): Future[Map[String, LedgerSummary]] = {
    val ledgers = execute[Seq[LedgerSummary]](get("ledgers", token = getAccessToken("merchant")))
    ledgers.map(_.foldLeft(Map[String, LedgerSummary]()) { case (m, s) =>
      m + (s.currency -> s)
    })
  }

  override def getLedger(
      currency: String,
      startDate: OffsetDateTime = OffsetDateTime.now().minusMonths(1),
      endDate: OffsetDateTime = OffsetDateTime.now()
  ): Future[Seq[LedgerEntry]] = {
    val params = Map("startDate" -> startDate, "endDate" -> endDate)
    execute[Seq[LedgerEntry]](get(s"ledgers/$currency", params, getAccessToken("merchant")))
  }

}

object BitPayClient {

  def apply(identity: Identity, testNet: Boolean = false)(implicit ec: ExecutionContext) =
    new BitPayClient(identity, testNet, defaultHttpExecutor)

  lazy val defaultHttpExecutor = {
    val cfg = config
      .withUserAgentOpt(Some(s"AlexDupre-BitPay/${BuildInfo.version}"))
      .withConnectTimeout(5 seconds)
      .withReadTimeout(30 seconds)
      .withRequestTimeout(60 seconds)
    http(cfg)
  }

}
