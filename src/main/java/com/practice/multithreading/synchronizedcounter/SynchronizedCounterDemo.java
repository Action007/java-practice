package com.practice.multithreading.synchronizedcounter;

public class SynchronizedCounterDemo {
  static int counter = 0;
  private static final Object lock = new Object();

  public static void main(String[] args) {
    Runnable task = () -> {
      synchronized (lock) {

        for (int i = 0; i < 10000; i++) {
          counter = counter + 1;
        }
      }
    };

    Thread thread1 = new Thread(task, "thread1");
    Thread thread2 = new Thread(task, "thread2");
    Thread thread3 = new Thread(task, "thread3");
    Thread thread4 = new Thread(task, "thread4");

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    try {
      thread1.join();
      thread2.join();
      thread3.join();
      thread4.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // restore interrupt status
      System.err.println("Main thread interrupted");
    }

    System.out.println(SynchronizedCounterDemo.counter);
  }
}
