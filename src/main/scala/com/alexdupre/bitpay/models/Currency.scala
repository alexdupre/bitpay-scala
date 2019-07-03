package com.alexdupre.bitpay.models

case class Currency(
    code: String,
    symbol: String,
    precision: Int,
    exchangePctFee: Int,
    payoutEnabled: Boolean,
    name: String,
    plural: String,
    alts: String,
    minimum: BigDecimal,
    sanctioned: Boolean,
    payoutFields: Seq[String],
    settlementMinimum: Option[BigDecimal] = None
)
