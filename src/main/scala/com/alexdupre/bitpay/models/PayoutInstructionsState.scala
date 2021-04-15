package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait PayoutInstructionsState extends EnumEntry with Uncapitalised

object PayoutInstructionsState extends Enum[PayoutInstructionsState] with PlayJsonEnum[PayoutInstructionsState] {
  val values = findValues

  case object Unpaid   extends PayoutInstructionsState
  case object Paid     extends PayoutInstructionsState
  case object Complete extends PayoutInstructionsState
}
