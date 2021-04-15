package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class RecipientInvite(email: String, label: Option[String] = None, notificationURL: Option[String] = None)

object RecipientInvite {
  implicit val format = Json.format[RecipientInvite]
}
