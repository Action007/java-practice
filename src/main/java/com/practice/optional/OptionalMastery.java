package com.practice.optional;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalMastery {

  static class User {
    String username;
    String email;
    Integer age;
    Address address; // Can be null

    public User(String username, String email, Integer age, Address address) {
      this.username = username;
      this.email = email;
      this.age = age;
      this.address = address;
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

    public Address getAddress() {
      return address;
    }

    @Override
    public String toString() {
      return String.format("User{username='%s', email='%s', age=%d}", username, email, age);
    }
  }

  static class Address {
    String city;
    String zipCode; // Can be null

    public Address(String city, String zipCode) {
      this.city = city;
      this.zipCode = zipCode;
    }

    public String getCity() {
      return city;
    }

    public String getZipCode() {
      return zipCode;
    }
  }

  static class Order {
    String id;
    User user;
    Double discount; // Can be null

    public Order(String id, User user, Double discount) {
      this.id = id;
      this.user = user;
      this.discount = discount;
    }

    public String getId() {
      return id;
    }

    public User getUser() {
      return user;
    }

    public Double getDiscount() {
      return discount;
    }
  }

  // ========== PART 1: BASIC OPTIONAL OPERATIONS (30 min) ==========

  // TODO 1.1: Create an Optional with value "John"
  static Optional<String> name1 = Optional.of("John");

  // TODO 1.2: Create an Optional from a nullable string
  static String nullableString = null;
  static Optional<String> name2 = Optional.ofNullable(nullableString);

  // TODO 1.3: Create an empty Optional
  static Optional<String> emptyOptional = Optional.empty();

  // TODO 1.4: Check if 'john' has a value (should be true)
  static Optional<String> john = Optional.of("John");
  static boolean johnPresent = john.isPresent();

  // TODO 1.5: Check if 'empty' is empty (should be true)
  static Optional<String> empty = Optional.empty();
  static boolean emptyIsEmpty = empty.isEmpty();

  // TODO 1.6: Get city value, or "Unknown" if empty
  static Optional<String> city = Optional.of("New York");
  static String cityName = city.orElse("Unknown");

  // TODO 1.7: Get noCity value, or "Unknown" if empty
  static Optional<String> noCity = Optional.empty();
  static String noCityName = noCity.orElse("Unknown");

  // ========== PART 2: MAP AND FLATMAP (45 min) - THE KEY SKILLS ==========

  static List<User> users =
      Arrays.asList(new User("alice", "alice@example.com", 25, new Address("NYC", "10001")),
          new User("bob", "bob@example.com", 30, new Address("LA", null)),
          new User("charlie", null, 35, null));

  // TODO 2.1: Find user "alice" and get her email in UPPERCASE
  // Hint: findUserByName("alice").map(User::getEmail).map(String::toUpperCase).orElse("NO EMAIL")
  static String aliceEmailUpper =
      findUserByName("alice").map(User::getEmail).map(String::toUpperCase).orElse("NO EMAIL");

  // TODO 2.2: Find user "alice" and get her city name
  // Problem: user.getAddress() might be null, and address.getCity() might be null
  // Hint: Use map() twice, then orElse("Unknown City")
  // This WON'T work: findUserByName("alice").map(User::getAddress).map(Address::getCity)
  // Why? Because first map returns Optional<Address>, second map expects Address but gets
  // Optional<Address>
  // Solution: Use flatMap for the first transformation if getAddress returns Optional
  // OR use map then check for null
  static String aliceCity =
      findUserByName("alice").flatMap(user -> Optional.ofNullable(user.getAddress()))
          .map(Address::getCity).orElse("Unknown City");

  // TODO 2.3: Find user "bob" and get his zip code
  // Challenge: User might not exist, address might be null, zipCode might be null
  // Hint: Chain map() calls and use orElse("NO ZIP")
  static String bobZip =
      findUserByName("bob").map(User::getAddress).map(Address::getZipCode).orElse("NO ZIP");

  // TODO 2.4: Find user "charlie" (has null address) and get his city
  // This should return "NO CITY" because address is null
  static String charlieCity =
      findUserByName("charlie").map(User::getAddress).map(Address::getCity).orElse("NO CITY");

  // TODO 2.5: Create a method that safely gets a user's city
  // Return Optional<String> so callers can chain further
  static Optional<String> getUserCity(String username) {
    // TODO: Implement this
    // Hint: findUserByName -> map to address -> map to city, all wrapped in Optional
    return findUserByName(username).map(User::getAddress).map(Address::getCity);
  }

  // TODO 2.6: Use the method above to get alice's city or "Unknown"
  static String aliceCityViaMethod = getUserCity("alice").orElse("Unknown");

  // TODO 2.7: FlatMap example - Find user and return Optional<Address>
  // If we have: Optional<User> and User.getAddress() returns Address (nullable)
  // We need: Optional<Address>
  // Problem: .map(User::getAddress) gives Optional<Address> where Address might be null
  // Solution: Create a helper that returns Optional<Address>
  static Optional<Address> getAddressForUser(String username) {
    // TODO: Return Optional<Address> (empty if user not found or address is null)
    return findUserByName(username).map(User::getAddress);
  }

  // TODO 2.8: Now use flatMap to get city from that address
  // getUserAddress("alice").flatMap(address -> Optional.ofNullable(address.getCity()))
  static String aliceCityViaFlatMap = null;

  // ========== PART 3: COMBINING OPTIONALS (20 min) ==========

  static List<Order> orders = Arrays.asList(new Order("O1", users.get(0), 10.0),
      new Order("O2", users.get(1), null), new Order("O3", users.get(2), 15.0));

  // TODO 3.1: Find order "O1" and get the user's email
  // Hint: findOrderById("O1").map(Order::getUser).map(User::getEmail).orElse("NO EMAIL")
  static String order1UserEmail = null;

  // TODO 3.2: Find order "O2" and calculate final price after discount
  // Order has getDiscount() that returns Double (nullable)
  // Calculate: basePrice - discount, or just basePrice if no discount
  // Assume base price is always 100.0
  static double order2FinalPrice = 0.0;
  // Hint: findOrderById("O2").map(Order::getDiscount).map(d -> 100.0 - d).orElse(100.0)

  // TODO 3.3: Get discount from order "O2" or order "O3" (first non-empty)
  // Hint: Use .or() method (Java 9+)
  // findOrderById("O2").map(Order::getDiscount).or(() ->
  // findOrderById("O3").map(Order::getDiscount))
  static Optional<Double> firstAvailableDiscount = null;

  // TODO 3.4: Check if BOTH order "O1" has a discount AND user "alice" has an address
  // Return true only if both conditions are met
  // Hint: Combine two Optionals with map
  static boolean bothPresent = false;

  // ========== PART 4: STREAM OF OPTIONALS (15 min) ==========

  static List<String> usernames = Arrays.asList("alice", "bob", "zack", "charlie");

  // TODO 4.1: Find all users that exist and collect their emails
  // Some usernames don't exist (like "zack"), skip those
  // Hint:
  // usernames.stream().map(this::findUserByName).filter(Optional::isPresent).map(Optional::get).map(User::getEmail)
  // BETTER: Use flatMap with Optional::stream (Java 9+)
  static List<String> existingUserEmails = null;

  // TODO 4.2: Get all cities where users live (skip users with no address)
  // Hint: users.stream().map(User::getAddress).filter(Objects::nonNull).map(Address::getCity)
  // OR: users.stream().map(u ->
  // Optional.ofNullable(u.getAddress())).flatMap(Optional::stream).map(Address::getCity)
  static List<String> allCities = null;

  // TODO 4.3: Find the first user over age 28, return their username or "NONE"
  static String firstOver28 = null;

  // ========== PART 5: ADVANCED PATTERNS (15 min) ==========

  // TODO 5.1: ifPresentOrElse - Print alice's email, or print "No email found"
  static void printAliceEmail() {
    // TODO: findUserByName("alice").map(User::getEmail).ifPresentOrElse(...)
  }

  // TODO 5.2: orElseThrow with custom exception
  // Get alice's age, or throw IllegalStateException if not found
  static int getAliceAgeOrThrow() {
    // TODO: Implement
    return 0;
  }

  // TODO 5.3: filter - Find user "alice" only if she's over 20 years old
  static Optional<User> aliceIfOver20 = null;
  // Hint: findUserByName("alice").filter(u -> u.getAge() > 20)

  // TODO 5.4: Complex chaining - Get alice's zip code in uppercase, or "NO ZIP"
  // Chain: find user -> get address -> get zip -> uppercase
  static String aliceZipUpper = null;

  // TODO 5.5: or() method - Try to find "zack", if not found try "alice"
  static Optional<User> zackOrAlice = null;
  // Hint: findUserByName("zack").or(() -> findUserByName("alice"))

  // ========== PART 6: COMMON MISTAKES TO AVOID (10 min) ==========

  // MISTAKE 1: Using isPresent() + get() instead of map/orElse
  static void printUserEmailBad(String username) {
    Optional<User> user = findUserByName(username);
    if (user.isPresent()) {
      User u = user.get();
      if (u.getEmail() != null) {
        System.out.println(u.getEmail().toUpperCase());
      }
    }
  }

  // TODO 6.1: Rewrite the above using map and ifPresent
  static void printUserEmailGood(String username) {
    // TODO: Implement using Optional methods only
  }

  // MISTAKE 2: Returning null from a method that should return Optional
  static Optional<User> findUserByNameBad(String name) {
    User found = users.stream().filter(u -> u.getUsername().equals(name)).findFirst().orElse(null);
    if (found == null) {
      return null; // BAD! Should return Optional.empty()
    }
    return Optional.of(found);
  }

  // TODO 6.2: Fix the above method
  static Optional<User> findUserByNameGood(String name) {
    // TODO: Implement correctly
    return null;
  }

  // MISTAKE 3: Using Optional.of() with nullable value
  static void mistake3() {
    String maybeNull = getNullableValue();
    // Optional<String> opt = Optional.of(maybeNull); // CRASHES if maybeNull is null!
    // TODO: Fix this
    Optional<String> opt = null;
  }

  // ========== HELPER METHODS ==========

  static Optional<User> findUserByName(String name) {
    return users.stream().filter(u -> u.getUsername().equals(name)).findFirst();
  }

  static Optional<Order> findOrderById(String id) {
    return orders.stream().filter(o -> o.getId().equals(id)).findFirst();
  }

  static String getNullableValue() {
    return null;
  }

  // ========== TEST YOUR SOLUTIONS ==========

  public static void main(String[] args) {
    System.out.println("=== PART 1: BASIC OPERATIONS ===");
    System.out.println("name1: " + name1);
    System.out.println("name2: " + name2);
    System.out.println("emptyOptional: " + emptyOptional);
    System.out.println("john is present: " + johnPresent);
    System.out.println("empty is empty: " + emptyIsEmpty);
    System.out.println("cityName: " + cityName);
    System.out.println("noCityName: " + noCityName);


    System.out.println("\n=== PART 2: MAP AND FLATMAP ===");
    System.out.println("Alice email upper: " + aliceEmailUpper);
    System.out.println("Alice city: " + aliceCity);
    System.out.println("Bob zip: " + bobZip);

    /*
     * System.out.println("Charlie city: " + charlieCity);
     * System.out.println("Alice city via method: " + aliceCityViaMethod);
     * System.out.println("Alice city via flatMap: " + aliceCityViaFlatMap);
     */

    System.out.println("\n=== PART 3: COMBINING OPTIONALS ===");
    // Uncomment after implementing
    /*
     * System.out.println("Order 1 user email: " + order1UserEmail);
     * System.out.println("Order 2 final price: " + order2FinalPrice);
     * System.out.println("First available discount: " + firstAvailableDiscount);
     * System.out.println("Both present: " + bothPresent);
     */

    System.out.println("\n=== PART 4: STREAM OF OPTIONALS ===");
    // Uncomment after implementing
    /*
     * System.out.println("Existing user emails: " + existingUserEmails);
     * System.out.println("All cities: " + allCities); System.out.println("First over 28: " +
     * firstOver28);
     */

    System.out.println("\n=== PART 5: ADVANCED PATTERNS ===");
    // Uncomment after implementing
    /*
     * printAliceEmail(); System.out.println("Alice age: " + getAliceAgeOrThrow());
     * System.out.println("Alice if over 20: " + aliceIfOver20);
     * System.out.println("Alice zip upper: " + aliceZipUpper); System.out.println("Zack or Alice: "
     * + zackOrAlice);
     */

    System.out.println("\n=== PART 6: AVOIDING MISTAKES ===");
    // Uncomment after implementing
    /*
     * printUserEmailGood("alice"); System.out.println("Find alice (good): " +
     * findUserByNameGood("alice")); System.out.println("Find zack (good): " +
     * findUserByNameGood("zack"));
     */
  }
}
