package com.practice.solidandpatterns.behavioral.chain.approvalsystem;

public class FinanceDirector extends Approver {

  public FinanceDirector(String approverName, double approvalLimit) {
    super(approverName, approvalLimit);
  }
}
