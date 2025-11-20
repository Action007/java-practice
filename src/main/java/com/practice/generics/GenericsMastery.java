package com.practice.generics;

import java.util.*;
import java.util.function.*;

public class GenericsMastery {

  static class Product {
    String name;
    double price;

    public Product(String name, double price) {
      this.name = name;
      this.price = price;
    }

    public String getName() {
      return name;
    }

    public double getPrice() {
      return price;
    }

    @Override
    public String toString() {
      return String.format("%s ($%.2f)", name, price);
    }
  }

  // ========== PART 1: BASIC GENERIC METHODS (20 min) ==========

  // TODO 1.1: Fill in the generic type parameters
  // Find the first element in a list that matches a predicate
  // Hint: What type does the list contain? What does Optional contain?
  public static Optional findFirst(List list, Predicate predicate) {
    for (Object item : list) {
      if (predicate.test(item)) {
        return Optional.of(item);
      }
    }
    return Optional.empty();
  }

  // TODO 1.2: Fill in the generic type parameters (THREE type parameters!)
  // Create a Map from a list using key and value extractors
  // Hint: T = input type, K = key type, V = value type
  public static Map toMap(List list, Function keyExtractor, Function valueExtractor) {
    Map result = new HashMap();
    for (Object item : list) {
      result.put(keyExtractor.apply(item), valueExtractor.apply(item));
    }
    return result;
  }

  // TODO 1.3: Fill in generic with UPPER BOUND
  // Sum all numbers (must extend Number)
  // Hint: <T extends Number>
  public static double sumAll(List numbers) {
    double sum = 0;
    for (Object num : numbers) {
      // FIX: Cast to Number to call doubleValue()
      sum += 0; // TODO: Fix this line
    }
    return sum;
  }

  // TODO 1.4: Generic method with MULTIPLE bounds
  // Find max element that is both Comparable
  // Hint: <T extends Comparable<T>>
  public static Object findMax(List list) {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    Object max = list.get(0);
    for (Object item : list) {
      // TODO: Compare items using compareTo
      // if (item.compareTo(max) > 0) {
      // max = item;
      // }
    }
    return max;
  }

  // TODO 1.5: Generic method that returns a List of the same type
  // Filter a list based on predicate
  // Hint: Use <T> for the type parameter
  public static List filter(List list, Predicate predicate) {
    List result = new ArrayList();
    for (Object item : list) {
      if (predicate.test(item)) {
        result.add(item);
      }
    }
    return result;
  }

  // ========== PART 2: WILDCARDS - READING (? extends) (15 min) ==========

  // TODO 2.1: Method that prints any list (we only READ from it)
  // Use ? wildcard
  // Hint: List<?> means "list of unknown type"
  public static void printAll(List list) {
    for (Object item : list) {
      System.out.println(item);
    }
  }

  // TODO 2.2: Method that sums numbers from any Number subtype list
  // Use ? extends Number (we READ numbers)
  // Hint: This allows List<Integer>, List<Double>, etc.
  public static double sumNumbers(List numbers) {
    double sum = 0;
    for (Object num : numbers) {
      // TODO: Cast to Number and call doubleValue()
      sum += 0; // Fix this
    }
    return sum;
  }

  // TODO 2.3: Find max in any Comparable list
  // Use ? extends Comparable with wildcard capture
  // Hint: <T extends Comparable<? super T>> T findMaxWildcard(List<T> list)
  public static Object findMaxWildcard(List list) {
    // TODO: Implement (similar to 1.4 but with proper wildcard bounds)
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    return null; // TODO: Implement
  }

  // TODO 2.4: Why can't we ADD to a "List<? extends Number>"?
  // Uncomment and try to understand the error:
  /*
   * public static void addNumber(List<? extends Number> numbers) { numbers.add(10); // COMPILE
   * ERROR! Why? numbers.add(10.5); // COMPILE ERROR! Why? // Answer: We don't know the EXACT type,
   * could be List<Integer>, can't add Double to it }
   */

  // ========== PART 3: WILDCARDS - WRITING (? super) (15 min) ==========

  // TODO 3.1: Copy numbers from source to destination
  // Source: can be any Number subtype (? extends Number) - we READ
  // Destination: must accept at least Number (? super Number) - we WRITE
  public static void copyNumbers(List source, List destination) {
    // TODO: Iterate through source and add to destination
  }

  // TODO 3.2: Add all integers to a collection that accepts Numbers or supertypes
  // Hint: Use ? super Integer
  public static void addIntegers(List list) {
    list.add(1);
    list.add(2);
    list.add(3);
  }

  // TODO 3.3: Why does this work?
  // List<Object> objects = new ArrayList<>();
  // List<Number> numbers = new ArrayList<>();
  // addIntegers(objects); // WORKS!
  // addIntegers(numbers); // WORKS!
  // Explain: ? super Integer accepts Integer, Number, Object

