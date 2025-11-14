package com.practice.solidandpatterns.behavioral.chain.approvalsystem;

public abstract class Approver {
  private String approverName;
  private double approvalLimit;
  private Approver nextApprover;

  public Approver(String approverName, double approvalLimit) {
    this.approverName = approverName;
    this.approvalLimit = approvalLimit;
  }

  public Approver setNext(Approver next) {
    this.nextApprover = next;
    return next;
  }

  public void processExpense(ExpenseReport report) {
    if (report.getAmount() <= approvalLimit) {
      System.out.println(
          approverName + " approved $" + report.getAmount() + " for " + report.getPurpose());
    } else if (nextApprover != null) {
      System.out
          .println(approverName + " cannot approve $" + report.getAmount() + ", escalating...");
      nextApprover.processExpense(report);
    } else {
      System.out.println("Expense $" + report.getAmount() + " exceeds all approval limits!");
    }
  }
}
