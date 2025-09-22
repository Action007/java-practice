package com.practice.multithreading.synchronization;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

  private final Exchanger<String> exchanger = new Exchanger<>();

  public static void main(String[] args) {
    new ExchangerDemo().start();
  }

  public void start() {
    // Thread 1: Producer
    new Thread(() -> {
      try {
        String messageToSend = "Hello from Producer";
        System.out.println("Thread Producer sending: " + messageToSend);
        String receivedMessage = exchanger.exchange(messageToSend);
        System.out.println("Thread Producer received: " + receivedMessage);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Producer").start();

    // Thread 2: Consumer
    new Thread(() -> {
      try {
        String messageToSend = "Hello from Consumer";
        System.out.println("Thread Consumer sending: " + messageToSend);
        String receivedMessage = exchanger.exchange(messageToSend);
        System.out.println("Thread Consumer received: " + receivedMessage);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, "Consumer").start();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("✅ Exchange session ended.");
  }
}
