package com.practice.multithreading.deadlock;

import java.util.Random;

public class LivelockDemo {
  private static class Friend {
    private final String name;
    private boolean active = true;
    Random random = new Random();

    public Friend(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    // Try to bow together — but be polite and yield if other is bowing
    public void bow(Friend bower) {
      if (this.active && bower.active) {
        System.out.println(name + ": " + bower.name + " is bowing to me, so I'll bow back!");
        bowBack(bower);
      }
    }

    public void bowBack(Friend bower) {
      if (this.active && bower.active) {
        System.out.println(name + ": Bending down to bow to " + bower.name);
        // Simulate "yielding" behavior — checking if the other is still bowing
        try {
          Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
        }

        // But if the other is still active, keep responding!
        if (bower.active) {
          System.out.println(name + ": " + bower.name + " is still bowing! I'll bow again!");
          bow(bower); // Recursively try to respond — livelock!
        } else {
          System.out.println(name + ": Finally, we're done bowing.");
        }
      }
    }
  }

  public static void main(String[] args) {
    Friend alice = new Friend("Alice");
    Friend bob = new Friend("Bob");

    Thread t1 = new Thread(() -> alice.bow(bob), "Alice-Thread");
    Thread t2 = new Thread(() -> bob.bow(alice), "Bob-Thread");

    t1.start();
    t2.start();
  }
}
