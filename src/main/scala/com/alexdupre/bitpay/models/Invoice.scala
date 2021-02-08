package com.alexdupre.bitpay.models

import ai.x.play.json.Jsonx
import play.api.libs.json.{JsBoolean, JsResult, JsSuccess, JsValue, Json, Reads, Writes}

import java.time.Instant

case class Invoice(
    url: String,
    posData: Option[String],
    status: InvoiceState,
    price: BigDecimal,
    currency: String,
    itemDesc: Option[String],
    orderId: Option[String],
    invoiceTime: Instant,
    expirationTime: Instant,
    currentTime: Instant,
    // guid: UUID,
    id: String,
    lowFeeDetected: Boolean,
    amountPaid: BigDecimal,
    displayAmountPaid: String,
    exceptionStatus: Either[Boolean, InvoiceExceptionState],
    targetConfirmations: Int,
    transactions: Seq[Transaction],
    transactionSpeed: TransactionSpeed,
    buyer: BuyerInfo,
    redirectUrl: Option[String],
    refundAddresses: Seq[Map[String, RefundAddressInfo]],
    refundAddressRequestPending: Boolean,
    buyerProvidedEmail: Option[String],
    buyerProvidedInfo: BuyerProvidedInfo,
    paymentSubtotals: Map[String, BigInt],
    paymentTotals: Map[String, BigInt],
    paymentDisplayTotals: Map[String, String],
    paymentDisplaySubTotals: Map[String, String],
    exchangeRates: Map[String, Map[String, BigDecimal]],
    minerFees: Map[String, MinerFee],
    nonPayProPaymentReceived: Option[Boolean],
    shopper: Shopper,
    billId: Option[String],
    refundInfo: Option[Seq[RefundInfo]], // never seen
    jsonPayProRequired: Boolean,
    transactionCurrency: Option[String],
    underpaidAmount: Option[BigDecimal],
    overpaidAmount: Option[BigDecimal],
    supportedTransactionCurrencies: Map[String, SupportedTransactionCurrency],
    paymentCodes: Map[String, Map[String, String]], // BIP72b & BIP73 for BTC/BCH, EIP681 for ETH
    token: String
)

object Invoice {
  implicit val readEither: Reads[Either[Boolean, InvoiceExceptionState]] = new Reads[Either[Boolean, InvoiceExceptionState]] {
    override def reads(json: JsValue): JsResult[Either[Boolean, InvoiceExceptionState]] = json match {
      case JsBoolean(b) => JsSuccess(Left(b))
      case _            => json.validate[InvoiceExceptionState].map(s => Right(s))
    }
  }
  implicit val writeEither: Writes[Either[Boolean, InvoiceExceptionState]] = new Writes[Either[Boolean, InvoiceExceptionState]] {
    override def writes(o: Either[Boolean, InvoiceExceptionState]): JsValue = o.fold(b => JsBoolean(b), s => Json.toJson(s))
  }
  implicit val format = Jsonx.formatCaseClass[Invoice]
}
