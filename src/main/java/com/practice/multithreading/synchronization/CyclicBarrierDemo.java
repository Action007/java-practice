package com.practice.multithreading.synchronization;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

  private final CyclicBarrier barrier = new CyclicBarrier(5); // ← 5 runners must reach barrier
  private final Random random = new Random();

  public static void main(String[] args) {
    new CyclicBarrierDemo().startRace();
  }

  public void startRace() {
    ExecutorService executor = Executors.newFixedThreadPool(5);

    for (int i = 1; i <= 5; i++) {
      final int runnerId = i;
      executor.submit(() -> {
        System.err.println("Runner " + runnerId + " is getting ready.");

        try {
          TimeUnit.SECONDS.sleep(random.nextInt(4) + 1);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        System.err.println("Runner " + runnerId + " is at start line — waiting for others...");

        try {
          barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          Thread.currentThread().interrupt();
        }

        System.err.println("🏁 Runner X started running!");
      });
    }

    executor.shutdown();
    try {
      if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}
