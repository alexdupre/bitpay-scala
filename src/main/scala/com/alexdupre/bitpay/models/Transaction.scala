package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.OffsetDateTime

case class Transaction(amount: Long, confirmations: Int, time: Option[OffsetDateTime], receivedTime: OffsetDateTime, txid: String)

object Transaction {
  implicit val format = Json.format[Transaction]
}
