package com.practice.multithreading.volatilevisibility;

public class VolatileVisibilityDemo {
  // Try commenting/uncommenting 'volatile' to see the difference
  private static volatile boolean running = true;
  private static long counter = 0;

  public static void main(String[] args) throws InterruptedException {
    // Thread that runs the loop
    Thread worker = new Thread(() -> {
      System.out.println("Worker thread (" + Thread.currentThread().getName() + ") starting...");

      // CPU-intensive loop: no I/O, no sleep
      while (running) {
        counter++; // Just increment — fast operation
      }

      System.out.println("Worker thread detected running = false.");
      System.out.println("Final counter value: " + counter);
    }, "WorkerThread");

    worker.start();

    // Let the worker run for 1 second
    Thread.sleep(1000);

    // Change the flag
    System.out.println("Main thread setting running = false");
    running = false;

    // Wait a bit to see if worker stops
    worker.join(2000); // Wait max 2 seconds for thread to stop

    if (worker.isAlive()) {
      System.out.println("Worker thread is still running! Visibility issue detected.");
    } else {
      System.out.println("Worker thread stopped gracefully.");
    }

    System.out.println("Main thread ending.");
  }
}
