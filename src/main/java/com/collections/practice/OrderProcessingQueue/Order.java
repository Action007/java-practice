package com.collections.practice.OrderProcessingQueue;

import java.util.Objects;

public class Order {
  private int orderId;
  private int customerId;
  private double totalAmount;
  private long timestamp = System.currentTimeMillis();
  private boolean priority;

  public Order(int orderId, int customerId, double totalAmount, long timestamp, boolean priority) {
    this.orderId = orderId;
    this.customerId = customerId;
    this.totalAmount = totalAmount;
    this.timestamp = timestamp;
    this.priority = priority;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public boolean isPriority() {
    return priority;
  }

  @Override
  public boolean equals(Object obj) {
    // Check if same reference
    if (this == obj)
      return true;

    // Check if null or different class
    if (obj == null || getClass() != obj.getClass())
      return false;

    // Cast and compare fields
    Order order = (Order) obj;
    return orderId == order.orderId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId);
  }

  @Override
  public String toString() {
    return "{" +
        " orderId='" + getOrderId() + "'" +
        ", customerId='" + getCustomerId() + "'" +
        ", totalAmount='" + getTotalAmount() + "'" +
        ", timestamp='" + getTimestamp() + "'" +
        ", priority='" + isPriority() + "'" +
        "}";
  }
}