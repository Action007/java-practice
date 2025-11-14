package com.practice.solidandpatterns.behavioral.chain.approvalsystem;

public class MainApp {
  public static void main(String[] args) {
    Approver teamLead = new TeamLead("Team Lead", 500);
    Approver manager = new DepartmentManager("Manager", 2000);
    Approver financeDirector = new FinanceDirector("Finance Director", 10000);
    Approver ceo = new CEO("CEO", Double.MAX_VALUE);

    // Build the chain (ignore the return value)
    teamLead.setNext(manager).setNext(financeDirector).setNext(ceo);

    // ALWAYS call teamLead.processExpense, NOT chain.processExpense
    System.out.println("=== Test 1: $200 ===");
    teamLead.processExpense(new ExpenseReport(200, "Office supplies", "Adam"));

    System.out.println("\n=== Test 2: $800 ===");
    teamLead.processExpense(new ExpenseReport(800, "Team lunch", "Sarah"));

    System.out.println("\n=== Test 3: $5000 ===");
    teamLead.processExpense(new ExpenseReport(5000, "New server", "Mike"));

    System.out.println("\n=== Test 4: $15000 ===");
    teamLead.processExpense(new ExpenseReport(15000, "Consultant contract", "Lisa"));
  }
}
