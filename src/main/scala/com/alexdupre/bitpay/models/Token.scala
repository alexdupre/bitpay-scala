package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.{Instant, OffsetDateTime}

case class Token(
    policies: Seq[Policy],
    resource: Option[String],
    token: String,
    facade: String,
    label: Option[String],
    dateCreated: Instant,
    pairingExpiration: Option[OffsetDateTime],
    pairingCode: Option[String]
)

object Token {
  implicit val format = Json.format[Token]
}
