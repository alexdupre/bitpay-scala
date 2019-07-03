package com.alexdupre.bitpay

import java.time.{Instant, OffsetDateTime}

import com.alexdupre.bitpay.models._

import scala.concurrent.Future

trait BitPay {

  def getPairingCode(label: String, facade: String): Future[Token]

  def setPairingCode(label: String, pairingCode: String): Future[Token]

  def getCurrencies(): Future[Map[String, Currency]]

  def getRates(): Future[Map[String, Rate]]

  def getRate(currency: String): Future[Rate]

  def createInvoice(
      amount: BigDecimal,
      currency: String,
      ipn: IPNParams = IPNParams(),
      order: OrderInfo = OrderInfo(),
      buyerEmail: Option[String] = None,
      redirectUrl: Option[String] = None
  ): Future[Invoice]

  def getInvoice(id: String): Future[Invoice]

  def getInvoices(
      dateStart: Instant,
      dateEnd: Option[Instant] = None,
      status: Option[InvoiceState.Value] = None,
      orderId: Option[String] = None,
      itemCode: Option[String] = None,
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
