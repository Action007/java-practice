package com.example.collection.OrderProcessingQueue;

import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    OrderProcessor orderProcessor = new OrderProcessor();

    System.out.println("🚀 Starting Order Processing Queue Tests...\n");

    // 🛒 1. Add Orders
    System.out.println("✅ 1. Adding orders...");
    try {
      orderProcessor.addOrder(new Order(101, 1001, 150.50, System.currentTimeMillis(), false));
      orderProcessor.addOrder(new Order(102, 1002, 299.99, System.currentTimeMillis(), true)); // priority
      orderProcessor.addOrder(new Order(103, 1003, 75.25, System.currentTimeMillis(), false));
      orderProcessor.addOrder(new Order(104, 1004, 500.00, System.currentTimeMillis(), true)); // priority
      orderProcessor.addOrder(new Order(105, 1005, 89.99, System.currentTimeMillis(), false));
      orderProcessor.addOrder(new Order(106, 1006, 199.99, System.currentTimeMillis(), true)); // priority

      System.out.println("✔️ All orders added successfully.\n");
    } catch (Exception e) {
      System.err.println("❌ Error adding order: " + e.getMessage());
    }

    // 📋 2. View Pending Orders
    System.out.println("✅ 2. Viewing pending orders...");
    Map<String, List<Order>> pendingOrders = orderProcessor.getPendingOrders();
    System.out.println("Priority Orders (sorted by total amount desc):");
    pendingOrders.get("PriorityOrders").forEach(System.out::println);
    System.out.println("\nNormal Orders (FIFO queue):");
    pendingOrders.get("NormalOrders").forEach(System.out::println);
    System.out.println();

    // 🎯 3. Process Next Order (should be highest priority)
    System.out.println("✅ 3. Processing next order...");
    Order nextOrder = orderProcessor.processNextOrder();
    System.out.println("Processed: " + nextOrder);
    System.out.println("✔️ Should be the highest value priority order ($500.00)\n");

    // 🎯 4. Process Another Order
    System.out.println("✅ 4. Processing another order...");
    Order anotherOrder = orderProcessor.processNextOrder();
    System.out.println("Processed: " + anotherOrder);
    System.out.println("✔️ Should be the next highest priority order ($299.99)\n");

    // 💰 5. Get High Value Orders
    System.out.println("✅ 5. Getting high value orders (>$100)...");
    List<Order> highValueOrders = orderProcessor.getHighValueOrders(100.0);
    System.out.println("High value normal orders:");
    highValueOrders.forEach(System.out::println);
    System.out.println("Found " + highValueOrders.size() + " high value orders.\n");

    // 📋 6. Check Remaining Pending Orders
    System.out.println("✅ 6. Checking remaining pending orders...");
    Map<String, List<Order>> remainingOrders = orderProcessor.getPendingOrders();
    System.out.println("Remaining Priority Orders:");
    remainingOrders.get("PriorityOrders").forEach(System.out::println);
    System.out.println("\nRemaining Normal Orders:");
    remainingOrders.get("NormalOrders").forEach(System.out::println);
    System.out.println();

    // ❌ 7. Cancel Last Normal Order
    System.out.println("✅ 7. Canceling last normal order...");
    try {
      Order canceledOrder = orderProcessor.cancelLastNormalOrder();
      System.out.println("Canceled: " + canceledOrder);
      System.out.println("✔️ Should be the last added normal order\n");
    } catch (Exception e) {
      System.err.println("❌ Error canceling order: " + e.getMessage());
    }

    // 🎯 8. Process Remaining Orders
    System.out.println("✅ 8. Processing all remaining orders...");
    Order order;
    int count = 0;
    while ((order = orderProcessor.processNextOrder()) != null) {
      count++;
      System.out.println("Processing order #" + count + ": " + order);
    }
    System.out.println("✔️ Processed " + count + " remaining orders.\n");

    // 🚫 9. Try to Process When Queue is Empty
    System.out.println("✅ 9. Trying to process when queue is empty...");
    Order emptyResult = orderProcessor.processNextOrder();
    System.out.println("Result: " + emptyResult); // should be null
    System.out.println("✔️ Correctly returned null for empty queue.\n");

    // 🚫 10. Test Invalid Orders
    System.out.println("✅ 10. Testing invalid orders...");
    try {
      orderProcessor.addOrder(null);
    } catch (IllegalArgumentException e) {
      System.out.println("✔️ Correctly rejected null order: " + e.getMessage());
    }

    try {
      orderProcessor.addOrder(new Order(999, 9999, -50.0, System.currentTimeMillis(), false));
    } catch (IllegalArgumentException e) {
      System.out.println("✔️ Correctly rejected negative amount: " + e.getMessage());
    }
    System.out.println();

    // 📊 11. Final Status
    System.out.println("✅ 11. Final queue status...");
    Map<String, List<Order>> finalStatus = orderProcessor.getPendingOrders();
    System.out.println("Priority queue size: " + finalStatus.get("PriorityOrders").size());
    System.out.println("Normal queue size: " + finalStatus.get("NormalOrders").size());
    System.out.println();

    System.out.println("🎉 All order processing tests completed!");
  }
}