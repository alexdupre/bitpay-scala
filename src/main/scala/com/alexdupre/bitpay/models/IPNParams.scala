package com.alexdupre.bitpay.models

case class IPNParams(posData: Option[String] = None,
                     notificationURL: Option[String] = None,
                     transactionSpeed: Option[TransactionSpeed.Value] = None,
                     fullNotifications: Option[Boolean] = None,
                     extendedNotifications: Option[Boolean] = None,
                     notificationEmail: Option[String] = None)
