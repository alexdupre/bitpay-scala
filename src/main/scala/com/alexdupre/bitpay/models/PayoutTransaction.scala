package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.Instant

case class PayoutTransaction(txid: String, amount: BigDecimal, date: Instant)

object PayoutTransaction {
  implicit val format = Json.format[PayoutTransaction]
}
