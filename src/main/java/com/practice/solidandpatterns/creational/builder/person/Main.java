package com.practice.solidandpatterns.creational.builder.person;

import com.practice.solidandpatterns.creational.builder.person.model.User;
import com.practice.solidandpatterns.creational.builder.person.model.UserDirector;

public class Main {
  public static void main(String[] args) {
    User.UserBuilder builder = new User.UserBuilder();
    UserDirector.constructNapaleon(builder);
    User user = builder.build();
    System.err.println(user);
  }
}
