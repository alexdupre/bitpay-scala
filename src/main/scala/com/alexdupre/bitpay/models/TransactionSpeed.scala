package com.alexdupre.bitpay.models

import enumeratum.EnumEntry.Uncapitalised
import enumeratum._

sealed trait TransactionSpeed extends EnumEntry with Uncapitalised

object TransactionSpeed extends Enum[TransactionSpeed] with PlayJsonEnum[TransactionSpeed] {
  val values = findValues

  case object High   extends TransactionSpeed
  case object Medium extends TransactionSpeed
  case object Low    extends TransactionSpeed
}
