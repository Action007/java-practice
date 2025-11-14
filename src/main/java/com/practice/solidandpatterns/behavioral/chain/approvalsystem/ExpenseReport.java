package com.practice.solidandpatterns.behavioral.chain.approvalsystem;


public class ExpenseReport {
  private double amount;
  private String purpose;
  private String employeeName;

  public ExpenseReport(double amount, String purpose, String employeeName) {
    this.amount = amount;
    this.purpose = purpose;
    this.employeeName = employeeName;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getPurpose() {
    return this.purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getEmployeeName() {
    return this.employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  @Override
  public String toString() {
    return "{" + " amount='" + getAmount() + "'" + ", purpose='" + getPurpose() + "'"
        + ", employeeName='" + getEmployeeName() + "'" + "}";
  }
}
