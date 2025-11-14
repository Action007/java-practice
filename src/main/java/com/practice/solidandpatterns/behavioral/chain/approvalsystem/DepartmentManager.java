package com.practice.solidandpatterns.behavioral.chain.approvalsystem;

public class DepartmentManager extends Approver {

  public DepartmentManager(String approverName, double approvalLimit) {
    super(approverName, approvalLimit);
  }
}
