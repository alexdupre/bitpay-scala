package com.alexdupre.bitpay.models

import enumeratum.values.{IntEnum, IntEnumEntry, IntPlayJsonValueEnum}

sealed abstract class PayoutEventCode(val value: Int) extends IntEnumEntry

object PayoutEventCode extends IntEnum[PayoutEventCode] with IntPlayJsonValueEnum[PayoutEventCode] {
  val values = findValues

  case object Funded    extends PayoutEventCode(2001)
  case object Completed extends PayoutEventCode(2002)
  case object Cancelled extends PayoutEventCode(2003)
}
