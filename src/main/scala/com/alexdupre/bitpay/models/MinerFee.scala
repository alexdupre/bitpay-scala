package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class MinerFee(satoshisPerByte: BigDecimal, totalFee: BigInt)

object MinerFee {
  implicit val format = Json.format[MinerFee]
}
