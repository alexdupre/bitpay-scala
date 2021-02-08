package com.alexdupre.bitpay

import java.time.{Instant, OffsetDateTime}

import com.alexdupre.bitpay.models._

import scala.concurrent.Future

trait BitPay {

  def getPairingCode(label: String, facade: String): Future[Token]

  def setPairingCode(label: String, pairingCode: String): Future[Token]

  def getCurrencies(): Future[Map[String, Currency]]

  def getRates(cryptoCurrency: String): Future[Map[String, Rate]]

  def getRate(cryptoCurrency: String, fiatCurrency: String): Future[Rate]

  def createInvoice(
      amount: BigDecimal,
      currency: String,
      ipn: IPNParams = IPNParams(),
      order: OrderInfo = OrderInfo(),
      buyer: Option[BuyerInfo] = None,
      redirectUrl: Option[String] = None
  ): Future[Invoice]

  def getInvoice(id: String): Future[Invoice]

  def getInvoices(
      dateStart: Instant,
      dateEnd: Instant,
      status: Option[InvoiceState] = None,
      orderId: Option[String] = None,
      limit: Option[Int] = None,
      offset: Option[Int] = None
  ): Future[Seq[Invoice]]

  def requestRefund(id: String, buyerEmail: String): Future[Unit]

  def getRefunds(id: String): Future[Seq[Refund]]

  def resendNotification(id: String): Future[Unit]

  def getLedgers(): Future[Map[String, LedgerSummary]]

  def getLedger(
      currency: String,
      startDate: OffsetDateTime = OffsetDateTime.now().minusMonths(1),
      endDate: OffsetDateTime = OffsetDateTime.now()
  ): Future[Seq[LedgerEntry]]

}
