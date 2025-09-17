package com.practice.multithreading.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class UserService {
  public static CompletableFuture<User> getUserById(String userId) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Interrupted while fetching user");
      }

      return new User(userId, "John Doe");
    });
  }

  public static CompletableFuture<List<Post>> getPostsForUser(String userId) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Interrupted while fetching user");
      }

      return List.of(new Post("p1", "My First Post"), new Post("p2", "Another Post"));
    });
  }

  public static CompletableFuture<String> getProfilePhoto(String userId) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Interrupted while fetching user");
      }

      return "https://example.com/photo.jpg";
    });
  }
}
