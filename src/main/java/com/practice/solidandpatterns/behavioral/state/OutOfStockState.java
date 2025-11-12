package com.practice.solidandpatterns.behavioral.state;

public class OutOfStockState implements VendingMachineState {

  @Override
  public void insertMoney(VendingMachine context, double amount) {
    System.out.println("Out of stock. Returning $" + String.format("%.2f", amount));
  }

  @Override
  public void selectItem(VendingMachine context) {
    System.out.println("Out of stock");
  }

  @Override
  public void dispense(VendingMachine context) {
    System.out.println("Out of stock");
  }

  @Override
  public void refund(VendingMachine context) {
    if (context.getBalance() > 0) {
      System.out.println("Refunded: " + context.getBalance() + "$");
      context.setBalance(0);
    } else {
      System.out.println("No money to refund");
    }
  }
}
