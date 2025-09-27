package com.practice.testing.samples;

public class AgeValidator {
  // Valid age: 0 to 120 inclusive
  public boolean isValidAge(int age) {
    return age >= 0 && age <= 120;
  }
}
