package com.practice.multithreading.deadlock;

public class DeadlockDemo {
  private static final Object fork = new Object();
  private static final Object knife = new Object();

  public static void main(String[] args) {
    Thread chefA = new Thread(() -> {
      synchronized (fork) {
        System.out.println("Chef A acquired fork. Now waiting for knife...");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        synchronized (knife) {
          System.out.println("Chef A has both fork and knife. Eating...");
        }
      }
    }, "Chef-A");

    Thread chefB = new Thread(() -> {
      synchronized (knife) {
        System.out.println("Chef B acquired knife. Now waiting for fork...");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        synchronized (fork) {
          System.out.println("Chef B has both. Eating...");
        }
      }
    }, "Chef-B");

    chefA.start();
    chefB.start();
  }
}
