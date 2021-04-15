package com.alexdupre.bitpay.models

import enumeratum.values.{IntEnum, IntEnumEntry, IntPlayJsonValueEnum}

sealed abstract class RecipientEventCode(val value: Int) extends IntEnumEntry

object RecipientEventCode extends IntEnum[RecipientEventCode] with IntPlayJsonValueEnum[RecipientEventCode] {
  val values = findValues

  case object Invited          extends RecipientEventCode(4001)
  case object Unverified       extends RecipientEventCode(4002)
  case object Verified         extends RecipientEventCode(4003)
  case object Active           extends RecipientEventCode(4004)
  case object Paused           extends RecipientEventCode(4005)
  case object Removed          extends RecipientEventCode(4006)
  case object ManuallyNotified extends RecipientEventCode(4007)
}
