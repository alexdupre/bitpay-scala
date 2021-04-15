package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class RecipientEvent(code: RecipientEventCode)

object RecipientEvent {
  implicit val format = Json.format[RecipientEvent]
}
