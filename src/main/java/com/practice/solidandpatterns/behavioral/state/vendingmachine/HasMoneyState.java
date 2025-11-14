package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class HasMoneyState implements VendingMachineState {

  @Override
  public void insertMoney(VendingMachine context, double amount) {
    context.setBalance(context.getBalance() + amount);
    System.out.println("Money inserted. Balance now $" + context.getBalance());
  }

  @Override
  public void selectItem(VendingMachine context) {
    if (context.getItemCount() > 0) {
      context.setState(new DispensingState());
      context.dispense();
    } else {
      context.setState(new OutOfStockState());
      context.refund();
    }
  }

  @Override
  public void dispense(VendingMachine context) {
    System.out.println("Select an item first");
  }

  @Override
  public void refund(VendingMachine context) {
    System.out.println("Refunded: $" + String.format("%.2f", context.getBalance()));
    context.setBalance(0);
    context.setState(new NoMoneyState());
  }
}
