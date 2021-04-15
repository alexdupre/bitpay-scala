package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class PayoutNotification(event: PayoutEvent, data: Payout)

object PayoutNotification {
  implicit val format = Json.format[PayoutNotification]
}
