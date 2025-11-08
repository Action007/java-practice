package com.practice.solidandpatterns.creational.builder.person.model;

public class UserDirector {
  public static void constructNapaleon(User.UserBuilder builder) {
    builder.firstName("Napoleon").lastName("Bonaparte").additionalInfo("French")
        .additionalInfo("Emperor");
  }
}
