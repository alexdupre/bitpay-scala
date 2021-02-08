package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait PolicyType extends EnumEntry with Uncapitalised

object PolicyType extends Enum[PolicyType] with PlayJsonEnum[PolicyType] {
  val values = findValues

  case object Sin     extends PolicyType
  case object Access  extends PolicyType
  case object Events  extends PolicyType
  case object Id      extends PolicyType
  case object Session extends PolicyType
}
