package com.practice.solidandpatterns.structural.bridge.currency;

import java.math.BigDecimal;

public class NetBankingPayment extends Payment {

  public NetBankingPayment(CurrencyProcessor currencyProcessor) {
    super(currencyProcessor);
  }

  // A helper method to handle logic specific to the NetBankingPayment abstraction.
  // In a real-word scenario, this would contain code to establish a secure connection
  // with the user's bank server.
  private void connectBankingApi() {
    System.out.println("[NetBankingPayment] Establish secure connection to bank API ...");

    // Simulate a delay for connection
    try {
      Thread.sleep(100);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    System.out.println("[NetBankingPayment] Connection successful");
  }

  @Override
  public void pay(BigDecimal amount) {

    System.out.println("--- BEGIN: Net Banking Transaction ---");

    // 1. Handle the abstraction-specific logic first
    connectBankingApi();

    // 2. Delegate to the implementation-specific logic first
    // This is the "bridge" in action
    currencyProcessor.pay(amount);

    System.out.println("--- END: Net Banking Transaction ---");
  }
}
