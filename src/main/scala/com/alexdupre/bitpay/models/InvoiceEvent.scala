package com.alexdupre.bitpay.models

import enumeratum.values.{IntEnum, IntEnumEntry, IntPlayJsonValueEnum}

sealed abstract class InvoiceEvent(val value: Int, val name: String) extends IntEnumEntry

object InvoiceEvent extends IntEnum[InvoiceEvent] with IntPlayJsonValueEnum[InvoiceEvent] {
  val values = findValues

  case object Created             extends InvoiceEvent(1001, "invoice_created")
  case object ReceivedPayment     extends InvoiceEvent(1002, "invoice_receivedPayment")
  case object PaidInFull          extends InvoiceEvent(1003, "invoice_paidInFull")
  case object Expired             extends InvoiceEvent(1004, "invoice_expired")
  case object Confirmed           extends InvoiceEvent(1005, "invoice_confirmed")
  case object Completed           extends InvoiceEvent(1006, "invoice_completed")
  case object Refunded            extends InvoiceEvent(1007, "invoice_refunded")
  case object MarkedInvalid       extends InvoiceEvent(1008, "invoice_markedInvalid")
  case object PaidAfterExpiration extends InvoiceEvent(1009, "invoice_paidAfterExpiration")
  case object ExpiredPartial      extends InvoiceEvent(1010, "invoice_expiredPartial")
  case object BlockedByTierLimit  extends InvoiceEvent(1011, "invoice_blockedByTierLimit")
  case object ManuallyNotified    extends InvoiceEvent(1012, "invoice_manuallyNotified")
  case object FailedToConfirm     extends InvoiceEvent(1013, "invoice_failedToConfirm")
  case object LatePayment         extends InvoiceEvent(1014, "invoice_latePayment")
  case object AdjustmentComplete  extends InvoiceEvent(1015, "invoice_adjustmentComplete")
  case object RefundComplete      extends InvoiceEvent(1016, "invoice_refundComplete")
}
