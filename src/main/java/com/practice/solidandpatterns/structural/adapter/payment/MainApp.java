package com.practice.solidandpatterns.structural.adapter.payment;

import java.math.BigDecimal;

public class MainApp {
  public static void main(String[] args) {
    PaymentService paymentService = new PaymentService();

    PaymentProcessor demo = new DemoPaymentProcessor();
    paymentService.processPayment(demo, new BigDecimal("100.00"));

    PaymentProcessor paypalSdk = new PaypalAdapter(new PaypalSDK());
    paymentService.processPayment(paypalSdk, new BigDecimal("230.00"));

    PaymentProcessor stripeSdk = new StripeAdapter(new StripeSDK());
    paymentService.processPayment(stripeSdk, new BigDecimal("20.00"));
  }
}
