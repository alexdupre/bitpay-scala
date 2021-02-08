package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Currency(
    code: String,
    symbol: Option[String],
    precision: Int,
    name: String,
    plural: String,
    alts: String,
    minimum: BigDecimal,
    sanctioned: Boolean,
    decimals: Int,
    chain: Option[String]
)

object Currency {
  implicit val format = Json.format[Currency]
}
