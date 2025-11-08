package com.practice.solidandpatterns.structural.bridge.currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class UsdCurrencyProcessor implements CurrencyProcessor {

  @Override
  public void pay(BigDecimal amount) {
    NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
    System.out.println("USD payment of " + f.format(amount));
  }
}
