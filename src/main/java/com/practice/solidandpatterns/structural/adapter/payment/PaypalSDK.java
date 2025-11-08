package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public class PaypalSDK {
  public void sendPayment(BigDecimal amount, String currencyCode) {
    System.out.println("Processing PayPal payment of " + amount + " " + currencyCode);
  }
}
