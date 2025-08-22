package com.example.collection.InventoryManagementSystem;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    InventoryService inventoryService = new InventoryService();

    System.out.println("🚀 Starting Inventory Service Tests...\n");

    // 🧪 1. Add Products
    System.out.println("✅ 1. Adding products...");
    try {
      inventoryService.addProduct(new Product(0, "Butt Plug", 29.99, "Sex Toy", true));
      inventoryService.addProduct(new Product(1, "Dildo", 39.99, "Sex Toy", true));
      inventoryService.addProduct(new Product(2, "Vibrator", 49.99, "Sex Toy", false)); // out of stock
      inventoryService.addProduct(new Product(3, "Penis Ring", 19.99, "Sex Toy", true));
      inventoryService.addProduct(new Product(4, "Strap-On", 89.99, "Sex Toy", true));
      inventoryService.addProduct(new Product(5, "Lubricant", 12.99, "Accessories", true));

      System.out.println("✔️ All products added successfully.\n");
    } catch (Exception e) {
      System.err.println("❌ Error adding product: " + e.getMessage());
    }

    // 🧪 2. Get Product by ID
    System.out.println("✅ 2. Testing getProductById...");
    Product product = inventoryService.getProductById(1);
    System.out.println("Found: " + product);
    assert product != null && product.getName().equals("Dildo") : "❌ Failed: Expected Dildo";
    System.out.println("✔️ getProductById passed.\n");

    // 🧪 3. Try to add duplicate ID
    System.out.println("✅ 3. Testing duplicate ID...");
    try {
      inventoryService.addProduct(new Product(1, "Duplicate", 0, "Test", true));
    } catch (IllegalArgumentException e) {
      System.out.println("✔️ Correctly blocked duplicate ID: " + e.getMessage());
    }
    System.out.println();

    // 🧪 4. Try to add duplicate name (should be allowed, different ID)
    System.out.println("✅ 4. Testing duplicate name (different ID)...");
    try {
      inventoryService.addProduct(new Product(6, "Dildo", 35.99, "Sex Toy", true));
      System.out.println("✔️ Allowed duplicate name with different ID — this is correct behavior.");
    } catch (Exception e) {
      System.err.println("❌ Should allow same name, different ID");
    }
    System.out.println();

    // 🧪 5. Get All Products
    System.out.println("✅ 5. getAllProducts:");
    List<Product> allProducts = inventoryService.getAllProducts();
    allProducts.forEach(System.out::println);
    System.out.println();

    // 🧪 6. Filter by Category
    System.out.println("✅ 6. getByCategory('Sex Toy'):");
    List<Product> sexToys = inventoryService.getByCategory("Sex Toy");
    sexToys.forEach(System.out::println);
    System.out.println("Found " + sexToys.size() + " sex toys.\n");

    // 🧪 7. Filter by Price Range
    System.out.println("✅ 7. getByPriceRange(20, 60):");
    List<Product> priceRange = inventoryService.getByPriceRange(20, 60);
    priceRange.forEach(System.out::println);
    System.out.println();

    // 🧪 8. Sort by Price
    System.out.println("✅ 8. sortByPriceAsc:");
    List<Product> sortedByPrice = inventoryService.sortByPriceAsc();
    sortedByPrice.forEach(System.out::println);
    System.out.println();

    // 🧪 9. Sort by Name
    System.out.println("✅ 9. sortByName:");
    List<Product> sortedByName = inventoryService.sortByName();
    sortedByName.forEach(System.out::println);
    System.out.println();

    // 🧪 10. Update Product
    System.out.println("✅ 10. Updating product ID=5 (Lubricant)...");
    Product updatedLubricant = new Product(5, "Premium Lubricant", 15.99, "Accessories", true);
    inventoryService.updateProduct(updatedLubricant);
    System.out.println("Updated: " + inventoryService.getProductById(5));
    System.out.println();

    // 🧪 11. Remove Out of Stock
    System.out.println("✅ 11. Removing out-of-stock products...");
    boolean removed = inventoryService.removeOutOfStockProducts();
    System.out.println("Was any product removed? " + removed);
    System.out.println("Remaining products:");
    inventoryService.getAllProducts().forEach(System.out::println);
    System.out.println();

    // 🧪 12. Remove Product
    System.out.println("✅ 12. Removing product ID=3 (Penis Ring)...");
    boolean wasRemoved = inventoryService.removeProduct(3);
    System.out.println("Removed: " + wasRemoved);
    System.out.println("After removal:");
    inventoryService.getAllProducts().forEach(System.out::println);
    System.out.println("Name set still consistent? " + inventoryService.getAllProducts().stream()
        .allMatch(p -> inventoryService.getProductNames().contains(p.getName())));
    System.out.println();

    // 🧪 13. Negative Test: Get non-existent product
    System.out.println("✅ 13. Getting non-existent product (ID=999):");
    Product missing = inventoryService.getProductById(999);
    System.out.println("Result: " + missing); // should be null
    System.out.println();

    System.out.println("🎉 All tests completed!");
  }
}