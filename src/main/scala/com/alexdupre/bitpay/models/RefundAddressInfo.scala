package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.OffsetDateTime

case class RefundAddressInfo(`type`: String, date: OffsetDateTime)

object RefundAddressInfo {
  implicit val format = Json.format[RefundAddressInfo]
}
