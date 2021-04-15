package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Instructions(amount: BigDecimal, recipientId: String, label: Option[String] = None)

object Instructions {
  implicit val format = Json.format[Instructions]
}
