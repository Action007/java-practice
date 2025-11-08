package com.practice.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfacesLab {

  static class Product {
    String name;
    double price;
    int category;

    public Product(String name, double price, int category) {
      this.name = name;
      this.price = price;
      this.category = category;
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

    @Override
    public String toString() {
      return String.format("%s ($%.2f, cat:%d)", name, price, category);
    }
  }

  public static void main(String[] args) {
    List<Product> products = Arrays.asList(new Product("Laptop", 999.99, 1),
        new Product("Mouse", 25.50, 1), new Product("Coffee", 4.99, 2),
        new Product("Book", 15.99, 3), new Product("Headphones", 149.99, 1));

    // 1. SUPPLIER - Create a new product factory
    // TODO: Create a Supplier that returns a default "Unknown Product" priced at $0
    Supplier<Product> unknownProductFactory = null;

    // 2. CONSUMER - Print product info
    // TODO: Create a Consumer that prints product name and price
    Consumer<Product> productPrinter = null;

    // 3. PREDICATE - Filter expensive products
    // TODO: Create a Predicate that returns true if product price > $50
    Predicate<Product> isExpensive = null;

    // 4. FUNCTION - Transform product to just its name
    // TODO: Create a Function that extracts product name
    Function<Product, String> toName = null;

    // 5. UNARY_OPERATOR - Apply discount to price
    // TODO: Create a UnaryOperator that applies 10% discount to a price
    UnaryOperator<Double> applyDiscount = null;

    // 6. BICONSUMER - Print product with custom label
    // TODO: Create a BiConsumer that prints a label and product together
    BiConsumer<String, Product> labeledPrinter = null;

    // 7. BIFUNCTION - Create product from name and price
    // TODO: Create a BiFunction that creates a product with category 99
    BiFunction<String, Double, Product> productCreator = null;

    // 8. BIPREDICATE - Check if product matches criteria
    // TODO: Create a BiPredicate that checks if product name contains substring AND price is under
    // max
    BiPredicate<Product, String> productContainsName = null;
    BiPredicate<Product, Double> productUnderPrice = null;

    // 9. BINARY_OPERATOR - Combine two prices
    // TODO: Create a BinaryOperator that adds two prices
    BinaryOperator<Double> addPrices = null;

    // ====== BONUS: PRIMITIVE SPECIALIZATIONS ======
    // TODO: Create an IntPredicate that checks if category is 1 (electronics)
    IntPredicate isElectronics = null;

    // ====== USAGE EXAMPLES (Uncomment after implementing) ======

    /*
     * System.out.println("=== 1. SUPPLIER ==="); Product unknown = unknownProductFactory.get();
     * System.out.println(unknown);
     * 
     * System.out.println("\n=== 2. CONSUMER ==="); products.forEach(productPrinter);
     * 
     * System.out.println("\n=== 3. PREDICATE ==="); List<Product> expensiveProducts =
     * products.stream() .filter(isExpensive) .collect(Collectors.toList());
     * expensiveProducts.forEach(System.out::println);
     * 
     * System.out.println("\n=== 4. FUNCTION ==="); List<String> productNames = products.stream()
     * .map(toName) .collect(Collectors.toList()); System.out.println(productNames);
     * 
     * System.out.println("\n=== 5. UNARY_OPERATOR ==="); List<Double> discountedPrices =
     * products.stream() .map(Product::getPrice) .map(applyDiscount) .collect(Collectors.toList());
     * System.out.println("Discounted prices: " + discountedPrices);
     * 
     * System.out.println("\n=== 6. BICONSUMER ==="); products.forEach(product ->
     * labeledPrinter.accept("ITEM", product));
     * 
     * System.out.println("\n=== 7. BIFUNCTION ==="); Product newProduct =
     * productCreator.apply("New Gadget", 75.99); System.out.println(newProduct);
     * 
     * System.out.println("\n=== 8. BIPREDICATE ==="); Product laptop = products.get(0); boolean
     * matches = productContainsName.and(productUnderPrice) .test(laptop, "Lap") &&
     * productUnderPrice.test(laptop, 1000.0); System.out.println("Laptop matches: " + matches);
     * 
     * System.out.println("\n=== 9. BINARY_OPERATOR ==="); Double totalPrice = products.stream()
     * .map(Product::getPrice) .reduce(0.0, addPrices); System.out.println("Total price: $" +
     * totalPrice);
     * 
     * System.out.println("\n=== BONUS: IntPredicate ==="); List<Product> electronics =
     * products.stream() .filter(p -> isElectronics.test(p.getCategory()))
     * .collect(Collectors.toList()); System.out.println("Electronics: " + electronics);
     */
  }
}
