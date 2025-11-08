package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public interface PaymentProcessor {
  void pay(BigDecimal amount);
}
