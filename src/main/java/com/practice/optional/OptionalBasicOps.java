package com.practice.optional;

import java.util.*;

public class OptionalBasicOps {

  static class User {
    String username;
    String email;
    Integer age;

    public User(String username, String email, Integer age) {
      this.username = username;
      this.email = email;
      this.age = age;
    }

    public String getUsername() {
      return username;
    }

    public String getEmail() {
      return email;
    }

    public Integer getAge() {
      return age;
    }

    @Override
    public String toString() {
      return String.format("User{username='%s', email='%s', age=%d}", username, email, age);
    }
  }

  // ========== EXERCISE 1: Creating Optionals ==========

  // TODO: Create an Optional with value "John"
  // Hint: Optional.of(...)
  static Optional<String> name1 = null;

  // TODO: Create an Optional from a nullable string (might be null)
  // Hint: Optional.ofNullable(...)
  static String nullableString = null; // This is null
  static Optional<String> name2 = null;

  // TODO: Create an empty Optional
  // Hint: Optional.empty()
  static Optional<String> emptyOptional = null;

  // ========== EXERCISE 2: Checking if Optional Has Value ==========

  static Optional<String> john = Optional.of("John");
  static Optional<String> empty = Optional.empty();

  // TODO: Check if 'john' has a value (should be true)
  // Hint: .isPresent()
  static boolean johnPresent = false;

  // TODO: Check if 'empty' is empty (should be true)
  // Hint: .isEmpty()
  static boolean emptyIsEmpty = false;

  // ========== EXERCISE 3: Getting Values Safely ==========

  static Optional<String> city = Optional.of("New York");
  static Optional<String> noCity = Optional.empty();

  // TODO: Get city value, or "Unknown" if empty
  // Hint: .orElse("Unknown")
  static String cityName = null;

  // TODO: Get noCity value, or "Unknown" if empty
  static String noCityName = null;

  // TODO: Get city value, or throw exception if empty
  // Hint: .orElseThrow()
  static String cityOrThrow = null;

  // ========== EXERCISE 4: orElse() vs orElseGet() ==========

  static int expensiveMethodCallCount = 0;

  static String expensiveMethod() {
    expensiveMethodCallCount++;
    System.out.println("Expensive method called!");
    return "Expensive Result";
  }

  // TODO: Use orElse() with expensiveMethod()
  // Notice: expensiveMethod() will be called even though optional has value
  static Optional<String> hasValue = Optional.of("Value");
  static String result1 = null; // hasValue.orElse(expensiveMethod())

  // TODO: Use orElseGet() with expensiveMethod()
  // Notice: expensiveMethod() will NOT be called because optional has value
  static String result2 = null; // hasValue.orElseGet(() -> expensiveMethod())

  // ========== EXERCISE 5: ifPresent() ==========

  static List<User> users = Arrays.asList(new User("alice", "alice@example.com", 25),
      new User("bob", "bob@example.com", 30), new User("charlie", null, 35));

  // TODO: Find user "alice" and print her email if it exists
  // Hint: findUserByName("alice").ifPresent(u -> System.out.println(u.getEmail()))
  static void printAliceEmail() {
    // Your code here
  }

  // TODO: Find user "david" (doesn't exist) and print "User not found" if empty
  // Hint: Use ifPresentOrElse() (Java 11+)
  static void findDavid() {
    // Your code here
  }

  // ========== EXERCISE 6: Avoiding get() ==========

  static Optional<Integer> age = Optional.of(25);
  static Optional<Integer> noAge = Optional.empty();

  // TODO: Get age value using get() - THIS IS DANGEROUS!
  // It will work for 'age' but crash for 'noAge'
  static Integer age1 = null; // age.get() - works
  // static Integer age2 = noAge.get(); // CRASHES! Uncomment to see

  // TODO: Get age safely using orElse()
  static Integer age2 = null; // noAge.orElse(0)

  // ========== EXERCISE 7: Real-World - Database Query ==========

  // Simulates database query that might not find user
  static Optional<User> findUserByName(String name) {
    return users.stream().filter(u -> u.getUsername().equals(name)).findFirst();
  }

  // TODO: Find user "alice" and return her email, or "no-email@example.com" if not found
  static String aliceEmail = null;

  // TODO: Find user "bob" and return his age, or 0 if not found
  static int bobAge = 0;

  // TODO: Find user "zack" (doesn't exist) and return username, or "Guest" if not found
  static String zackUsername = null;

  // ========== EXERCISE 8: Working with Nullable Fields ==========

