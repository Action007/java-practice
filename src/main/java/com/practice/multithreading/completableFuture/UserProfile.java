package com.practice.multithreading.completableFuture;

import java.util.List;

public class UserProfile {
  private User user;
  private List<Post> posts;
  private String photoUrl;

  public UserProfile(User user, List<Post> posts, String photoUrl) {
    this.user = user;
    this.posts = posts;
    this.photoUrl = photoUrl;
  }

  public User getUser() {
    return user;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  @Override
  public String toString() {
    return "UserProfile{" + "user=" + user + ", posts=" + posts + ", photoUrl='" + photoUrl + '\''
        + '}';
  }
}
