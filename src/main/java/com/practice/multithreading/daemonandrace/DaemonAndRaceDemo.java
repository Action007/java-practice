package com.practice.multithreading.daemonandrace;

public class DaemonAndRaceDemo {
  private static int counter = 0;

  public static void main(String[] args) {
    System.out.println("Main thread starting...");

    // === PART 1: Demonstrate Daemon Thread ===
    Thread daemonThread = new Thread(() -> {
      while (true) {
        System.out.println("Daemon thread running: " + Thread.currentThread().getName());
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          System.out.println("Daemon thread interrupted.");
          Thread.currentThread().interrupt();
          return;
        }
      }
    }, "DaemonWorker");

    // Mark as daemon BEFORE start()
    daemonThread.setDaemon(true);
    daemonThread.start();

    // === PART 2: Race Condition on Shared Counter ===
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Not atomic! No synchronization
      }
    }, "RaceThread-1");

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Race condition here
      }
    }, "RaceThread-2");

    Thread t3 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Race condition here
      }
    }, "RaceThread-3");

    Thread t4 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Race condition here
      }
    }, "RaceThread-4");


    Thread t5 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Race condition here
      }
    }, "RaceThread-5");


    Thread t6 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter = counter + 1; // Race condition here
      }
    }, "RaceThread-6");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();

    // Wait for both threads to finish
    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    // Check final counter value
    System.out.println("Final counter value: " + counter); // Often < 2000

    // === End of main thread ===
    System.out.println("Main thread ending. JVM will now exit...");
    // Once main ends, daemonThread will be killed automatically
  }
}
