package com.practice.multithreading.lock;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RequestProcessor {
  private final Deque<String> requestQueue = new LinkedList<>();
  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock writeLock = readWriteLock.writeLock();
  private final Condition hasRequests = writeLock.newCondition();
  private ExecutorService executor;

  public static void main(String[] args) {
    RequestProcessor processor = new RequestProcessor();

    processor.startProcessing();

    processor.submit("User Login Request");
    processor.submit("Data Fetch Request");
    processor.submit("Payment Process Request");
    processor.submit("Report Generate Request");
    processor.submit("Logout Request");

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    processor.shutdown();
  }

  public void shutdown() {
    executor.shutdown();
    try {
      if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  public void startProcessing() {
    this.executor = Executors.newFixedThreadPool(2);
    this.executor.submit(this::worker);
    this.executor.submit(this::worker);
  }

  private void worker() {
    while (true) {
      writeLock.lock();
      String request = null;

      try {
        while (requestQueue.isEmpty()) {
          hasRequests.await();
        }

        request = requestQueue.removeFirst();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      } finally {
        writeLock.unlock();
      }

      System.err.println(Thread.currentThread().getName() + " processing: " + request);
      try {
        TimeUnit.MICROSECONDS.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void submit(String request) {
    writeLock.lock();

    try {
      requestQueue.addLast(request);
      hasRequests.signal();
    } finally {
      writeLock.unlock();
    }
  }
}
