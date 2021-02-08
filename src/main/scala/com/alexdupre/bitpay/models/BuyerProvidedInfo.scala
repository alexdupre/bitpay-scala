package com.alexdupre.bitpay.models

import play.api.libs.json._

case class BuyerProvidedInfo(
    name: Option[String],
    phoneNumber: Option[String],
    selectedWallet: Option[String],
    selectedTransactionCurrency: Option[String],
    emailAddress: Option[String]
)

object BuyerProvidedInfo {
  implicit val format = Json.format[BuyerProvidedInfo]
}
