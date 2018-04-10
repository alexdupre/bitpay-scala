package com.alexdupre.bitpay.models

import java.time.OffsetDateTime

case class Refund(id: String, requestDate: OffsetDateTime, status: RefundState.Value, token: String)
