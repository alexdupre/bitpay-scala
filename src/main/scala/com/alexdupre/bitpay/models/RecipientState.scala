package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait RecipientState extends EnumEntry with Uncapitalised

object RecipientState extends Enum[RecipientState] with PlayJsonEnum[RecipientState] {
  val values = findValues

  case object Invited    extends RecipientState
  case object Unverified extends RecipientState
  case object Verified   extends RecipientState
  case object Active     extends RecipientState
  case object Paused     extends RecipientState
  case object Removed    extends RecipientState
}