  // TODO 3.4: PECS Principle Exercise
  // Fill in the wildcards for a generic copy method
  // PECS = Producer Extends, Consumer Super
  // Hint: source is producer (extends), destination is consumer (super)
  public static void copy(List source, List destination) {
    // TODO: Implement the copy logic
  }

  // ========== PART 4: GENERIC CLASSES (15 min) ==========

  // TODO 4.1: Create a generic Pair class
  // Should hold two values of potentially different types
  // Hint: Use two type parameters <K, V>
  static class Pair {
    // TODO: Add private fields for key and value

    // TODO: Add constructor

    // TODO: Add getters

    @Override
    public String toString() {
      return "(" + "key" + ", " + "value" + ")"; // TODO: Fix
    }
  }

  // TODO 4.2: Create a generic Box class with upper bound
  // Box can only hold Numbers or subclasses
  // Hint: class Box<T extends Number>
  static class Box {
    // TODO: Add private field for value

    // TODO: Add constructor

    // TODO: Add getValue() method

    // TODO: Add getDoubleValue() method that returns value.doubleValue()

    // TODO 4.3: Add a method that compares this box with another box
    // Should work with any Number type
    // Hint: Use wildcard Box<? extends Number>
    public boolean isGreaterThan(Box other) {
      // TODO: Compare this.value.doubleValue() with other's value
      return false;
    }
  }

  // TODO 4.4: Create a generic Stack
  // Hint: Use List<T> internally for storage
  static class Stack {
    private List items = new ArrayList(); // TODO: Make this generic

    public void push(Object item) {
      // TODO: Implement push
    }

    public Object pop() {
      // TODO: Implement pop (remove and return last item)
      // Check if empty first!
      return null;
    }

    public Object peek() {
      // TODO: Implement peek (return last item without removing)
      // Check if empty first!
      return null;
    }

    public boolean isEmpty() {
      // TODO: Implement isEmpty
      return true;
    }
  }

  // ========== PART 5: ADVANCED GENERIC PATTERNS (15 min) ==========

  // TODO 5.1: Generic method with multiple bounds
  // Element must be Comparable AND Cloneable
  // Hint: <T extends Comparable<T> & Cloneable>
  public static Object findMaxCloneable(List list) {
    // TODO: Find max and return a clone of it
    // Note: You'll need to cast to Cloneable and call clone()
    // clone() returns Object, so cast back to T
    return null;
  }

  // TODO 5.2: Recursive type bound (self-referential generics)
  // This is how Comparable is actually defined!
  // Hint: <T extends Comparable<T>>
  public static void sort(List list) {
    // TODO: Implement bubble sort or use Collections.sort
    // Collections.sort(list); is the easy way!
  }

  // TODO 5.3: Generic Builder pattern
  // Create a generic builder for any type
  // This is already implemented as an example - study it!
  static class Builder<T> {
    private Supplier<T> constructor;
    private List<Consumer<T>> operations = new ArrayList<>();

    public Builder(Supplier<T> constructor) {
      this.constructor = constructor;
    }

    public Builder<T> with(Consumer<T> operation) {
      operations.add(operation);
      return this;
    }

    public T build() {
      T instance = constructor.get();
      operations.forEach(op -> op.accept(instance));
      return instance;
    }
  }

  // TODO 5.4: Generic Cache with bounded types
  static class Cache {
    private Map map = new HashMap(); // TODO: Make generic <K, V>

    public void put(Object key, Object value) {
      map.put(key, value);
    }

    public Optional get(Object key) {
      return Optional.ofNullable(map.get(key));
    }

    // TODO: Add a method that works with subtypes
    // Should accept any key of type K
    public Optional getWithSubtype(Object key) {
      return get(key);
    }
  }

  // ========== PART 6: TYPE ERASURE GOTCHAS (10 min) ==========

  // TODO 6.1: Why does this NOT compile?
  /*
   * public static <T> void printArray(T[] array) { // T[] newArray = new T[10]; // COMPILE ERROR!
   * // Why? Type erasure removes T at runtime, can't create generic arrays }
   */

  // TODO 6.2: Why can't we do this?
  /*
   * class GenericArray<T> { private T[] array;
   * 
   * public GenericArray(int size) { // array = new T[size]; // COMPILE ERROR! // Workaround: array
   * = (T[]) new Object[size]; } }
   */

  // TODO 6.3: Overloading doesn't work with generics after erasure
  /*
   * class Overload { public void process(List<String> list) { } public void process(List<Integer>
   * list) { } // COMPILE ERROR! // Why? Both become process(List) after erasure }
   */

