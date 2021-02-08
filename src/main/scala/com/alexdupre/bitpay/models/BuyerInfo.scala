package com.alexdupre.bitpay.models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class BuyerInfo(
    name: Option[String] = None,
    address1: Option[String] = None,
    address2: Option[String] = None,
    locality: Option[String] = None,
    region: Option[String] = None,
    postalCode: Option[String] = None,
    country: Option[String] = None,
    email: Option[String] = None,
    phone: Option[String] = None,
    notifyBuyer: Option[Boolean] = None
)

object BuyerInfo {
  implicit val format = ((__ \ "name").formatNullable[String] ~
    (__ \ "address1").formatNullable[String] ~
    (__ \ "address2").formatNullable[String] ~
    (__ \ "locality").formatNullable[String] ~
    (__ \ "region").formatNullable[String] ~
    (__ \ "postalCode").formatNullable[String] ~
    (__ \ "country").formatNullable[String] ~
    (__ \ "email").formatNullable[String] ~
    (__ \ "phone").formatNullable[String] ~
    (__ \ "notify").formatNullable[Boolean])(BuyerInfo.apply, unlift(BuyerInfo.unapply))
}
