package com.alexdupre.bitpay.models

case class Policy(policy: PolicyType.Value, method: PolicyMethod.Value, params: Seq[String])
