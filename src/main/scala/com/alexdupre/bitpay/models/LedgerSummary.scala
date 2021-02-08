package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class LedgerSummary(currency: String, balance: BigDecimal)

object LedgerSummary {
  implicit val format = Json.format[LedgerSummary]
}
