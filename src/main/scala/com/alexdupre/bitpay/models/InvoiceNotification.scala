package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.Instant

case class InvoiceNotification(
    id: String,
    price: BigDecimal,
    currency: String,
    posData: Option[String],
    url: String,
    status: InvoiceState,
    amountPaid: BigDecimal,
    paymentSubtotals: Map[String, BigDecimal],
    paymentTotals: Map[String, BigDecimal],
    invoiceTime: Instant,
    expirationTime: Instant,
    currentTime: Instant,
    exceptionStatus: Option[InvoiceExceptionState],
    exchangeRates: Map[String, Map[String, BigDecimal]],
    transactionCurrency: Option[String]
)

object InvoiceNotification {
  implicit val format = Json.format[InvoiceNotification]
}
