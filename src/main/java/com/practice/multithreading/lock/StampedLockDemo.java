package com.practice.multithreading.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
  private final StampedLock lock = new StampedLock();
  private double x, y; // shared mutable state

  // ✅ Optimistic read — fastest when no writes
  public double distanceFromOrigin() {
    long stamp = lock.tryOptimisticRead();
    double currentX = x, currentY = y;
    if (!lock.validate(stamp)) { // ← Check if write happened
      stamp = lock.readLock(); // ← Upgrade to real read
      try {
        currentX = x;
        currentY = y;
      } finally {
        lock.unlockRead(stamp);
      }
    }
    return Math.sqrt(currentX * currentX + currentY * currentY);
  }

  // ✅ Write — exclusive
  public void move(double deltaX, double deltaY) {
    long stamp = lock.writeLock();
    try {
      x += deltaX;
      y += deltaY;
    } finally {
      lock.unlockWrite(stamp);
    }
  }

  // ✅ Read with upgrade to write
  public void moveIfAtOrigin(double newX, double newY) {
    long stamp = lock.readLock();
    try {
      while (x == 0.0 && y == 0.0) {
        long ws = lock.tryConvertToWriteLock(stamp);
        if (ws != 0L) {
          stamp = ws;
          x = newX;
          y = newY;
          return;
        } else {
          // upgrade failed → release read, get write
          lock.unlockRead(stamp);
          stamp = lock.writeLock();
          // re-check condition after acquiring write lock
        }
      }
    } finally {
      lock.unlock(stamp);
    }
  }
}
