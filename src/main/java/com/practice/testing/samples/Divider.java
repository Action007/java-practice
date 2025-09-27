package com.practice.testing.samples;

public class Divider {
  public double divide(double dividend, double divisor) {
    if (divisor == 0) {
      throw new IllegalArgumentException("Division by zero is not allowed.");
    }
    return dividend / divisor;
  }
}
