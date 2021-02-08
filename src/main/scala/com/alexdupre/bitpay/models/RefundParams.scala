package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class RefundParams(
    requesterType: String,
    requesterEmail: Option[String],
    amount: Option[BigDecimal],
    currency: Option[String],
    email: Option[String],
    purchaserNotifyEmail: Option[String],
    destinationTag: Option[String],
    refundAddress: Option[String], // I've seen bitcoinAddress, not this one
    supportRequestEid: Option[String]
)

object RefundParams {
  implicit val format = Json.format[RefundParams]
}
