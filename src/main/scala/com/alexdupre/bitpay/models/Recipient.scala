package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Recipient(
    email: String,
    notificationURL: Option[String],
    label: Option[String],
    status: RecipientState,
    id: String,
    shopperId: Option[String],
    token: Option[String]
)

object Recipient {
  implicit val format = Json.format[Recipient]
}
