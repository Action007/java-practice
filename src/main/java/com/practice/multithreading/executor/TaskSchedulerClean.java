package com.practice.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TaskSchedulerClean {

  public static void main(String[] args) throws InterruptedException {
    // Create thread factories (reusable)
    ThreadFactory cpuFactory = new NamedThreadFactory("CPU-Pool");
    ThreadFactory ioFactory = new NamedThreadFactory("IO-Pool");
    ThreadFactory singleFactory = new NamedThreadFactory("Sequential-Pool");
    ThreadFactory scheduledFactory = new NamedThreadFactory("Scheduled-Pool");

    // ✅ Use convenience methods
    ExecutorService cpuPool =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), cpuFactory);

    ExecutorService ioPool = Executors.newCachedThreadPool(ioFactory);

    ExecutorService singlePool = Executors.newSingleThreadExecutor(singleFactory);

    ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2, scheduledFactory);

    // Example with ThreadPoolExecutor:
    // ThreadPoolExecutor cpuPool = new
    // ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
    // Runtime.getRuntime().availableProcessors(), 0L, TimeUnit.MILLISECONDS,
    // new LinkedBlockingQueue<>(), cpuThreadFactory);

    // ExecutorService ioPool = Executors.newCachedThreadPool(ioThreadFactory);

    // ExecutorService singlePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
    // new LinkedBlockingQueue<>(), singleThreadFactory);

    // ScheduledExecutorService scheduledPool =
    // new ScheduledThreadPoolExecutor(2, scheduledThreadFactory);

    // Submit tasks (same as before)
    for (int i = 0; i < 3; i++)
      cpuPool.submit(cpuTask);
    for (int i = 0; i < 5; i++)
      ioPool.submit(ioTask);
    for (int i = 0; i < 3; i++)
      singlePool.submit(sequentialTask);

    scheduledPool.scheduleAtFixedRate(
        () -> System.out.println("Periodic task (fixed rate): " + System.currentTimeMillis()), 0, 1,
        TimeUnit.SECONDS);

    scheduledPool.scheduleWithFixedDelay(
        () -> System.out.println("Periodic task (fixed delay): " + System.currentTimeMillis()), 0,
        1, TimeUnit.SECONDS);

    // Let tasks run
    Thread.sleep(3000);

    // Shutdown (same as before)
    System.out.println("Shutting down...");
    shutdownGracefully(cpuPool, "CPU");
    shutdownGracefully(ioPool, "IO");
    shutdownGracefully(singlePool, "Sequential");
    shutdownGracefully(scheduledPool, "Scheduled");
  }

  // ✅ Helper method to avoid repetition
  private static void shutdownGracefully(ExecutorService pool, String name) {
    pool.shutdown();
    try {
      if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
        System.out.println(name + " pool forced shutdown.");
        pool.shutdownNow();
      }
    } catch (InterruptedException e) {
      System.out.println(name + " pool interrupted during shutdown.");
      pool.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  // Tasks (same as before)
  static Runnable cpuTask = () -> {
    long result = 1;
    for (int i = 1; i <= 10000; i++)
      result *= i;
    System.out.println("CPU Task completed by: " + Thread.currentThread().getName());
  };

  static Runnable ioTask = () -> {
    try {
      TimeUnit.MILLISECONDS.sleep(500);
      System.out.println("IO task completed by: " + Thread.currentThread().getName());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  };

  static Runnable sequentialTask = () -> {
    System.out.println("Sequential Task started by: " + Thread.currentThread().getName());
    try {
      TimeUnit.MILLISECONDS.sleep(200);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("Sequential Task completed by: " + Thread.currentThread().getName());
  };

  static class NamedThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private int count = 1;

    public NamedThreadFactory(String namePrefix) {
      this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r);
      t.setName(namePrefix + "-" + count++);
      return t;
    }
  }
}
