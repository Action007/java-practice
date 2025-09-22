package com.practice.multithreading.synchronization;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

  private final Semaphore connectionSlots = new Semaphore(3); // ← Only 3 connections available
  private final Random random = new Random();

  public static void main(String[] args) {
    new SemaphoreDemo().start();
  }

  public void start() {
    ExecutorService executor = Executors.newFixedThreadPool(10); // ← 10 threads want to use DB

    // 2. Acquire semaphore → blocks if no slot
    // 6. Release semaphore


    for (int i = 1; i <= 10; i++) {
      final int threadId = i;
      executor.submit(() -> {
        System.err.println("Thread " + threadId + " is requesting DB connection...");

        try {
          connectionSlots.acquire();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        System.err.println("Thread " + threadId + " got DB connection → processing...");

        try {
          TimeUnit.SECONDS.sleep(random.nextInt(4) + 2);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        System.err.println("Thread " + threadId + " released DB connection");

        connectionSlots.release();
      });
    }

    executor.shutdown();
    try {
      if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}
