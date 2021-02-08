package com.alexdupre.bitpay.models

import enumeratum.values.{IntEnum, IntEnumEntry, IntPlayJsonValueEnum}

sealed abstract class LedgerCode(val value: Int) extends IntEnumEntry

object LedgerCode extends IntEnum[LedgerCode] with IntPlayJsonValueEnum[LedgerCode] {
  val values = findValues

  case object Invoice                              extends LedgerCode(1000)
  case object Fee                                  extends LedgerCode(1001)
  case object Payment                              extends LedgerCode(1002)
  case object Other                                extends LedgerCode(1003)
  case object FeeRefund                            extends LedgerCode(1004)
  case object Deposit                              extends LedgerCode(1005)
  case object Exchange                             extends LedgerCode(1006)
  case object ExchangeFee                          extends LedgerCode(1007)
  case object PlanCharge                           extends LedgerCode(1008)
  case object PlanProratedCredit                   extends LedgerCode(1009)
  case object PlanUnderutilizationCredit           extends LedgerCode(1010)
  case object PayoffNegativeBalance                extends LedgerCode(1011)
  case object Donation                             extends LedgerCode(1012)
  case object Corrective                           extends LedgerCode(1013)
  case object SettlementFee                        extends LedgerCode(1014)
  case object Custom                               extends LedgerCode(1016)
  case object AccountSettlement                    extends LedgerCode(1017)
  case object OverpaymentCreditFee                 extends LedgerCode(1018)
  case object OverpaymentCredit                    extends LedgerCode(1019)
  case object InvoiceRefund                        extends LedgerCode(1020)
  case object OverpaymentCreditRefund              extends LedgerCode(1021)
  case object CorrectiveForFailedAccountSettlement extends LedgerCode(1022)
  case object InvoiceFee                           extends LedgerCode(1023)
  case object BitcoinDeposit                       extends LedgerCode(1024)
  case object FiatDeposit                          extends LedgerCode(1025)
  case object DonationFee                          extends LedgerCode(1026)
  case object CustomFee                            extends LedgerCode(1027)
  case object AffiliateCommission                  extends LedgerCode(1030)
  case object ManualAffiliateCommission            extends LedgerCode(1031)
  case object MerchantCardDeposit                  extends LedgerCode(1032)
  case object CustomPlanCharge                     extends LedgerCode(1033)
  case object FundingPayoutRequest                 extends LedgerCode(1034)
  case object ManuallyFundingPayoutRequest         extends LedgerCode(1035)
  case object CustomFeeReimbursement               extends LedgerCode(1036)
  case object CustomPlanChargeReimbursement        extends LedgerCode(1037)
  case object TestnetBitcoinSettlement             extends LedgerCode(1038)
  case object RefundFee                            extends LedgerCode(1039)
  case object PayoutFee                            extends LedgerCode(1040)
  case object WireFee                              extends LedgerCode(1041)
}
