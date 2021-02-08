package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class OrderInfo(orderId: Option[String] = None, itemDesc: Option[String] = None, physical: Option[Boolean] = None)

object OrderInfo {
  implicit val format = Json.format[OrderInfo]
}
