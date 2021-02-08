package com.alexdupre.bitpay.models

import play.api.libs.json.Json

case class Policy(policy: PolicyType, method: PolicyMethod, params: Seq[String])

object Policy {
  implicit val format = Json.format[Policy]
}
