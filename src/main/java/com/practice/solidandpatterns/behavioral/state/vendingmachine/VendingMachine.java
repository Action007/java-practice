package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class VendingMachine {
  private VendingMachineState currentState;
  private VendingMachineState stateBeforeMaintenance;
  private int itemCount;
  private double balance;

  public VendingMachine(int itemCount) {
    this.itemCount = itemCount;
    this.balance = 0.0;
    this.currentState = new NoMoneyState();
  }

  protected void setState(VendingMachineState state) {
    this.currentState = state;
  }

  public void enableMaintenanceMode() {
    stateBeforeMaintenance = this.currentState;
    this.currentState = new MaintenanceState();
  }

  public void disableMaintenanceMode() {
    this.currentState = stateBeforeMaintenance;
  }

  public int getItemCount() {
    return this.itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void insertMoney(double amount) {
    currentState.insertMoney(this, amount);
  }

  public void selectItem() {
    currentState.selectItem(this);
  }

  public void dispense() {
    currentState.dispense(this);

  }

  public void refund() {
    currentState.refund(this);
  }
}
