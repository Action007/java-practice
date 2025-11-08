package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public class StripeSDK {
  public void makePayment(long cents) {
    System.out.println("Processing Stripe payment amount: "
        + new BigDecimal(cents).movePointLeft(2).toPlainString());
  }
}
