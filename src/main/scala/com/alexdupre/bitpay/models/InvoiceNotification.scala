package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.Instant

case class InvoiceNotification(
    id: String,
    url: String,
    posData: Option[String],
    status: InvoiceState,
    price: BigDecimal,
    currency: String,
    invoiceTime: Instant,
    expirationTime: Instant,
    currentTime: Instant,
    exceptionStatus: Option[InvoiceExceptionState],
    buyerFields: BuyerInfo,
    paymentSubtotals: Map[String, BigDecimal],
    paymentTotals: Map[String, BigDecimal],
    exchangeRates: Map[String, Map[String, BigDecimal]],
    amountPaid: BigDecimal,
    orderId: Option[String],
    transactionCurrency: Option[String]
)

object InvoiceNotification {
  implicit val buyerFormat = BuyerInfo.alternativeFormat
  implicit val format      = Json.format[InvoiceNotification]
}
