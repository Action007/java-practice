package com.practice.solidandpatterns.creational.builder.meal;

import java.util.Objects;

public class Meal {
  // Required
  private final Burger burger;

  // Optional
  private final Drink drink;
  private final Fries fries;

  private Meal(Builder builder) {
    this.burger = builder.burger;
    this.drink = builder.drink;
    this.fries = builder.fries;
  }

  public Burger getBurger() {
    return burger;
  }

  public Drink getDrink() {
    return drink;
  }


  public Fries getFries() {
    return fries;
  }

  public static class Builder {
    private final Burger burger;
    private Drink drink;
    private Fries fries;

    public Builder(Burger burger) {
      this.burger = Objects.requireNonNull(burger, "burger must be not null");
    }

    public Builder drink(Drink drink) {
      this.drink = drink;
      return this;
    }

    public Builder fries(Fries fries) {
      this.fries = fries;
      return this;
    }

    public Meal build() {
      return new Meal(this);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Meal created: {");
    sb.append("\n  burger = '").append(burger).append('\'');
    sb.append("\n  drink = '").append(drink).append('\'');
    sb.append("\n  fries = '").append(fries).append('\'');
    sb.append("\n}");
    return sb.toString();
  }
}
