package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public class DemoPaymentProcessor implements PaymentProcessor {

  @Override
  public void pay(BigDecimal amount) {
    System.out.println("Processing Demo payment of: " + amount);
  }
}
