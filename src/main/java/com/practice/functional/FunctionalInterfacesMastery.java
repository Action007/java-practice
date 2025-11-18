package com.practice.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfacesMastery {

  static class Product {
    String name;
    double price;
    int category;
    boolean inStock;

    public Product(String name, double price, int category, boolean inStock) {
      this.name = name;
      this.price = price;
      this.category = category;
      this.inStock = inStock;
    }

    public String getName() {
      return name;
    }

    public double getPrice() {
      return price;
    }

    public int getCategory() {
      return category;
    }

    public boolean isInStock() {
      return inStock;
    }

    @Override
    public String toString() {
      return String.format("%s ($%.2f, cat:%d, %s)", name, price, category,
          inStock ? "in-stock" : "out-of-stock");
    }
  }

  // ========== PART 1: BASIC FUNCTIONAL INTERFACES (15 min) ==========

  static List<Product> products =
      Arrays.asList(new Product("Laptop", 999.99, 1, true), new Product("Mouse", 25.50, 1, true),
          new Product("Coffee", 4.99, 2, false), new Product("Book", 15.99, 3, true),
          new Product("Headphones", 149.99, 1, true), new Product("Desk", 299.99, 4, false));

  // TODO 1.1: Create a Supplier that returns a default "Unknown Product"
  static Supplier<Product> unknownProductFactory = null;

  // TODO 1.2: Create a Consumer that prints product name and price
  static Consumer<Product> productPrinter = null;

  // TODO 1.3: Create a Predicate that returns true if product price > $50
  static Predicate<Product> isExpensive = null;

  // TODO 1.4: Create a Function that extracts product name
  static Function<Product, String> toName = null;

  // TODO 1.5: Create a UnaryOperator that applies 10% discount to a price
  static UnaryOperator<Double> applyDiscount = null;

  // TODO 1.6: Create a BiConsumer that prints a label and product together
  static BiConsumer<String, Product> labeledPrinter = null;

  // TODO 1.7: Create a BiFunction that creates a product with category 99
  static BiFunction<String, Double, Product> productCreator = null;

  // TODO 1.8: Create an IntPredicate that checks if category is 1 (electronics)
  static IntPredicate isElectronics = null;

  // ========== PART 2: FUNCTION COMPOSITION (15 min) ==========

  // TODO 2.1: Create two functions and chain them with andThen
  // Function 1: Extract product name
  // Function 2: Convert to uppercase
  // Chain them: extractName.andThen(toUpperCase)
  static Function<Product, String> extractName = null;
  static Function<String, String> toUpperCase = null;
  static Function<Product, String> nameToUpper = null; // Chained result

  // TODO 2.2: Create function chain with compose (reverse order)
  // Function 1: Add "Product: " prefix to a string
  // Function 2: Extract product name
  // Chain: addPrefix.compose(extractName) - extracts name THEN adds prefix
  static Function<String, String> addPrefix = null;
  static Function<Product, String> extractThenPrefix = null; // Chained result

  // TODO 2.3: Create a multi-stage function pipeline
  // Product -> extract price -> apply discount -> format as "$XX.XX"
  static Function<Product, Double> extractPrice = null;
  static Function<Double, Double> discount10Percent = null;
  static Function<Double, String> formatCurrency = null;
  static Function<Product, String> pricePipeline = null; // Chain all three

  // TODO 2.4: Use andThen to chain multiple transformations
  // Start with price -> apply 10% discount -> apply additional 5% discount -> round to int
  static UnaryOperator<Double> discount10 = null;
  static UnaryOperator<Double> discount5 = null;
  static Function<Double, Integer> roundToInt = null;
  static Function<Double, Integer> fullDiscountPipeline = null;

  // ========== PART 3: PREDICATE COMPOSITION (15 min) ==========

  // TODO 3.1: Combine predicates with AND
  // Predicate 1: price > 50
  // Predicate 2: inStock == true
  // Combined: expensive AND in stock
  static Predicate<Product> expensive = null;
  static Predicate<Product> inStock = null;
  static Predicate<Product> expensiveAndInStock = null;

  // TODO 3.2: Combine predicates with OR
  // Either price < 10 OR category == 2
  static Predicate<Product> cheap = null;
  static Predicate<Product> categoryFood = null;
  static Predicate<Product> cheapOrFood = null;

  // TODO 3.3: Negate a predicate
  // Get all products that are NOT in stock
  static Predicate<Product> notInStock = null;

  // TODO 3.4: Complex predicate combination
  // (expensive AND inStock) OR (cheap AND category==1)
  static Predicate<Product> complexFilter = null;

  // TODO 3.5: Create a reusable predicate builder
  // Method that returns a predicate for price range
  static Predicate<Product> priceInRange(double min, double max) {
    // TODO: Return predicate that checks if product.getPrice() is between min and max
    return null;
  }

  // TODO 3.6: Chain predicate with stream filter
  // Find all products that are: expensive AND in stock, collect names
  static List<String> expensiveInStockNames = null;

  // ========== PART 4: METHOD REFERENCES (10 min) ==========

  // TODO 4.1: Convert lambda to static method reference
  // Lambda: p -> ProductUtils.isAffordable(p)
  // Method reference: ???
  static Predicate<Product> affordableLambda = p -> ProductUtils.isAffordable(p);
  static Predicate<Product> affordableMethodRef = null; // Convert to method reference

  // TODO 4.2: Convert lambda to instance method reference
  // Lambda: s -> s.toUpperCase()
  // Method reference: ???
  static Function<String, String> upperLambda = s -> s.toUpperCase();
  static Function<String, String> upperMethodRef = null;

  // TODO 4.3: Convert lambda to constructor reference
  // Lambda: (name, price) -> new Product(name, price, 0, true)
  // This is tricky - you might need a wrapper or custom functional interface
  static BiFunction<String, Double, Product> productLambda =
      (name, price) -> new Product(name, price, 0, true);
  // TODO: Can you use Product::new here? Hint: You might need a different approach

  // TODO 4.4: Convert these lambdas to method references where possible
  // a) products.stream().map(p -> p.getName()) -> ???
  // b) products.stream().filter(p -> p.isInStock()) -> ???
  // c) products.stream().mapToDouble(p -> p.getPrice()) -> ???
  List<String> namesLambda = products.stream().map(p -> p.getName()).collect(Collectors.toList());
  List<String> namesMethodRef = null; // Convert to method reference

  List<Product> inStockLambda =
      products.stream().filter(p -> p.isInStock()).collect(Collectors.toList());
  List<Product> inStockMethodRef = null; // Convert to method reference

  // ========== PART 5: CUSTOM FUNCTIONAL INTERFACES (10 min) ==========

  // TODO 5.1: Create a custom functional interface for validation
  // @FunctionalInterface
  // interface Validator<T> {
  // boolean validate(T item);
  // }

  // Then create validators:
  // Validator<Product> priceValidator = p -> p.getPrice() > 0;
  // Validator<Product> nameValidator = p -> p.getName() != null && !p.getName().isEmpty();

  // TODO 5.2: Create a custom functional interface with multiple methods (one abstract)
  // @FunctionalInterface
  // interface ProductProcessor {
  // Product process(Product p);
  // default Product processWithLogging(Product p) {
  // System.out.println("Processing: " + p.getName());
  // return process(p);
  // }
  // }

  // TODO 5.3: Create a TriFunction (3 parameters)
  // @FunctionalInterface
  // interface TriFunction<T, U, V, R> {
  // R apply(T t, U u, V v);
  // }

  // Use it to create a Product from 3 parameters
  // TriFunction<String, Double, Integer, Product> productFactory = (name, price, category) -> new
  // Product(name, price, category, true);

  // ========== PART 6: ADVANCED PATTERNS (10 min) ==========

  // TODO 6.1: Function that returns a Function (Higher-order function)
  // Create a function that takes a discount percentage and returns a discount function
  static Function<Double, UnaryOperator<Double>> createDiscounter(double discountPercent) {
    // TODO: Return a UnaryOperator that applies the discount
    // Example: createDiscounter(10.0) returns a function that applies 10% discount
    return null;
  }

  // TODO 6.2: Predicate factory method
  // Create a method that returns different predicates based on criteria
  static Predicate<Product> createFilter(String filterType, double threshold) {
    // TODO: Return different predicates based on filterType
    // "price": p -> p.getPrice() > threshold
    // "stock": p -> p.isInStock()
    // default: p -> true
    return null;
  }

  // TODO 6.3: Compose a validation pipeline
  // Create a list of validators and check if product passes all
  // List<Predicate<Product>> validators = Arrays.asList(
  // p -> p.getPrice() > 0,
  // p -> p.getName() != null,
  // p -> !p.getName().isEmpty()
  // );
  // Return true only if ALL validators pass
  static boolean validateProduct(Product product, List<Predicate<Product>> validators) {
    // TODO: Use stream and allMatch
    return false;
  }

  // TODO 6.4: Create a function cache (memoization)
  // Wrap a function so it remembers previous results
  static <T, R> Function<T, R> memoize(Function<T, R> function) {
    Map<T, R> cache = new HashMap<>();
    return input -> {
      // TODO: Check cache first, compute and store if not present
      return null;
    };
  }

  // ========== HELPER CLASS ==========

  static class ProductUtils {
    static boolean isAffordable(Product p) {
      return p.getPrice() < 100.0;
    }

    static String formatProduct(Product p) {
      return String.format("[%s: $%.2f]", p.getName(), p.getPrice());
    }
  }

  // ========== TEST YOUR SOLUTIONS ==========

  public static void main(String[] args) {
    System.out.println("=== PART 1: BASIC FUNCTIONAL INTERFACES ===");
    // Uncomment after implementing
    /*
     * System.out.println("1.1 Unknown product: " + unknownProductFactory.get());
     * System.out.println("1.2 Print products:"); products.forEach(productPrinter);
     * System.out.println("1.3 Expensive products: " +
     * products.stream().filter(isExpensive).count()); System.out.println("1.4 Product names: " +
     * products.stream().map(toName).collect(Collectors.toList()));
     * System.out.println("1.5 Discounted price of $100: " + applyDiscount.apply(100.0));
     * labeledPrinter.accept("ITEM", products.get(0)); System.out.println("1.7 Created product: " +
     * productCreator.apply("Test", 50.0)); System.out.println("1.8 Electronics count: " +
     * products.stream().filter(p -> isElectronics.test(p.getCategory())).count());
     */

    System.out.println("\n=== PART 2: FUNCTION COMPOSITION ===");
    // Uncomment after implementing
    /*
     * System.out.println("2.1 Name to upper: " + nameToUpper.apply(products.get(0)));
     * System.out.println("2.2 Extract then prefix: " + extractThenPrefix.apply(products.get(0)));
     * System.out.println("2.3 Price pipeline: " + pricePipeline.apply(products.get(0)));
     * System.out.println("2.4 Full discount: " + fullDiscountPipeline.apply(100.0));
     */

    System.out.println("\n=== PART 3: PREDICATE COMPOSITION ===");
    // Uncomment after implementing
    /*
     * System.out.println("3.1 Expensive and in stock: " +
     * products.stream().filter(expensiveAndInStock).collect(Collectors.toList()));
     * System.out.println("3.2 Cheap or food: " +
     * products.stream().filter(cheapOrFood).collect(Collectors.toList()));
     * System.out.println("3.3 Not in stock: " +
     * products.stream().filter(notInStock).collect(Collectors.toList()));
     * System.out.println("3.4 Complex filter: " +
     * products.stream().filter(complexFilter).collect(Collectors.toList()));
     * System.out.println("3.5 Price in range [20-150]: " +
     * products.stream().filter(priceInRange(20, 150)).collect(Collectors.toList()));
     * System.out.println("3.6 Expensive in-stock names: " + expensiveInStockNames);
     */

    System.out.println("\n=== PART 4: METHOD REFERENCES ===");
    // Uncomment after implementing
    /*
     * System.out.println("4.1 Affordable (method ref): " +
     * products.stream().filter(affordableMethodRef).collect(Collectors.toList()));
     * System.out.println("4.2 Upper method ref: " + upperMethodRef.apply("hello"));
     * System.out.println("4.4a Names (method ref): " + namesMethodRef);
     * System.out.println("4.4b In stock (method ref): " + inStockMethodRef);
     */

    System.out.println("\n=== PART 6: ADVANCED PATTERNS ===");
    // Uncomment after implementing
    /*
     * UnaryOperator<Double> discount20 = createDiscounter(20.0);
     * System.out.println("6.1 Apply 20% discount to $100: " + discount20.apply(100.0));
     * 
     * Predicate<Product> priceFilter = createFilter("price", 50.0);
     * System.out.println("6.2 Products > $50: " +
     * products.stream().filter(priceFilter).collect(Collectors.toList()));
     * 
     * List<Predicate<Product>> validators = Arrays.asList( p -> p.getPrice() > 0, p -> p.getName()
     * != null, p -> !p.getName().isEmpty() ); System.out.println("6.3 Validate laptop: " +
     * validateProduct(products.get(0), validators));
     */
  }
}
