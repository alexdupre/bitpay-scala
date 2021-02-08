package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Rate(code: String, name: String, rate: BigDecimal)

object Rate {
  implicit val format = Json.format[Rate]
}
