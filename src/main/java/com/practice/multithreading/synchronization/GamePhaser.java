package com.practice.multithreading.synchronization;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class GamePhaser {

  private final Phaser phaser = new Phaser();
  private final Random random = new Random();

  public static void main(String[] args) {
    new GamePhaser().startGame();
  }

  public void startGame() {
    ExecutorService executor = Executors.newFixedThreadPool(4);

    // ➤ YOUR JOB: Register 4 players
    phaser.register(); // Player 1
    phaser.register(); // Player 2
    phaser.register(); // Player 3
    phaser.register(); // Player 4

    // ➤ YOUR JOB: Submit 4 player tasks
    for (int i = 1; i <= 4; i++) {
      final int playerId = i;
      executor.submit(() -> {
        try {
          // 🎮 PHASE 1: SETUP
          System.err.println("Player " + playerId + " is setting up...");
          TimeUnit.SECONDS.sleep(1 + random.nextInt(3));
          System.err.println("Player " + playerId + " finished setup.");
          phaser.arriveAndAwaitAdvance(); // ← Wait for all players

          // 🎮 PHASE 2: PLAY
          System.err.println("Player " + playerId + " is playing...");
          TimeUnit.SECONDS.sleep(2 + random.nextInt(4));
          System.err.println("Player " + playerId + " finished playing.");
          phaser.arriveAndAwaitAdvance(); // ← Wait for all players

          // 🎮 PHASE 3: SCORE
          System.err.println("Player " + playerId + " is calculating score...");
          TimeUnit.SECONDS.sleep(1 + random.nextInt(2));
          System.err.println("Player " + playerId + " final score calculated.");
          phaser.arriveAndDeregister(); // ← Leave phaser

        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          phaser.arriveAndDeregister(); // ← Clean deregister if interrupted
        }
      });
    }

    // Graceful shutdown
    executor.shutdown();
    try {
      if (!executor.awaitTermination(20, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }

    System.out.println("✅ Game session ended.");
  }
}
