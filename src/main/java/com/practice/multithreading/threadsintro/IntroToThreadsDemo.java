package com.practice.multithreading.threadsintro;

public class IntroToThreadsDemo {
  public static void main(String[] args) {
    System.err.println("Main thread is running...");

    Thread thread1 = new Thread(task1);
    Thread thread2 = new Thread(task2);

    thread1.start();
    thread2.start();
  }

  public static Runnable task1 = () -> {
    System.err.println("Hello from thread 1!");
    try {
      Thread.sleep(500);
      System.err.println("Thread 1 is off!");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  };

  public static Runnable task2 = () -> {
    System.err.println("Hello from thread 2!");
    for (int i = 0; i < 5; i++) {
      System.out.println("New Thread: " + i);
      try {
        System.out.println("ZZZ");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  };
}
