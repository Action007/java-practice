package com.practice.testing.samples.advanced;

import java.util.Random;

public class RandomNumberGenerator {
  private final Random random = new Random();

  public int nextInt(int bound) {
    return random.nextInt(bound);
  }

  public boolean isEven(int number) {
    return number % 2 == 0;
  }
}
