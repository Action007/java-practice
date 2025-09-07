package com.practice.multithreading.threadvsrunnable;

public class ThreadVsRunnable {
  public static void main(String[] args) {
    ThreadVsRunnable runnable = new ThreadVsRunnable();
    MyRunnableTask sharedRunnable = runnable.new MyRunnableTask();

    Thread runnablThread1 = new Thread(sharedRunnable, "MyRunnableTask no1");
    Thread runnablThread2 = new Thread(sharedRunnable, "MyRunnableTask no2");

    runnablThread1.start();
    runnablThread2.start();

    runnable.new MyThreadClass().start();
    runnable.new MyThreadClass().start();
  }

  public class MyThreadClass extends Thread {
    private int counter = 0; // Shared mutable state

    @Override
    public void run() {
      System.err.println("Hello from Thread class!");

      for (int i = 0; i < 5; i++) {
        System.err
            .println("Thread: " + Thread.currentThread().getName() + ", Counter: " + counter++);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  public class MyRunnableTask implements Runnable {
    private int counter = 0; // Shared mutable state

    @Override
    public void run() {
      System.err.println("Hello from Runnable class! " + Thread.currentThread().getName());

      for (int i = 0; i < 5; i++) {
        System.err
            .println("Thread: " + Thread.currentThread().getName() + ", Counter: " + counter++);
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
