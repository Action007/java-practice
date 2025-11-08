public class GenericsMethodReferenceLab {

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

  // ========== EXERCISE 1: Simple Generic Method ==========
  // TODO: Fill in the generic type parameters
  // This method should find the first element in a list that matches a predicate
  // Return Optional.empty() if not found

  public static <???> Optional<???> findFirst(List<???> list, Predicate<???> predicate) {
    for (??? item : list) {
      if (predicate.test(item)) {
        return Optional.of(item);
      }
    }
    return Optional.empty();
  }

  // ========== EXERCISE 2: Generic Method with Two Type Parameters ==========
  // TODO: Fill in the generic type parameters
  // This method should create a Map from a list using keyExtractor and valueExtractor functions
  
  public static <???, ???, ???> Map<???, ???> toMap(
      List<???> list,
      Function<???, ???> keyExtractor,
      Function<???, ???> valueExtractor) {
    Map<???, ???> result = new HashMap<>();
    for (??? item : list) {
      result.put(keyExtractor.apply(item), valueExtractor.apply(item));
    }
    return result;
  }

  // ========== EXERCISE 3: Generic Method with Upper Bound ==========
  // TODO: Fill in the generic type parameters
  // This method should sum all numbers in a list
  // Hint: The type must extend Number, and you need to convert to double
  
  public static <???>

  double sumAll(List<???> numbers) {
    double sum = 0;
    for (??? num : numbers) {
      sum += num.doubleValue();
    }
    return sum;
  }

  // ========== EXERCISE 4: Wildcards (Two Parts) ==========

  // PART A: TODO: Fill in the wildcard
  // This method should print any list of any type
  // Hint: Use "? " wildcard since we only READ from the list

  public static void printAll(List<???> list) {
    for (Object item : list) {
      System.out.println(item);
    }
  }

  // PART B: TODO: Fill in the wildcard
  // This method should copy numbers from source to destination
  // Hint: source can be any Number subtype (use "? extends")
  // destination must accept at least Number (use "? super")

  public static void copyNumbers(List<???> source, List<???> destination) {
    for (Number num : source) {
      destination.add(num);
    }
  }

  // ========== TEST YOUR SOLUTIONS ==========
  public static void main(String[] args) {
    List<Product> products = Arrays.asList(new Product("Laptop", 999.99),
        new Product("Mouse", 25.50), new Product("Keyboard", 75.00));

    System.out.println("=== EXERCISE 1: findFirst ===");
    // TODO: Uncomment after solving
    /*
     * Optional<Product> expensive = findFirst(products, p -> p.getPrice() > 100);
     * System.out.println("First expensive product: " + expensive);
     * 
     * Optional<Product> cheap = findFirst(products, p -> p.getPrice() < 20);
     * System.out.println("First cheap product: " + cheap);
     */

    System.out.println("\n=== EXERCISE 2: toMap ===");
    // TODO: Uncomment after solving
    /*
     * Map<String, Double> priceMap = toMap(products, Product::getName, Product::getPrice);
     * System.out.println("Price map: " + priceMap);
     * 
     * List<String> words = Arrays.asList("apple", "banana", "cherry"); Map<String, Integer>
     * lengthMap = toMap(words, w -> w, String::length); System.out.println("Length map: " +
     * lengthMap);
     */

    System.out.println("\n=== EXERCISE 3: sumAll ===");
    // TODO: Uncomment after solving
    /*
     * List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5); System.out.println("Sum of integers: "
     * + sumAll(integers));
     * 
     * List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5); System.out.println("Sum of doubles: " +
     * sumAll(doubles));
     * 
     * List<Double> prices = Arrays.asList(999.99, 25.50, 75.00);
     * System.out.println("Sum of prices: " + sumAll(prices));
     */

    System.out.println("\n=== EXERCISE 4A: printAll (wildcard) ===");
    // TODO: Uncomment after solving
    /*
     * printAll(products); printAll(Arrays.asList(1, 2, 3)); printAll(Arrays.asList("hello",
     * "world"));
     */

    System.out.println("\n=== EXERCISE 4B: copyNumbers (wildcards) ===");
    // TODO: Uncomment after solving
    /*
     * List<Integer> integers2 = Arrays.asList(1, 2, 3); List<Number> numbers = new ArrayList<>();
     * copyNumbers(integers2, numbers); System.out.println("Copied numbers: " + numbers);
     * 
     * List<Double> doubles2 = Arrays.asList(4.5, 5.5); copyNumbers(doubles2, numbers);
     * System.out.println("After copying doubles: " + numbers);
     */
  }
}
