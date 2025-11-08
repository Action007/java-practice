package com.practice.solidandpatterns.structural.bridge.currency;

import java.math.BigDecimal;

// Defines the Implementor interface for processing a payment in a specific currency
public interface CurrencyProcessor {
  void pay(BigDecimal amount);
}
