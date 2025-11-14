package com.practice.solidandpatterns.behavioral.state.vendingmachine;

public class MainApp {
  public static void main(String[] args) {
    // VendingMachineOld machine = new VendingMachineOld(2);

    // machine.selectItem(); // ❌ No money
    // machine.insertMoney(0.50); // ✅ Balance = $0.50
    // machine.selectItem(); // ❌ Still not enough
    // machine.insertMoney(0.50); // ✅ Now $1.00 → HAS_MONEY
    // machine.insertMoney(0.25); // ✅ Overpay → $1.25
    // machine.selectItem(); // ✅ Dispense + $0.25 change
    // machine.printStatus(); // Should show 1 item left, $0 balance, NO_MONEY

    // machine.insertMoney(1.00);
    // machine.refund(); // ✅ Full refund
    // machine.printStatus(); // NO_MONEY

    // // Deplete stock
    // machine.insertMoney(1.00);
    // machine.selectItem(); // Last item
    // machine.insertMoney(1.00); // Now OUT_OF_STOCK → should auto-refund


    VendingMachine vendingMachine = new VendingMachine(3);
    vendingMachine.insertMoney(1.00);


    vendingMachine.selectItem();
    vendingMachine.insertMoney(1.00);
    vendingMachine.selectItem();
    vendingMachine.insertMoney(1.00);
    vendingMachine.selectItem();

    vendingMachine.insertMoney(1.00);
  }
}
