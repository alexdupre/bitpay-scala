package com.alexdupre.bitpay.models

import play.api.libs.json.Json

import java.time.Instant

case class Payout(
    id: String,
    account: String,
    reference: Option[String],
    supportPhone: String,
    status: PayoutState,
    amount: BigDecimal,
    percentFee: BigDecimal,
    fee: BigDecimal,
    depositTotal: BigDecimal,
    rates: Map[String, BigDecimal],
    totals: Map[String, BigDecimal],
    rate: Option[BigDecimal],
    btc: Option[BigDecimal],
    currency: String,
    requestDate: Instant,
    effectiveDate: Instant,
    notificationURL: Option[String],
    notificationEmail: Option[String],
    instructions: Seq[PayoutInstructions],
    // guid: UUID
    dateExecuted: Option[Instant],
    token: Option[String]
)

object Payout {
  implicit val format = Json.format[Payout]
}
