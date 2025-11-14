package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public interface VendingMachineState {
  void insertMoney(VendingMachine context, double amount);

  void selectItem(VendingMachine context);

  void dispense(VendingMachine context);

  void refund(VendingMachine context);
}
