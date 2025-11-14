package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class NoMoneyState implements VendingMachineState {
  @Override
  public void insertMoney(VendingMachine context, double amount) {
    context.setBalance(context.getBalance() + amount);
    System.out.println("Money inserted. Balance: $" + String.format("%.2f", context.getBalance()));

    if (context.getBalance() >= 1.0) {
      context.setState(new HasMoneyState());
    }
  }

  @Override
  public void selectItem(VendingMachine context) {
    System.out.println("Insert money first");
  }

  @Override
  public void dispense(VendingMachine context) {
    System.out.println("Insert money first");
  }

  @Override
  public void refund(VendingMachine context) {
    System.out.println("No money to refund");
  }
}
