package com.practice.multithreading.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {

  private Lock lock = new ReentrantLock();

  // 🚨 SHARED MUTABLE STATE — this is what makes locking necessary!
  private double sharedTotal = 0.0;

  public static void main(String[] args) throws InterruptedException {
    var demo = new ReentrantLockDemo2();
    var es = Executors.newFixedThreadPool(4);

    // Mix of calls — some through calculate(), some direct
    es.execute(() -> demo.calculate("+", 10, 5)); // → calls add(10,5)
    es.execute(() -> demo.add(20, 10)); // → direct call to add()
    es.execute(() -> demo.calculate("+", 30, 15)); // → calls add(30,15)
    es.execute(() -> demo.add(40, 20)); // → direct call to add()

    terminateExecutorService(es);

    // Print final result — should be 10+5 + 20+10 + 30+15 + 40+20 = 140
    System.out.println("\n✅ Final sharedTotal = " + demo.sharedTotal);
  }

  // 🔒 THREAD-SAFE BECAUSE IT LOCKS INTERNALLY
  public void add(double value1, double value2) {
    try {
      lock.lock();
      System.out
          .println(Thread.currentThread().getName() + " → add(" + value1 + ", " + value2 + ")");

      // 🧨 CRITICAL SECTION — read-modify-write on shared state
      double temp = value1 + value2;
      sharedTotal += temp; // ← Without lock, this is NOT atomic → RACE CONDITION!

      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      System.out
          .println(Thread.currentThread().getName() + " → sharedTotal updated to: " + sharedTotal);
    } finally {
      lock.unlock();
    }
  }

  // Same for subtract — omitted for brevity
  public void subtract(double value1, double value2) {
    try {
      lock.lock();
      System.out.println(
          Thread.currentThread().getName() + " → subtract(" + value1 + ", " + value2 + ")");
      sharedTotal -= (value1 - value2);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      System.out
          .println(Thread.currentThread().getName() + " → sharedTotal updated to: " + sharedTotal);
    } finally {
      lock.unlock();
    }
  }

  public void calculate(String operation, double operand1, double operand2) {
    try {
      lock.lock();
      System.out.println(Thread.currentThread().getName() + " → calculate(" + operation + ", "
          + operand1 + ", " + operand2 + ")");

      switch (operation) {
        case "+":
          add(operand1, operand2); // ← Reentrant: safe!
          break;
        case "-":
          subtract(operand1, operand2); // ← Reentrant: safe!
          break;
        default:
          System.out.println("Unsupported");
      }
    } finally {
      lock.unlock();
    }
  }

  private static void terminateExecutorService(ExecutorService es) throws InterruptedException {
    es.shutdown();
    if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
      es.shutdownNow();
    }
  }
}
