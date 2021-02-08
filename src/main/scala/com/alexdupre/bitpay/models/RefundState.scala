package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait RefundState extends EnumEntry with Uncapitalised

object RefundState extends Enum[RefundState] with PlayJsonEnum[RefundState] {
  val values = findValues

  case object Pending extends RefundState
  case object Success extends RefundState
  case object Failure extends RefundState
}
