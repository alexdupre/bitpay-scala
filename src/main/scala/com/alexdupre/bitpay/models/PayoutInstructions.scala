package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class PayoutInstructions(
    id: String,
    amount: BigDecimal,
    btc: PayoutAmount,
    address: Option[String],
    email: Option[String],
    recipientId: String,
    shopperId: String,
    label: Option[String],
    transactions: Seq[PayoutTransaction],
    status: PayoutInstructionsState,
    currency: String
)

object PayoutInstructions {
  implicit val format = Json.format[PayoutInstructions]
}
