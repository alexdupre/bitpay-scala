package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait PolicyMethod extends EnumEntry with Uncapitalised

object PolicyMethod extends Enum[PolicyMethod] with PlayJsonEnum[PolicyMethod] {
  val values = findValues

  case object Require     extends PolicyMethod
  case object Allow       extends PolicyMethod
  case object Invalidated extends PolicyMethod
  case object Inactive    extends PolicyMethod
  case object Unclaimed   extends PolicyMethod
}
