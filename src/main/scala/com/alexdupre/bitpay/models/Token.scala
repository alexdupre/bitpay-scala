package com.alexdupre.bitpay.models

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
