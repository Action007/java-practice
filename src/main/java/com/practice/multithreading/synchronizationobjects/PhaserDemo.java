package com.practice.multithreading.synchronizationobjects;

// Import the Phaser class — it allows threads to synchronize across multiple phases
import java.util.concurrent.Phaser;

// Main class to demonstrate how Phaser works in a multi-stage game
public class PhaserDemo {

  public static void main(String[] args) {
    // Create a new Phaser with 1 registered party — the main thread
    // This means: "I (main thread) am also a participant in the phaser"
    // So, the phaser will wait for main thread + any others before advancing
    Phaser game = new Phaser(1);

    // Start 3 player threads — each will join the game and register with the phaser
    new Thread(new Player(game, "Alice")).start();
    new Thread(new Player(game, "Bob")).start();
    new Thread(new Player(game, "Charlie")).start();

    // We will go through 3 phases: Setup, Play, Finish
    for (int p = 0; p < 3; p++) {
      // Main thread signals: "I have arrived at this phase"
      // It will WAIT here until ALL registered threads (players + main) also arrive
      // This synchronizes the main thread with all players
      game.arriveAndAwaitAdvance();

      // Once all threads have arrived, the phase ends and this line executes
      System.out.println("Phase " + p + " complete");
    }
    // After 3 phases, the program ends
  }
}


// Represents a player in the game
// Each player is a thread that participates in the phaser's phases
class Player implements Runnable {
  private final Phaser phaser; // Reference to the shared Phaser
  private final String name; // Name of the player

  // Constructor: receives the phaser and player name
  public Player(Phaser phaser, String name) {
    this.phaser = phaser;
    this.name = name;
  }

  public void run() {
    // When a player thread starts, it registers itself with the phaser
    // This means: "I am a participant — wait for me in each phase"
    // Now phaser knows: "There’s one more party in the game"
    phaser.register();

    // --- PHASE 0: SETUP ---
    System.out.println(name + " is setting up...");
    // Signal that this player has completed phase 0
    // AND WAIT until all other players (and main) also finish this phase
    phaser.arriveAndAwaitAdvance();

    // --- PHASE 1: PLAY ---
    System.out.println(name + " is playing the game...");
    // Again, wait for all to complete phase 1
    phaser.arriveAndAwaitAdvance();

    // --- PHASE 2: FINISH ---
    // This is the final phase
    System.out.println(name + " is finishing...");

    // SPECIAL CASE: Charlie quits after this phase
    if (name.equals("Charlie")) {
      // He participates in phase 2, but says: "I’m done — don’t wait for me in future"
      // This removes him from the phaser
      // After this, only Alice and Bob remain
      phaser.arriveAndDeregister();
      System.out.println(name + " has left the game.");
    } else {
      // Alice and Bob continue to the end
      // They signal arrival and wait for others (including main thread)
      phaser.arriveAndAwaitAdvance();
    }
  }
}
