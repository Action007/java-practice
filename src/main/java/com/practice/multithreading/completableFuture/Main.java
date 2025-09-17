package com.practice.multithreading.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
  public static void main(String[] args) {
    String userId = "u1";

    System.out.println("🔍 Starting profile fetch for user: " + userId);

    CompletableFuture<User> userFuture = UserService.getUserById(userId);
    CompletableFuture<List<Post>> userPostsFuture =
        userFuture.thenCompose((user) -> UserService.getPostsForUser(user.getId()));
    CompletableFuture<String> userPhotoFuture =
        UserService.getProfilePhoto(userId).exceptionally((error) -> {
          System.out.println("⚠️ Photo fetch failed — using default.");
          return "https://example.com/default.jpg";
        });

    CompletableFuture<Void> allDone =
        CompletableFuture.allOf(userFuture, userPostsFuture, userPhotoFuture);

    allDone.thenApply((v) -> {
      User user = userFuture.join();
      List<Post> posts = userPostsFuture.join();
      String photo = userPhotoFuture.join();
      return new UserProfile(user, posts, photo);
    }).handle((success, error) -> {
      if (error != null) {
        System.out.println("Something wrong: " + error.getMessage());
        return null;
      } else {
        System.out.println(success);
        return success;
      }
    }).thenRun(() -> {
      System.out.println("✅ Profile fetch process completed.");
    }).join();
  }
}
