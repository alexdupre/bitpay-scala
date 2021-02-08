package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class SupportedTransactionCurrency(enabled: Boolean, reason: Option[String])

object SupportedTransactionCurrency {
  implicit val format = Json.format[SupportedTransactionCurrency]
}
