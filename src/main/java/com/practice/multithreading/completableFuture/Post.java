package com.practice.multithreading.completableFuture;

public class Post {
  private String id;
  private String title;

  public Post(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Post{id='" + id + "', title='" + title + "'}";
  }
}