  // Charlie has null email
  static Optional<User> charlie = findUserByName("charlie");

  // TODO: Get Charlie's email safely
  // Problem: charlie.get().getEmail() will give null, not throw exception
  // Solution: Use Optional.ofNullable() on the email field
  static String charlieEmail = null;
  // Hint: charlie.map(User::getEmail).orElse("no-email@example.com")
  // But this still gives null! Need: Optional.ofNullable(charlie.get().getEmail())

  // TODO: BETTER - Create a method that returns Optional<String> for email
  // Modify User class or create helper method

  // ========== EXERCISE 9: Multiple Fallbacks with or() (Java 9+) ==========

  static Optional<String> primary = Optional.empty();
  static Optional<String> secondary = Optional.of("Secondary");
  static Optional<String> tertiary = Optional.of("Tertiary");

  // TODO: Try primary, then secondary, then tertiary
  // Hint: primary.or(() -> secondary).or(() -> tertiary).orElse("None")
  static String fallbackResult = null;

  // ========== EXERCISE 10: Common Mistakes to Avoid ==========

  // MISTAKE 1: Returning null instead of Optional.empty()
  static Optional<String> findSomething(boolean exists) {
    if (!exists) {
      // TODO: Return Optional.empty(), NOT null!
      return null; // Fix this!
    }
    return Optional.of("Found");
  }

  // MISTAKE 2: Using Optional.of() with nullable value
  static String getNullableValue() {
    return null;
  }

  static void mistake2() {
    // TODO: This will crash! Use Optional.ofNullable() instead
    // Optional<String> opt = Optional.of(getNullableValue()); // CRASHES
    Optional<String> opt = null; // Fix this
  }

  // MISTAKE 3: Using isPresent() + get() pattern
  static void mistake3(Optional<String> opt) {
    // TODO: Rewrite this using ifPresent() instead
    if (opt.isPresent()) {
      String value = opt.get();
      System.out.println(value.toUpperCase());
    }

    // Your better version here:
  }

  // ========== USAGE EXAMPLES (Uncomment after implementing) ======

  public static void main(String[] args) {
    /*
     * System.out.println("=== EXERCISE 1: Creating Optionals ==="); System.out.println("name1: " +
     * name1); System.out.println("name2: " + name2); System.out.println("emptyOptional: " +
     * emptyOptional);
     * 
     * System.out.println("\n=== EXERCISE 2: Checking Values ===");
     * System.out.println("john is present: " + johnPresent); System.out.println("empty is empty: "
     * + emptyIsEmpty);
     * 
     * System.out.println("\n=== EXERCISE 3: Getting Values Safely ===");
     * System.out.println("cityName: " + cityName); System.out.println("noCityName: " + noCityName);
     * System.out.println("cityOrThrow: " + cityOrThrow); //
     * System.out.println("noCity.orElseThrow(): " + noCity.orElseThrow()); // Uncomment to see
     * exception
     * 
     * System.out.println("\n=== EXERCISE 4: orElse vs orElseGet ==="); expensiveMethodCallCount =
     * 0; System.out.println("result1: " + result1); System.out.println("Expensive method called " +
     * expensiveMethodCallCount + " time(s)");
     * 
     * expensiveMethodCallCount = 0; System.out.println("result2: " + result2);
     * System.out.println("Expensive method called " + expensiveMethodCallCount + " time(s)");
     * 
     * System.out.println("\n=== EXERCISE 5: ifPresent ==="); printAliceEmail(); findDavid();
     * 
     * System.out.println("\n=== EXERCISE 6: Avoiding get() ==="); System.out.println("age1: " +
     * age1); System.out.println("age2: " + age2);
     * 
     * System.out.println("\n=== EXERCISE 7: Database Query ===");
     * System.out.println("Alice email: " + aliceEmail); System.out.println("Bob age: " + bobAge);
     * System.out.println("Zack username: " + zackUsername);
     * 
     * System.out.println("\n=== EXERCISE 8: Nullable Fields ===");
     * System.out.println("Charlie email: " + charlieEmail);
     * 
     * System.out.println("\n=== EXERCISE 9: Multiple Fallbacks ===");
     * System.out.println("Fallback result: " + fallbackResult);
     * 
     * System.out.println("\n=== EXERCISE 10: Common Mistakes ===");
     * System.out.println("findSomething(true): " + findSomething(true));
     * System.out.println("findSomething(false): " + findSomething(false)); mistake2();
     * mistake3(Optional.of("hello"));
     */
  }
}
