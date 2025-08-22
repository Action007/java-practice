package com.example.collection.OrderProcessingQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class OrderProcessor {
  Deque<Order> normalOrders = new LinkedList<>();
  // Comparator<Order> comparator = Comparator.comparing((order) ->
  // order.getTimestamp());
  Comparator<Order> comparator = Comparator.comparing(Order::getTotalAmount).reversed();
  PriorityQueue<Order> priorityOrders = new PriorityQueue<>(comparator);

  public boolean addOrder(Order order) {
    if (order == null) {
      throw new IllegalArgumentException("Order cannot be null.");
    }

    if (order.getTotalAmount() < 0) {
      throw new IllegalArgumentException("Price cannot be negative.");
    }

    if (order.isPriority()) {
      return priorityOrders.add(order);
    }

    return normalOrders.add(order);
  }

  public Order processNextOrder() {
    if (priorityOrders.size() != 0) {
      return priorityOrders.poll();
    } else if (normalOrders.size() != 0) {
      return normalOrders.poll();
    }

    return null;
  }

  public Map<String, List<Order>> getPendingOrders() {
    List<Order> sortedPriority = new ArrayList<>(priorityOrders);
    sortedPriority.sort(comparator);

    return Map.of(
        "PriorityOrders", sortedPriority,
        "NormalOrders", new ArrayList<>(normalOrders));
  }

  // public ArrayList<Order> getPendingOrders() {
  // ArrayList<Order> orders = new ArrayList<>(priorityOrders);
  // orders.addAll(normalOrders);
  // orders.sort(comparator);
  // return orders;
  // }

  public List<Order> getHighValueOrders(double minAmount) {
    Iterator<Order> iterator = normalOrders.iterator();
    List<Order> highValueOrders = new ArrayList<>();

    while (iterator.hasNext()) {
      Order order = iterator.next();
      if (order.getTotalAmount() > minAmount) {
        highValueOrders.add(order);
      }
    }

    return highValueOrders;
  }

  public Order cancelLastNormalOrder() {
    return normalOrders.removeLast();
  }
}
