package com.alexdupre.bitpay.models

object PolicyMethod extends Enumeration {

  val Require     = Value("require")
  val Allow       = Value("allow")
  val Invalidated = Value("invalidated")
  val Inactive    = Value("inactive")
  val Unclaimed   = Value("unclaimed")
}
