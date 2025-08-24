package com.collections.practice.InventoryManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InventoryService {
  private final Map<Integer, Product> products = new HashMap<>();
  private final Set<String> productNames = new HashSet<>();

  public Set<String> getProductNames() {
    return Collections.unmodifiableSet(productNames);
  }

  public void addProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null");
    }
    if (products.containsKey(product.getId())) {
      throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists");
    }

    if (!productNames.contains(product.getName())) {
      productNames.add(product.getName());
    }

    products.put(product.getId(), product);
  }

  public Product getProductById(int id) {
    return products.get(id);
  }

  public boolean removeProduct(int id) {
    Product removedProduct = products.remove(id);
    if (removedProduct != null) {
      productNames.remove(removedProduct.getName());
      return true;
    }
    return false;
  }

  public boolean updateProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException("Product is cannot be null.");
    }

    if (products.get(product.getId()) == null) {
      throw new IllegalArgumentException("Product with ID " + product.getId() + " does not exist.");
    }

    return products.put(product.getId(), product) != null;
  }

  public List<Product> getAllProducts() {
    List<Product> listOfProducts = new ArrayList<>(products.values());

    return listOfProducts;
  }

  public List<Product> getByCategory(String category) {
    if (category == null) {
      return new ArrayList<>();
    }
    List<Product> listOfProducts = new ArrayList<>();

    for (Product product : products.values()) {
      if (product.getCategory().equals(category)) {
        listOfProducts.add(product);
      }
    }

    return listOfProducts;
  }

  public List<Product> sortByPriceAsc() {
    List<Product> list = new ArrayList<>(products.values());
    Collections.sort(list, Comparator.comparing((product) -> product.getPrice()));
    return list;
  }

  public List<Product> sortByName() {
    List<Product> list = new ArrayList<>(products.values());
    Collections.sort(list, Comparator.comparing((product) -> product.getName()));
    return list;
  }

  public List<Product> getByPriceRange(double min, double max) {
    List<Product> listOfProducts = new ArrayList<>();

    for (Product product : products.values()) {
      if (product.getPrice() >= min && product.getPrice() <= max) {
        listOfProducts.add(product);
      }
    }

    return listOfProducts;
  }

  public boolean removeOutOfStockProducts() {
    Iterator<Product> iterator = products.values().iterator();
    boolean isRemovedAny = false;

    while (iterator.hasNext()) {
      Product product = iterator.next();

      if (!product.isInStock()) {
        iterator.remove();
        productNames.remove(product.getName());
        isRemovedAny = true;
      }
    }

    return isRemovedAny;
  }
}
