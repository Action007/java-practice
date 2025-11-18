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
  public static <T> Optional<T> findFirst(List<T> list, Predicate<T> predicate) {
    for (T item : list) {
      if (predicate.test(item)) {
        return Optional.of(item);
      }
    }
    return Optional.empty();
  }

  // TODO 1.2: Fill in the generic type parameters (THREE type parameters!)
  // Create a Map from a list using key and value extractors
  public static <T, K, V> Map<K, V> toMap(List<T> list, Function<T, K> keyExtractor,
      Function<T, V> valueExtractor) {
    Map<K, V> result = new HashMap<>();
    for (T item : list) {
      result.put(keyExtractor.apply(item), valueExtractor.apply(item));
    }
    return result;
  }

  // TODO 1.3: Fill in generic with UPPER BOUND
  // Sum all numbers (must extend Number)
  public static <T extends Number> double sumAll(List<T> numbers) {
    double sum = 0;
    for (T num : numbers) {
      sum += num.doubleValue();
    }
    return sum;
  }

  // TODO 1.4: Generic method with MULTIPLE bounds
  // Find max element that is both Comparable AND has a specific interface
  // Hint: <T extends Comparable<T> & SomeInterface>
  public static <T extends Comparable<T>> T findMax(List<T> list) {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List is empty");
    }
    T max = list.get(0);
    for (T item : list) {
      if (item.compareTo(max) > 0) {
        max = item;
      }
    }
    return max;
  }

  // TODO 1.5: Generic method that returns a List of the same type
  // Filter a list based on predicate
  public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    List<T> result = new ArrayList<>();
    for (T item : list) {
      if (predicate.test(item)) {
        result.add(item);
      }
    }
    return result;
  }

  // ========== PART 2: WILDCARDS - READING (? extends) (15 min) ==========

  // TODO 2.1: Method that prints any list (we only READ from it)
  // Use ? wildcard
  public static void printAll(List<?> list) {
    for (Object item : list) {
      System.out.println(item);
    }
  }

  // TODO 2.2: Method that sums numbers from any Number subtype list
  // Use ? extends Number (we READ numbers)
  public static double sumNumbers(List<? extends Number> numbers) {
    double sum = 0;
    for (Number num : numbers) {
      sum += num.doubleValue();
    }
    return sum;
  }

  // TODO 2.3: Find max in any Comparable list
  // Use ? extends Comparable
  public static <T extends Comparable<? super T>> T findMaxWildcard(List<T> list) {
    // This is the CORRECT signature for findMax with wildcards
    // TODO: Implement (similar to 1.4)
    return null;
  }

  // TODO 2.4: Why can't we ADD to a "List<? extends Number>"?
  // Uncomment and try to fix:
  /*
   * public static void addNumber(List<? extends Number> numbers) { numbers.add(10); // COMPILE
   * ERROR! Why? numbers.add(10.5); // COMPILE ERROR! Why? // Answer: We don't know the EXACT type,
   * could be List<Integer>, can't add Double to it }
   */

  // ========== PART 3: WILDCARDS - WRITING (? super) (15 min) ==========

  // TODO 3.1: Copy numbers from source to destination
  // Source: can be any Number subtype (? extends Number) - we READ
  // Destination: must accept at least Number (? super Number) - we WRITE
  public static void copyNumbers(List<? extends Number> source, List<? super Number> destination) {
    for (Number num : source) {
      destination.add(num);
    }
  }

  // TODO 3.2: Add all integers to a collection that accepts Numbers or supertypes
  public static void addIntegers(List<? super Integer> list) {
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
  public static <T> void copy(List<? extends T> source, List<? super T> destination) {
    for (T item : source) {
      destination.add(item);
    }
  }

  // ========== PART 4: GENERIC CLASSES (15 min) ==========

  // TODO 4.1: Create a generic Pair class
  // Should hold two values of potentially different types
  static class Pair<K, V> {
    // TODO: Add fields, constructor, getters
    private K key;
    private V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    @Override
    public String toString() {
      return "(" + key + ", " + value + ")";
    }
  }

  // TODO 4.2: Create a generic Box class with upper bound
  // Box can only hold Numbers or subclasses
  static class Box<T extends Number> {
    // TODO: Implement
    private T value;

    public Box(T value) {
      this.value = value;
    }

    public T getValue() {
      return value;
    }

    public double getDoubleValue() {
      return value.doubleValue();
    }

    // TODO 4.3: Add a method that compares this box with another box
    // Should work with any Number type
    public boolean isGreaterThan(Box<? extends Number> other) {
      // TODO: Compare values
      return false;
    }
  }

  // TODO 4.4: Create a generic Stack
  static class Stack<T> {
    // TODO: Implement push, pop, peek, isEmpty
    private List<T> items = new ArrayList<>();

    public void push(T item) {
      // TODO
    }

    public T pop() {
      // TODO
      return null;
    }

    public T peek() {
      // TODO
      return null;
    }

    public boolean isEmpty() {
      // TODO
      return true;
    }
  }

  // ========== PART 5: ADVANCED GENERIC PATTERNS (15 min) ==========

  // TODO 5.1: Generic method with multiple bounds
  // Element must be Comparable AND Cloneable
  public static <T extends Comparable<T> & Cloneable> T findMaxCloneable(List<T> list) {
    // TODO: Find max and return a clone of it
    // Note: You'll need to cast to Cloneable and call clone()
    return null;
  }

  // TODO 5.2: Recursive type bound (self-referential generics)
  // This is how Comparable is actually defined!
  public static <T extends Comparable<T>> void sort(List<T> list) {
    // TODO: Implement bubble sort or use Collections.sort
  }

  // TODO 5.3: Generic Builder pattern
  // Create a generic builder for any type
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
  static class Cache<K, V> {
    private Map<K, V> map = new HashMap<>();

    public void put(K key, V value) {
      map.put(key, value);
    }

    public Optional<V> get(K key) {
      return Optional.ofNullable(map.get(key));
    }

    // TODO: Add a method that works with subtypes
    // Should accept any key that extends K
    public Optional<V> getWithSubtype(K key) {
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
  public static int sumIntegers(List<? extends Integer> numbers) {
    int sum = 0;
    for (Integer n : numbers) {
      sum += n;
    }
    return sum;
  }

  // TODO 7.2: Which wildcard to use?
  // Scenario: Adding strings to a list
  public static void addStrings(List<? super String> list) {
    list.add("Hello");
    list.add("World");
  }

  // TODO 7.3: Complex PECS scenario
  // Transfer elements from source to destination with transformation
  public static <T, R> void transferAndTransform(List<? extends T> source,
      List<? super R> destination, Function<T, R> transformer) {
    for (T item : source) {
      destination.add(transformer.apply(item));
    }
  }

  // TODO 7.4: PECS with multiple collections
  // Merge two lists into a third
  public static <T> void merge(List<? extends T> list1, List<? extends T> list2,
      List<? super T> result) {
    result.addAll(list1);
    result.addAll(list2);
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
