package com.example.collection.UserSessionTracker;

public class Main {
  public static void main(String[] args) {
    SessionCache cache = new SessionCache();

    // Add 100 sessions
    for (int i = 1; i <= 100; i++) {
      cache.put("user" + i, new UserSession("user" + i));
    }

    System.out.println("Size after 100: " + cache.size()); // Should be 100

    // Access user1 -> mark as recently used
    cache.get("user1");

    // Add user101 -> should evict least recently used (not user1)
    cache.put("user101", new UserSession("user101"));

    System.out.println("Size after 101st: " + cache.size()); // Should be 100

    // Check if user1 still exists
    System.out.println("Contains user1: " + (cache.get("user1") != null)); // Should be true

    // Check if user2 exists? Maybe, maybe not — depends on access order
    // But one of the middle ones should be gone
    System.out.println("LRU test complete.");
  }
}
