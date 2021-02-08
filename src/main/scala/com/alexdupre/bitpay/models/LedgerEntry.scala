package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.OffsetDateTime

case class LedgerEntry(
    `type`: String,
    amount: BigDecimal,
    code: LedgerCode,
    timestamp: OffsetDateTime,
    currency: Option[String],
    txType: String, // deprecated
    scale: Long,
    id: String,
    supportRequest: Option[String], // only for 1020
    description: Option[String],    // not for 1011
    // next fields for 1000, 1020, 1023, 1039
    invoiceId: Option[String],
    buyerFields: Option[BuyerInfo],
    invoiceAmount: Option[BigDecimal],
    invoiceCurrency: Option[String],
    transactionCurrency: Option[String]
) {
  def scaledAmount = amount / BigDecimal(scale)
}

object LedgerEntry {
  implicit val buyerFormat = BuyerInfo.alternativeFormat
  implicit val format      = Json.format[LedgerEntry]
}
