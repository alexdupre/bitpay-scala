package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class IPNParams(
    posData: Option[String] = None,
    notificationURL: Option[String] = None,
    transactionSpeed: Option[TransactionSpeed] = None,
    fullNotifications: Option[Boolean] = None,
    extendedNotifications: Option[Boolean] = None,
    notificationEmail: Option[String] = None
)

object IPNParams {
  implicit val format = Json.format[IPNParams]
}
