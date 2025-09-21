package com.practice.multithreading.synchronization;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
  public static void main(String[] args) throws InterruptedException {
    final int NUM_WORKERS = 3;
    CountDownLatch latch = new CountDownLatch(NUM_WORKERS);

    for (int i = 0; i < NUM_WORKERS; i++) {
      final int workerId = i;
      new Thread(() -> {
        System.out.println("Worker " + workerId + " started.");
        try {
          Thread.sleep(2000); // Simulate work
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        System.out.println("Worker " + workerId + " finished.");
        latch.countDown(); // Signal completionz
      }).start();
    }

    System.out.println("Main thread is waiting for workers to finish...");
    latch.await(); // Wait until count becomes 0
    System.out.println("All workers finished. Main thread proceeds.");
  }
}
