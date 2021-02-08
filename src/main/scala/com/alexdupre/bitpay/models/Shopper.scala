package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Shopper(user: Option[String])

object Shopper {
  implicit val format = Json.format[Shopper]
}
