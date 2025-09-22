package com.practice.multithreading.lock;

import java.util.concurrent.locks.StampedLock;

public class OptimisticPoint {

  private final StampedLock stampedLock = new StampedLock();
  private double x, y; // shared mutable state — protected by stampedLock

  // ✅ CONSTRUCTOR
  public OptimisticPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // ✨ STAR FEATURE: OPTIMISTIC READ — FASTEST WHEN NO WRITES
  public double distanceFromOrigin() {
    long stamp = stampedLock.tryOptimisticRead(); // ← Step 1: Try optimistic read (no blocking!)

    // Read state — assume no write is happening
    double currentX = x;
    double currentY = y;

    // Step 2: Validate — did a write happen during our read?
    if (!stampedLock.validate(stamp)) {
      // ❌ Validation failed → someone wrote → retry with real read lock
      stamp = stampedLock.readLock(); // ← Step 3: Acquire shared read lock
      try {
        currentX = x; // ← Re-read safely
        currentY = y;
      } finally {
        stampedLock.unlockRead(stamp); // ← Always unlock
      }
    }

    // ✅ Return result — safe and consistent
    return Math.sqrt(currentX * currentX + currentY * currentY);
  }

  // ✅ WRITE — exclusive access
  public void move(double deltaX, double deltaY) {
    long stamp = stampedLock.writeLock(); // ← Blocks all readers/writers
    try {
      x += deltaX;
      y += deltaY;
    } finally {
      stampedLock.unlockWrite(stamp);
    }
  }

  // ✅ READ-WRITE UPGRADE — start reading, then write if needed
  public void moveIfAtOrigin(double newX, double newY) {
    long stamp = stampedLock.readLock(); // ← Start with read lock
    try {
      while (x == 0.0 && y == 0.0) {
        // Try to upgrade to write lock
        long writeStamp = stampedLock.tryConvertToWriteLock(stamp);
        if (writeStamp != 0L) {
          // ✅ Upgrade succeeded
          stamp = writeStamp;
          x = newX;
          y = newY;
          return;
        } else {
          // ❌ Upgrade failed → release read, acquire write
          stampedLock.unlockRead(stamp);
          stamp = stampedLock.writeLock(); // ← Full write lock
          // Re-check condition after acquiring write lock
        }
      }
    } finally {
      stampedLock.unlock(stamp); // ← unlock whatever you ended up with
    }
  }

  // ✅ FOR TESTING — read with full read lock (not optimistic)
  public String toString() {
    long stamp = stampedLock.readLock();
    try {
      return String.format("(x=%.2f, y=%.2f)", x, y);
    } finally {
      stampedLock.unlockRead(stamp);
    }
  }

  // ✅ DEMO — in main
  public static void main(String[] args) throws InterruptedException {
    OptimisticPoint point = new OptimisticPoint(0, 0);

    // Writer thread — moves point occasionally
    Thread writer = new Thread(() -> {
      for (int i = 1; i <= 5; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
        point.move(i, i);
        System.out.println("MOVED → " + point);
      }
    });

    // Reader threads — calculate distance very frequently
    Runnable readerTask = () -> {
      for (int i = 0; i < 20; i++) {
        double distance = point.distanceFromOrigin();
        System.out.println(
            Thread.currentThread().getName() + " → Distance: " + String.format("%.2f", distance));
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          return;
        }
      }
    };

    writer.start();
    new Thread(readerTask, "Reader-1").start();
    new Thread(readerTask, "Reader-2").start();

    writer.join();
  }
}
