package com.alexdupre.bitpay.models

import java.time.OffsetDateTime

case class LedgerEntry(
    code: LedgerCode.Value,
    `type`: String,
    amount: BigDecimal,
    timestamp: OffsetDateTime,
    description: Option[String],
    scale: Long,
    txType: String,
    invoiceId: Option[String],
    invoiceAmount: Option[BigDecimal],
    invoiceCurrency: Option[String],
    transactionCurrency: Option[String]
) {

  def scaledAmount = amount / BigDecimal(scale)
}
