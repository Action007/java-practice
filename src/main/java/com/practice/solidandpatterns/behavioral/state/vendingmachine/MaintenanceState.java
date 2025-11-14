package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class MaintenanceState implements VendingMachineState {
  @Override
  public void insertMoney(VendingMachine context, double amount) {
    System.out.println("Machine under maintenance, please try later");
  }

  @Override
  public void selectItem(VendingMachine context) {
    System.out.println("Machine under maintenance, please try later");
  }

  @Override
  public void dispense(VendingMachine context) {
    System.out.println("Machine under maintenance, please try later");
  }

  @Override
  public void refund(VendingMachine context) {
    System.out.println("Machine under maintenance, please try later");
  }
}
