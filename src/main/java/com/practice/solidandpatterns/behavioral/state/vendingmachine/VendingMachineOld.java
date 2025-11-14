package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class VendingMachineOld {
  // State representation as enum for clarity (but still used in conditionals)
  private enum State {
    NO_MONEY, HAS_MONEY, DISPENSING, OUT_OF_STOCK
  }

  private State currentState;
  private int itemCount;
  private double balance;

  public VendingMachineOld(int initialItemCount) {
    this.itemCount = initialItemCount;
    this.balance = 0.0;
    this.currentState = (itemCount > 0) ? State.NO_MONEY : State.OUT_OF_STOCK;
  }

  public void insertMoney(double amount) {
    if (amount <= 0) {
      System.out.println("Please insert a valid amount.");
      return;
    }

    switch (currentState) {
      case NO_MONEY:
        balance += amount;
        System.out.println("Money inserted. Balance: $" + String.format("%.2f", balance));
        if (balance >= 1.0) {
          currentState = State.HAS_MONEY;
        }
        break;

      case HAS_MONEY:
        balance += amount;
        System.out
            .println("Additional money inserted. Balance: $" + String.format("%.2f", balance));
        break;

      case DISPENSING:
        System.out.println("Please wait, dispensing item...");
        break;

      case OUT_OF_STOCK:
        System.out
            .println("Machine is out of stock. Returning money: $" + String.format("%.2f", amount));
        // In naive version, we assume money is returned immediately
        break;
    }
  }

  public void selectItem() {
    switch (currentState) {
      case NO_MONEY:
        System.out.println("Insert money first.");
        break;

      case HAS_MONEY:
        if (itemCount > 0) {
          System.out.println("Item selected. Dispensing...");
          currentState = State.DISPENSING;
          dispense();
        } else {
          System.out.println("Out of stock!");
          currentState = State.OUT_OF_STOCK;
          refund();
        }
        break;

      case DISPENSING:
        System.out.println("Already dispensing an item.");
        break;

      case OUT_OF_STOCK:
        System.out.println("Out of stock. Cannot select item.");
        break;
    }
  }

  public void dispense() {
    switch (currentState) {
      case NO_MONEY:
        System.out.println("Insert money first.");
        break;

      case HAS_MONEY:
        // This shouldn't happen if selectItem() is used properly,
        // but we include it for defensive completeness
        if (itemCount > 0) {
          currentState = State.DISPENSING;
          // Fall through to dispensing logic
        } else {
          System.out.println("Out of stock.");
          currentState = State.OUT_OF_STOCK;
          refund();
          return;
        }

      case DISPENSING:
        // Perform dispensing
        itemCount--;
        double change = balance - 1.0;
        balance = 0.0;
        System.out.println(
            "Dispensing item. Change returned: $" + String.format("%.2f", Math.max(change, 0.0)));

        if (itemCount == 0) {
          currentState = State.OUT_OF_STOCK;
        } else {
          currentState = State.NO_MONEY;
        }
        break;

      case OUT_OF_STOCK:
        System.out.println("Out of stock. Cannot dispense.");
        break;
    }
  }

  public void refund() {
    switch (currentState) {
      case NO_MONEY:
        System.out.println("No money to refund.");
        break;

      case HAS_MONEY:
      case OUT_OF_STOCK:
        if (balance > 0) {
          System.out.println("Refunding $" + String.format("%.2f", balance));
          balance = 0.0;
        } else {
          System.out.println("No money to refund.");
        }
        currentState = State.NO_MONEY;
        break;

      case DISPENSING:
        System.out.println("Cannot refund while dispensing.");
        break;
    }
  }

  // Helper for debugging
  public void printStatus() {
    System.out.println("--- Status ---");
    System.out.println("State: " + currentState);
    System.out.println("Balance: $" + String.format("%.2f", balance));
    System.out.println("Items left: " + itemCount);
    System.out.println("--------------");
  }
}
