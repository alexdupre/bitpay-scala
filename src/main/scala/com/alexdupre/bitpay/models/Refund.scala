package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.OffsetDateTime

case class Refund(id: String, requestDate: OffsetDateTime, status: RefundState, token: String)

object Refund {
  implicit val format = Json.format[Refund]
}
