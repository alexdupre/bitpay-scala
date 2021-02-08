package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class RefundInfo(supportRequest: String, currency: String, amounts: Map[String, BigDecimal])

object RefundInfo {
  implicit val format = Json.format[RefundInfo]
}
