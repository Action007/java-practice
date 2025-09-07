package com.practice.multithreading.synchronizationobjects;

// Import the Exchanger class — it allows two threads to swap data
import java.util.concurrent.Exchanger;

// Main class to run the demo
public class BufferSwapDemo {
  public static void main(String[] args) {
    // Create an Exchanger that swaps StringBuilder objects
    // Think of it as a "handoff point" where two threads meet to exchange buffers
    Exchanger<StringBuilder> exchanger = new Exchanger<>();

    // Start the Producer thread (it will fill a buffer and wait to swap)
    new Thread(new Producer(exchanger), "Producer").start();

    // Start the Consumer thread (it will take the filled buffer and give back an empty one)
    new Thread(new Consumer(exchanger), "Consumer").start();
  }
}


// Producer: This thread creates/fills data in a buffer and exchanges it with the Consumer
class Producer implements Runnable {
  // Reference to the shared Exchanger so this thread can swap data
  private final Exchanger<StringBuilder> exchanger;

  // Constructor: gets the exchanger from main()
  Producer(Exchanger<StringBuilder> ex) {
    this.exchanger = ex;
  }

  public void run() {
    // This is the buffer the Producer will use (created once, reused)
    StringBuilder buffer = new StringBuilder();

    // Start with letter 'A' — will increment to make strings like "ABCDE"
    char ch = 'A';

    // We'll do 3 rounds of filling and swapping
    for (int i = 0; i < 3; i++) {
      // 👉 STEP 1: Clear the old content from the buffer
      // We reuse the same StringBuilder instead of creating a new one
      buffer.setLength(0); // Makes the buffer empty again

      // 👉 STEP 2: Fill the buffer with 5 letters (e.g., "ABCDE")
      for (int j = 0; j < 5; j++) {
        buffer.append(ch++); // Add current letter, then move to next (A→B→C...)
      }

      // Show what was filled
      System.out.println(Thread.currentThread().getName() + " filled: " + buffer);

      // 👉 STEP 3: Try to swap the full buffer with the Consumer
      // The Producer gives the full buffer and expects to get an empty one back
      try {
        System.out.println("Producer waiting to swap...");

        // This line is KEY:
        // - Sends 'buffer' (full) to Consumer
        // - Waits until Consumer calls exchange() too
        // - When both meet, Producer gets back whatever Consumer sent (an empty buffer)
        buffer = exchanger.exchange(buffer);

        // After the swap, Producer now has the empty buffer sent by Consumer
        System.out.println("Producer got back an empty buffer.");
      } catch (InterruptedException e) {
        // If the thread is interrupted while waiting, stop the work
        System.out.println("Producer was interrupted.");
        Thread.currentThread().interrupt(); // Restore interrupt status
        return;
      }
    }

    // After 3 swaps, Producer finishes
    System.out.println("Producer is done.");
  }
}


// Consumer: This thread receives a filled buffer, uses it (prints), and returns an empty one
class Consumer implements Runnable {
  // Reference to the same Exchanger used by Producer
  private final Exchanger<StringBuilder> exchanger;

  // Constructor: gets the exchanger
  Consumer(Exchanger<StringBuilder> ex) {
    this.exchanger = ex;
  }

  public void run() {
    // This buffer starts empty and will be reused
    StringBuilder buffer = new StringBuilder(); // Initially empty

    // We'll do 3 rounds of receiving and printing
    for (int i = 0; i < 3; i++) {
      try {
        // 👉 STEP 1: Prepare to swap
        // Consumer has an empty buffer and wants a full one
        System.out.println("Consumer waiting to swap...");

        // This is the KEY line:
        // - Sends 'buffer' (empty) to Producer
        // - Waits until Producer calls exchange() too
        // - When both meet, Consumer gets back the full buffer from Producer
        buffer = exchanger.exchange(buffer);

        // Now buffer contains the data sent by Producer
        System.out.println("Consumer got: " + buffer);
      } catch (InterruptedException e) {
        // If interrupted while waiting, stop
        System.out.println("Consumer was interrupted.");
        Thread.currentThread().interrupt();
        return;
      }
    }

    // After 3 swaps, Consumer finishes
    System.out.println("Consumer is done.");
  }
}
