package com.practice.testing.samples;

public class Counter {
  private int count = 0;

  public void increment() {
    count++;
  }

  public void decrement() {
    count--;
  }

  public int getCount() {
    return count;
  }

  public void reset() {
    count = 0;
  }
}
