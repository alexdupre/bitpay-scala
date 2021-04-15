package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class PayoutAmount(unpaid: Option[BigDecimal], paid: BigDecimal)

object PayoutAmount {
  implicit val format = Json.format[PayoutAmount]
}
