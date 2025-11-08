package com.practice.solidandpatterns.creational.builder.person.model;

import java.util.ArrayList;
import java.util.List;

public class User {
  private final String firstName;
  private final String lastName;
  private final List<String> additionalInfo;

  private User(UserBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.additionalInfo = builder.additionalInfo;
  }

  public List<String> getAdditionalInfo() {
    return new ArrayList<>(additionalInfo);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public static class UserBuilder {
    private String firstName;
    private String lastName;
    private List<String> additionalInfo = new ArrayList<>();

    public UserBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public UserBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public UserBuilder additionalInfo(String info) {
      this.additionalInfo.add(info);
      return this;
    }

    public User build() {
      return new User(this);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("User {");
    sb.append("\n  firstName = '").append(firstName).append('\'');
    sb.append("\n  lastName = '").append(lastName).append('\'');

    if (additionalInfo != null && !additionalInfo.isEmpty()) {
      sb.append("\n  additionalInfo = [");
      sb.append(String.join(", ", additionalInfo));
      sb.append("]");
    } else {
      sb.append("\n  additionalInfo = []");
    }

    sb.append("\n}");
    return sb.toString();
  }
}