  // ========== PART 7: PECS PRINCIPLE PRACTICE (10 min) ==========

  // TODO 7.1: Which wildcard to use?
  // Scenario: Reading from a list of Integers to sum them
  // Hint: We're reading (producing) values, use extends
  public static int sumIntegers(List numbers) {
    int sum = 0;
    for (Object n : numbers) {
      // TODO: Cast to Integer
      sum += 0; // Fix this
    }
    return sum;
  }

  // TODO 7.2: Which wildcard to use?
  // Scenario: Adding strings to a list
  // Hint: We're writing (consuming), use super
  public static void addStrings(List list) {
    list.add("Hello");
    list.add("World");
  }

  // TODO 7.3: Complex PECS scenario
  // Transfer elements from source to destination with transformation
  // Hint: source produces T (extends), destination consumes R (super)
  public static void transferAndTransform(List source, List destination, Function transformer) {
    // TODO: Implement - read from source, transform, add to destination
  }

  // TODO 7.4: PECS with multiple collections
  // Merge two lists into a third
  // Hint: Both sources produce (extends), result consumes (super)
  public static void merge(List list1, List list2, List result) {
    // TODO: Add all from list1 and list2 to result
  }

  // ========== TEST YOUR SOLUTIONS ==========

  public static void main(String[] args) {
    List<Product> products = Arrays.asList(new Product("Laptop", 999.99),
        new Product("Mouse", 25.50), new Product("Keyboard", 75.00));

    System.out.println("=== PART 1: BASIC GENERIC METHODS ===");
    // Uncomment after implementing
    /*
     * Optional<Product> expensive = findFirst(products, p -> p.getPrice() > 100);
     * System.out.println("1.1 First expensive: " + expensive);
     * 
     * Map<String, Double> priceMap = toMap(products, Product::getName, Product::getPrice);
     * System.out.println("1.2 Price map: " + priceMap);
     * 
     * List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5); System.out.println("1.3 Sum: " +
     * sumAll(integers));
     * 
     * System.out.println("1.4 Max integer: " + findMax(integers));
     * 
     * List<Product> filtered = filter(products, p -> p.getPrice() < 100);
     * System.out.println("1.5 Filtered: " + filtered);
     */

    System.out.println("\n=== PART 2: WILDCARDS - READING ===");
    // Uncomment after implementing
    /*
     * printAll(products); printAll(Arrays.asList(1, 2, 3));
     * 
     * List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5); System.out.println("2.2 Sum: " +
     * sumNumbers(doubles)); System.out.println("2.2 Sum integers: " + sumNumbers(integers));
     */

    System.out.println("\n=== PART 3: WILDCARDS - WRITING ===");
    // Uncomment after implementing
    /*
     * List<Number> numbers = new ArrayList<>(); copyNumbers(integers, numbers);
     * copyNumbers(doubles, numbers); System.out.println("3.1 Copied numbers: " + numbers);
     * 
     * List<Object> objects = new ArrayList<>(); addIntegers(objects);
     * System.out.println("3.2 Objects with integers: " + objects);
     * 
     * List<String> strings = Arrays.asList("a", "b", "c"); List<Object> objDest = new
     * ArrayList<>(); copy(strings, objDest); System.out.println("3.4 Copied strings: " + objDest);
     */

    System.out.println("\n=== PART 4: GENERIC CLASSES ===");
    // Uncomment after implementing
    /*
     * Pair<String, Integer> pair = new Pair<>("Age", 25); System.out.println("4.1 Pair: " + pair);
     * 
     * Box<Integer> box1 = new Box<>(100); Box<Double> box2 = new Box<>(50.5);
     * System.out.println("4.2 Box1 value: " + box1.getDoubleValue());
     * System.out.println("4.3 Box1 > Box2: " + box1.isGreaterThan(box2));
     * 
     * Stack<String> stack = new Stack<>(); stack.push("First"); stack.push("Second");
     * System.out.println("4.4 Pop: " + stack.pop()); System.out.println("4.4 Peek: " +
     * stack.peek());
     */

    System.out.println("\n=== PART 7: PECS PRACTICE ===");
    // Uncomment after implementing
    /*
     * System.out.println("7.1 Sum: " + sumIntegers(integers));
     * 
     * List<Object> objList = new ArrayList<>(); addStrings(objList);
     * System.out.println("7.2 Strings added: " + objList);
     * 
     * List<String> names = Arrays.asList("Alice", "Bob"); List<Object> objResult = new
     * ArrayList<>(); transferAndTransform(names, objResult, String::toUpperCase);
     * System.out.println("7.3 Transformed: " + objResult);
     * 
     * List<Object> merged = new ArrayList<>(); merge(integers, doubles, merged);
     * System.out.println("7.4 Merged: " + merged);
     */
  }
}
