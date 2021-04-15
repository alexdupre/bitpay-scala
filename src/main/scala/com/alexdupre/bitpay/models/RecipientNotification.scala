package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class RecipientNotification(event: RecipientEvent, data: Recipient)

object RecipientNotification {
  implicit val format = Json.format[RecipientNotification]
}
