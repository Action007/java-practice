package com.practice.solidandpatterns.structural.bridge.currency;

import java.math.BigDecimal;

public class MainApp {

  public static void main(String[] args) {
    // The client decides which Abstraction to pair with which implementation

    // Combination 1: A Net Banking payment (Abstraction) in EUR (Implementation)
    Payment eurPayment = new NetBankingPayment(new EurCurrencyProcessor());
    eurPayment.pay(new BigDecimal("200.00"));

    // Combination 2: A Net Banking payment (Abstraction) in USD (Implementation)
    Payment usdPayment = new NetBankingPayment(new UsdCurrencyProcessor());
    usdPayment.pay(new BigDecimal("200.00"));
  }
}
