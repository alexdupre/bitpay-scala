package com.alexdupre.bitpay

import java.time.{Instant, OffsetDateTime}
import com.alexdupre.bitpay.models._

import scala.concurrent.Future

trait BitPay {

  // Tokens

  def getPairingCode(label: String, facade: Option[String]): Future[Token]

  def setPairingCode(label: String, pairingCode: String): Future[Token]

  // Currencies

  def getCurrencies(): Future[Map[String, Currency]]

  // Rates

  def getRates(cryptoCurrency: String): Future[Map[String, Rate]]

  def getRate(cryptoCurrency: String, fiatCurrency: String): Future[Rate]

  // Invoices

  def createInvoice(
      amount: BigDecimal,
      currency: String,
      ipn: IPNParams = IPNParams(),
      order: OrderInfo = OrderInfo(),
      buyer: BuyerInfo = BuyerInfo(),
      redirectURL: Option[String] = None
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

  def requestInvoiceRefund(id: String, buyerEmail: String): Future[Unit]

  def getInvoiceRefunds(id: String): Future[Seq[Refund]]

  def resendInvoiceNotification(id: String): Future[Unit]

  // Ledgers

  def getLedgers(): Future[Map[String, LedgerSummary]]

  def getLedger(
      currency: String,
      startDate: OffsetDateTime = OffsetDateTime.now().minusMonths(1),
      endDate: OffsetDateTime = OffsetDateTime.now()
  ): Future[Seq[LedgerEntry]]

  // Recipients

  def inviteRecipients(recipients: Seq[RecipientInvite]): Future[Seq[Recipient]]

  def inviteRecipient(email: String, label: Option[String] = None, notificationURL: Option[String] = None): Future[Recipient]

  def getRecipient(id: String): Future[Recipient]

  def getRecipients(status: Option[RecipientState] = None, limit: Option[Int] = None, offset: Option[Int] = None): Future[Seq[Recipient]]

  def updateRecipient(id: String, label: Option[String] = None, notificationURL: Option[String] = None): Future[Recipient]

  def removeRecipient(id: String): Future[Unit]

  def resendRecipientNotification(id: String): Future[Unit]

  // Payouts

  def createPayout(
      totalAmount: BigDecimal,
      currency: String,
      instructions: Seq[Instructions],
      effectiveDate: Instant = Instant.now(),
      reference: Option[String] = None,
      notificationEmail: Option[String] = None,
      notificationURL: Option[String] = None
  ): Future[Payout]

  def getPayouts(status: PayoutState): Future[Seq[Payout]]

  def getPayout(id: String): Future[Payout]

  def cancelPayout(id: String): Future[Payout]

}
