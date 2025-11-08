package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public class PaypalAdapter implements PaymentProcessor {
  private PaypalSDK paypalSDK;

  public PaypalAdapter(PaypalSDK paypalSDK) {
    this.paypalSDK = paypalSDK;
  }

  @Override
  public void pay(BigDecimal amount) {
    System.out.println("[PaypalAdapter] Forwarding payment for " + amount + " with currency USD");

    paypalSDK.sendPayment(amount, "USD");
  }

}
