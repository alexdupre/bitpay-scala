package com.alexdupre.bitpay.models

import ai.x.play.json.Jsonx

import java.time.Instant

case class Invoice(
    url: String,
    posData: Option[String],
    status: InvoiceState,
    price: BigDecimal,
    currency: String,
    itemDesc: Option[String],
    orderId: Option[String],
    invoiceTime: Instant,
    expirationTime: Instant,
    currentTime: Instant,
    // guid: UUID,
    id: String,
    lowFeeDetected: Boolean,
    amountPaid: BigDecimal,
    displayAmountPaid: Option[String],
    exceptionStatus: Either[Boolean, InvoiceExceptionState],
    targetConfirmations: Option[Int],
    transactions: Option[Seq[Transaction]],
    transactionSpeed: Option[TransactionSpeed],
    buyer: BuyerInfo,
    redirectURL: Option[String],
    refundAddresses: Option[Seq[Map[String, RefundAddressInfo]]],
    refundAddressRequestPending: Boolean,
    buyerProvidedEmail: Option[String],
    buyerProvidedInfo: Option[BuyerProvidedInfo],
    paymentSubtotals: Map[String, BigInt],
    paymentTotals: Map[String, BigInt],
    paymentDisplayTotals: Option[Map[String, String]],
    paymentDisplaySubTotals: Option[Map[String, String]],
    exchangeRates: Map[String, Map[String, BigDecimal]],
    minerFees: Map[String, MinerFee],
    nonPayProPaymentReceived: Option[Boolean],
    shopper: Option[Shopper],
    billId: Option[String],
    refundInfo: Option[Seq[RefundInfo]], // never seen
    jsonPayProRequired: Option[Boolean],
    transactionCurrency: Option[String],
    underpaidAmount: Option[BigDecimal],
    overpaidAmount: Option[BigDecimal],
    supportedTransactionCurrencies: Map[String, SupportedTransactionCurrency],
    paymentCodes: Map[String, Map[String, Option[String]]], // BIP72b & BIP73 for BTC/BCH, EIP681 for ETH
    token: String
)

object Invoice {
  import ai.x.play.json.implicits.optionWithNull
  implicit val buyerFormat = BuyerInfo.standardFormat
  implicit val format      = Jsonx.formatCaseClass[Invoice]
}
