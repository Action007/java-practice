package com.practice.testing.samples.advanced;

public class EmailValidator {
  public boolean isValid(String email) {
    if (email == null)
      return false;
    return email.contains("@") && email.contains(".");
  }

  public void processEmail(String email) {
    if (!isValid(email)) {
      throw new IllegalArgumentException("Invalid email: " + email);
    }
    // Simulate slow processing
    try {
      Thread.sleep(800); // 800ms
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
