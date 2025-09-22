package com.practice.multithreading.synchronization;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

  private final CountDownLatch latch = new CountDownLatch(3); // ← 3 services to wait for
  private final Random random = new Random();

  public static void main(String[] args) {
    new CountDownLatchDemo().start();
  }

  public void start() {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    // Submit DatabaseService
    executor.submit(() -> {
      System.err.println("Starting database service: " + Thread.currentThread().getName());
      int randomNumber = random.nextInt(3) + 1;

      try {
        TimeUnit.SECONDS.sleep(randomNumber);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      System.err.println("Database service initialized");
      latch.countDown();
    });

    // Submit CacheService
    executor.submit(() -> {
      System.err.println("Starting cache service: " + Thread.currentThread().getName());
      int randomNumber = random.nextInt(3) + 1;

      try {
        TimeUnit.SECONDS.sleep(randomNumber);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      System.err.println("Cache service initialized");
      latch.countDown();
    });

    // Submit AuthService
    executor.submit(() -> {
      System.err.println("Starting auth service: " + Thread.currentThread().getName());
      int randomNumber = random.nextInt(3) + 1;

      try {
        TimeUnit.SECONDS.sleep(randomNumber);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      System.err.println("Auth service initialized");
      latch.countDown();
    });

    try {
      latch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("✅ All services ready — Server is running!");

    // Graceful shutdown
    executor.shutdown();
    try {
      if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}
