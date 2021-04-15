package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class PayoutEvent(code: PayoutEventCode)

object PayoutEvent {
  implicit val format = Json.format[PayoutEvent]
}
