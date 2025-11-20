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
  // Learn the core functional interface signatures

  static List<Product> products =
      Arrays.asList(new Product("Laptop", 999.99, 1, true), new Product("Mouse", 25.50, 1, true),
          new Product("Coffee", 4.99, 2, false), new Product("Book", 15.99, 3, true),
          new Product("Headphones", 149.99, 1, true), new Product("Desk", 299.99, 4, false));

  // TODO 1.1: Create a Supplier that returns a default "Unknown Product"
  // Supplier<T>: () -> T (no input, returns T)
  // Hint: Return a new Product with name "Unknown", price 0.0, category 0, inStock false
  static Supplier<Product> unknownProductFactory = null;

  // TODO 1.2: Create a Consumer that prints product name and price
  // Consumer<T>: (T) -> void (takes T, returns nothing)
  // Hint: System.out.println(p.getName() + ": $" + p.getPrice())
  static Consumer<Product> productPrinter = null;

  // TODO 1.3: Create a Predicate that returns true if product price > $50
  // Predicate<T>: (T) -> boolean (takes T, returns true/false)
  static Predicate<Product> isExpensive = null;

  // TODO 1.4: Create a Function that extracts product name
  // Function<T, R>: (T) -> R (takes T, returns R)
  static Function<Product, String> toName = null;

  // TODO 1.5: Create a UnaryOperator that applies 10% discount to a price
  // UnaryOperator<T>: (T) -> T (takes T, returns same type T)
  // Hint: 10% discount means multiply by 0.9
  static UnaryOperator<Double> applyDiscount = null;

  // TODO 1.6: Create a BiConsumer that prints a label and product together
  // BiConsumer<T, U>: (T, U) -> void (takes two inputs, returns nothing)
  // Example output: "ITEM: Laptop ($999.99)"
  // Hint: System.out.println(label + ": " + p.getName() + " ($" + p.getPrice() + ")")
  static BiConsumer<String, Product> labeledPrinter = null;

  // TODO 1.7: Create a BiFunction that creates a product with category 99
  // BiFunction<T, U, R>: (T, U) -> R (takes two inputs, returns R)
  // Create a Product with the given name and price, category=99, inStock=true
  static BiFunction<String, Double, Product> productCreator = null;

  // TODO 1.8: Create an IntPredicate that checks if category is 1 (electronics)
  // IntPredicate: (int) -> boolean (specialized for primitive int)
  static IntPredicate isElectronics = null;

  // ========== PART 2: FUNCTION COMPOSITION (15 min) ==========
  // Learn to chain functions together

  // TODO 2.1: Create two functions and chain them with andThen
  // Function 1: Extract product name (use p.getName())
  // Function 2: Convert string to uppercase (use s.toUpperCase())
  // Chain them: extractName.andThen(toUpperCase)
  // andThen: first.andThen(second) means "do first, THEN do second"
  static Function<Product, String> extractName = null;
  static Function<String, String> toUpperCase = null;
  static Function<Product, String> nameToUpper = null; // Chained result

  // TODO 2.2: Create function chain with compose (reverse order)
  // Function 1: Add "Product: " prefix to a string (use "Product: " + s)
  // Function 2: Extract product name
  // Chain: addPrefix.compose(extractName) - extracts name THEN adds prefix
  // compose: second.compose(first) means "do first, THEN do second"
  // Key difference: compose executes in REVERSE order of the call
  static Function<String, String> addPrefix = null;
  static Function<Product, String> extractThenPrefix = null; // Chained result

  // TODO 2.3: Create a multi-stage function pipeline
  // Product -> extract price -> apply discount -> format as "$XX.XX"
  // Use andThen to chain all three functions
  // Hint: String.format("$%.2f", value)
  static Function<Product, Double> extractPrice = null;
  static Function<Double, Double> discount10Percent = null;
  static Function<Double, String> formatCurrency = null;
  static Function<Product, String> pricePipeline = null; // Chain all three

  // TODO 2.4: Use andThen to chain multiple transformations
  // Start with price -> apply 10% discount -> apply additional 5% discount -> round to int
  // Chain: discount10.andThen(discount5).andThen(roundToInt)
  // Hint for rounding: d.intValue() or (int)d.doubleValue()
  static UnaryOperator<Double> discount10 = null;
  static UnaryOperator<Double> discount5 = null;
  static Function<Double, Integer> roundToInt = null;
  static Function<Double, Integer> fullDiscountPipeline = null;

  // ========== PART 3: PREDICATE COMPOSITION (15 min) ==========
  // Learn to combine boolean logic

  // TODO 3.1: Combine predicates with AND
  // Predicate 1: price > 50
  // Predicate 2: inStock == true
  // Combined: expensive.and(inStock)
  // and(): Both predicates must be true
  static Predicate<Product> expensive = null;
  static Predicate<Product> inStock = null;
  static Predicate<Product> expensiveAndInStock = null;

  // TODO 3.2: Combine predicates with OR
  // Either price < 10 OR category == 2
  // or(): At least one predicate must be true
  static Predicate<Product> cheap = null;
  static Predicate<Product> categoryFood = null;
  static Predicate<Product> cheapOrFood = null;

  // TODO 3.3: Negate a predicate
  // Get all products that are NOT in stock
  // negate(): Reverses the predicate result
  // Hint: inStock.negate() or create a new predicate p -> !p.isInStock()
  static Predicate<Product> notInStock = null;

  // TODO 3.4: Complex predicate combination
  // (expensive AND inStock) OR (cheap AND category==1)
  // You'll need to create a categoryElectronics predicate first
  // Then chain: expensiveAndInStock.or(cheap.and(categoryElectronics))
  static Predicate<Product> complexFilter = null;

  // TODO 3.5: Create a reusable predicate builder
  // Method that returns a predicate for price range
  // Check if product.getPrice() is >= min AND <= max
  static Predicate<Product> priceInRange(double min, double max) {
    // TODO: Return predicate that checks if product.getPrice() is between min and max
    return null;
  }

  // TODO 3.6: Chain predicate with stream filter
  // Find all products that are: expensive AND in stock, collect names
  // Use: products.stream().filter(...).map(...).collect(Collectors.toList())
  static List<String> expensiveInStockNames = null;

  // ========== PART 4: METHOD REFERENCES (10 min) ==========
  // Method references are shorthand for lambdas that just call a method.
  // Four types:
  // 1. Static method: ClassName::staticMethod
  // 2. Instance method of particular object: instance::method
  // 3. Instance method of arbitrary object: ClassName::instanceMethod
  // 4. Constructor: ClassName::new
  //
  // Rule: Use method reference when lambda just passes all parameters to a method.
  // Examples:
  // x -> Math.sqrt(x) → Math::sqrt
  // s -> s.length() → String::length
  // () -> new ArrayList<>() → ArrayList::new

  // TODO 4.1: Create lambda AND method reference versions
  // Lambda version: p -> ProductUtils.isAffordable(p)
  // Method reference: ProductUtils::isAffordable
  static Predicate<Product> affordableLambda = null;
  static Predicate<Product> affordableMethodRef = null;

  // TODO 4.2: Create lambda AND method reference versions
  // Lambda version: s -> s.toUpperCase()
  // Method reference: String::toUpperCase
  static Function<String, String> upperLambda = null;
  static Function<String, String> upperMethodRef = null;

  // TODO 4.3: Constructor reference challenge
  // Problem: Product constructor needs 4 params, but BiFunction only provides 2
  // Option 1: Create a wrapper method that fills in the missing params
  // Option 2: Use a custom functional interface (see Part 5)
  // Try creating: (name, price) -> new Product(name, price, 0, true)
  static BiFunction<String, Double, Product> productLambda = null;
  // Can you convert this to Product::new? Think about why it's tricky!

  // TODO 4.4: Convert stream operations to method references
  // a) .map(p -> p.getName()) → Product::getName
  // b) .filter(p -> p.isInStock()) → Product::isInStock

  // Write BOTH versions for comparison:
  static List<String> namesLambda = null; // Use: products.stream().map(p ->
                                          // p.getName()).collect(...)
  static List<String> namesMethodRef = null; // Use:
                                             // products.stream().map(Product::getName).collect(...)

  static List<Product> inStockLambda = null; // Use lambda version
  static List<Product> inStockMethodRef = null; // Use method reference version

  // ========== PART 5: CUSTOM FUNCTIONAL INTERFACES (10 min) ==========

  // TODO 5.1: Create a custom functional interface for validation
  // Step 1: Uncomment and complete the interface definition
  // Step 2: Create TWO validators below

  // @FunctionalInterface
  // interface Validator<T> {
  // boolean validate(T item);
  // }

  // TODO: Create these validators:
  // Validator<Product> priceValidator = ???; // Check if price > 0
  // Validator<Product> nameValidator = ???; // Check if name is not null and not empty

  // TODO 5.2: Custom interface with default method
  // Step 1: Uncomment the interface
  // Step 2: Create a ProductProcessor that applies 10% discount

  // @FunctionalInterface
  // interface ProductProcessor {
  // Product process(Product p);
  //
  // default Product processWithLogging(Product p) {
  // System.out.println("Processing: " + p.getName());
  // return process(p);
  // }
  // }

  // TODO: Create a processor that discounts the price by 10%
  // ProductProcessor discountProcessor = ???;

  // TODO 5.3: Create a TriFunction interface (3 parameters)
  // Step 1: Uncomment and complete the interface
  // Step 2: Create a productFactory

  // @FunctionalInterface
  // interface TriFunction<T, U, V, R> {
  // R apply(T t, U u, V v);
  // }

  // TODO: Create a factory that takes (name, price, category) and creates a Product with
  // inStock=true
  // TriFunction<String, Double, Integer, Product> productFactory = ???;

  // ========== PART 6: ADVANCED PATTERNS (10 min) ==========

  // TODO 6.1: Function that returns a Function (Higher-order function)
  // Create a method that takes a discount percentage and returns a discount function
  // Example usage:
  // UnaryOperator<Double> discount20 = createDiscounter(20.0);
  // discount20.apply(100.0) should return 80.0
  // Hint: Return price -> price * (1 - discountPercent / 100)
  static UnaryOperator<Double> createDiscounter(double discountPercent) {
    // TODO: Return a UnaryOperator that applies the discount
    return null;
  }

  // TODO 6.2: Predicate factory method
  // Create a method that returns different predicates based on criteria
  // If filterType is "price": return p -> p.getPrice() > threshold
  // If filterType is "stock": return p -> p.isInStock() (ignore threshold)
  // Otherwise: return p -> true (accept all)
  // Hint: Use switch statement or if-else
  static Predicate<Product> createFilter(String filterType, double threshold) {
    // TODO: Return different predicates based on filterType
    return null;
  }

  // TODO 6.3: Compose a validation pipeline
  // Given a list of predicates, check if product passes ALL of them
  // Hint: validators.stream().allMatch(validator -> validator.test(product))
  // Return true only if product passes every validator
  static boolean validateProduct(Product product, List<Predicate<Product>> validators) {
    // TODO: Use stream and allMatch
    return false;
  }

  // TODO 6.4: Create a function cache (memoization)
  // Wrap a function so it remembers previous results
  // Algorithm:
  // 1. Check if input exists in cache map
  // 2. If yes, return cached result
  // 3. If no, compute result using function.apply(input), store in cache, then return
  // Hint: Use cache.computeIfAbsent(input, function)
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
     * System.out.println("4.1 Affordable (lambda): " +
     * products.stream().filter(affordableLambda).collect(Collectors.toList()));
     * System.out.println("4.1 Affordable (method ref): " +
     * products.stream().filter(affordableMethodRef).collect(Collectors.toList()));
     * System.out.println("4.2 Upper method ref: " + upperMethodRef.apply("hello"));
     * System.out.println("4.4a Names (lambda): " + namesLambda);
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
     * 
     * // Test memoization Function<Integer, Integer> expensive = x -> {
     * System.out.println("Computing " + x); return x * x; }; Function<Integer, Integer> cached =
     * memoize(expensive); System.out.println("6.4 First call: " + cached.apply(5)); // Should print
     * "Computing 5" System.out.println("6.4 Second call: " + cached.apply(5)); // Should NOT print
     * "Computing 5"
     */
  }
}
