package com.practice.solidandpatterns.behavioral.state;

public class DispensingState implements VendingMachineState {

  @Override
  public void insertMoney(VendingMachine context, double amount) {
    System.out.println("Please wait, dispensing item");
  }

  @Override
  public void selectItem(VendingMachine context) {
    System.out.println("Already dispensing");
  }

  @Override
  public void dispense(VendingMachine context) {
    context.setItemCount(context.getItemCount() - 1);
    double change = context.getBalance() - 1.0;
    System.out.println("Dispensing item. Change: $" + change);
    context.setBalance(0);

    if (context.getItemCount() == 0) {
      context.setState(new OutOfStockState());
    } else {
      context.setState(new NoMoneyState());
    }
  }

  @Override
  public void refund(VendingMachine context) {
    System.out.println("Cannot refund, dispensing in progress");
  }
}
