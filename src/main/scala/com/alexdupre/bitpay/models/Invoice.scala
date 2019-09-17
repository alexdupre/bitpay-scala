package com.alexdupre.bitpay.models

import java.time.Instant

case class Invoice(
    id: String,
    token: String,
    price: BigDecimal,
    currency: String,
    orderId: Option[String],
    itemDesc: Option[String],
    notificationEmail: Option[String],
    notificationURL: Option[String],
    redirectUrl: Option[String],
    paymentCodes: Map[String, PaymentCodes],
    posData: Option[String],
    physical: Option[Boolean],
    url: String,
    status: InvoiceState.Value,
    lowFeeDetected: Boolean,
    targetConfirmations: Int,
    amountPaid: Long,
    paymentSubtotals: Map[String, Long],
    paymentTotals: Map[String, Long],
    paymentDisplaySubTotals: Map[String, String],
    paymentDisplayTotals: Map[String, String],
    minerFees: Map[String, MinerFee],
    invoiceTime: Instant,
    expirationTime: Instant,
    currentTime: Instant,
    exceptionStatus: Option[InvoiceExceptionState.Value],
    exchangeRates: Map[String, Map[String, BigDecimal]],
    transactions: Seq[Transaction],
    creditedOverpaymentAmounts: Option[Map[String, BigDecimal]],
    transactionCurrency: Option[String],
    supportedTransactionCurrencies: Map[String, SupportedTransactionCurrency]
)
