package com.practice.multithreading.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorCallableFutureDemo {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    List<Future<Integer>> futures = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      int finalI = i;
      futures.add(executor.submit(() -> {
        Thread.sleep(1000);
        return finalI * finalI;
      }));
    }

    // ✅ Step 1: Initiate shutdown (no more tasks)
    executor.shutdown();

    // ✅ Step 2: Wait for futures to complete (this also waits for tasks)
    futures.stream().forEach(future -> {
      try {
        System.out.println("Result: " + future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });

    // ✅ Step 3: Optional: wait a bit more for cleanup (redundant here, but safe)
    try {
      if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
        System.out.println("Some tasks didn't terminate in time; forcing shutdown.");
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}
