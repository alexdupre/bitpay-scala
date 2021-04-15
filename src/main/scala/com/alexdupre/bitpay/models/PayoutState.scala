package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait PayoutState extends EnumEntry with Uncapitalised

object PayoutState extends Enum[PayoutState] with PlayJsonEnum[PayoutState] {
  val values = findValues

  case object New        extends PayoutState
  case object Funded     extends PayoutState
  case object Processing extends PayoutState
  case object Complete   extends PayoutState
  case object Cancelled  extends PayoutState
}
