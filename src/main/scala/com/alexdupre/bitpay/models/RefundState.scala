package com.alexdupre.bitpay.models

object RefundState extends Enumeration {

  val Pending = Value("pending")
  val Success = Value("success")
  val Failure = Value("failure")
}
