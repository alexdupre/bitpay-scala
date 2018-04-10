package com.alexdupre.bitpay.models

object InvoiceEvent extends Enumeration {

  val Created             = Value(1001, "invoice_created")
  val ReceivedPayment     = Value(1002, "invoice_receivedPayment")
  val PaidInFull          = Value(1003, "invoice_paidInFull")
  val Expired             = Value(1004, "invoice_expired")
  val Confirmed           = Value(1005, "invoice_confirmed")
  val Completed           = Value(1006, "invoice_completed")
  val Refunded            = Value(1007, "invoice_refunded")
  val MarkedInvalid       = Value(1008, "invoice_markedInvalid")
  val PaidAfterExpiration = Value(1009, "invoice_paidAfterExpiration")
  val ExpiredPartial      = Value(1010, "invoice_expiredPartial")
  val BlockedByTierLimit  = Value(1011, "invoice_blockedByTierLimit")
  val ManuallyNotified    = Value(1012, "invoice_manuallyNotified")
  val FailedToConfirm     = Value(1013, "invoice_failedToConfirm")
  val LatePayment         = Value(1014, "invoice_latePayment")
  val AdjustmentComplete  = Value(1015, "invoice_adjustmentComplete")
  val RefundComplete      = Value(1016, "invoice_refundComplete")
}
