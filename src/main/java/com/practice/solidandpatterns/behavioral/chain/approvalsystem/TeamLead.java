package com.practice.solidandpatterns.behavioral.chain.approvalsystem;

public class TeamLead extends Approver {

  public TeamLead(String approverName, double approvalLimit) {
    super(approverName, approvalLimit);
  }

}
