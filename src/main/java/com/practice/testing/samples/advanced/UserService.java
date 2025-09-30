package com.practice.testing.samples.advanced;

import java.util.*;

public class UserService {
  public List<String> getActiveUserEmails() {
    return Arrays.asList("alice@example.com", "bob@test.org", "charlie@demo.net");
  }

  public Map<String, Integer> getUserAges() {
    Map<String, Integer> ages = new HashMap<>();
    ages.put("Alice", 30);
    ages.put("Bob", 25);
    ages.put("Charlie", 35);
    return ages;
  }
}
