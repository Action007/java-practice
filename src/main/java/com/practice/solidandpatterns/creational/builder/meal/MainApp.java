package com.practice.solidandpatterns.creational.builder.meal;

public class MainApp {
  public static void main(String[] args) {
    Meal.Builder builder = new Meal.Builder(Burger.CHEESE_BURGER);
    Meal meal = builder.drink(Drink.COKE).fries(Fries.FRENCH_FRIES).build();
    System.out.println(meal);
  }
}
