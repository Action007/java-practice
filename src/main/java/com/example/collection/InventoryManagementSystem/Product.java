package com.example.collection.InventoryManagementSystem;

import java.util.Objects;

public class Product {
  private int id;
  private String name;
  private double price;
  private String category;
  private boolean inStock;

  public Product(int id, String name, double price, String category, boolean inStock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.category = category;
    this.inStock = inStock;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }

  public boolean isInStock() {
    return inStock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Product product = (Product) o;
    return id == product.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", category='" + category + '\'' +
        ", inStock=" + inStock +
        '}';
  }
}
