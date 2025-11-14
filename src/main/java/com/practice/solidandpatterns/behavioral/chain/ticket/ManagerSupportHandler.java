package com.practice.solidandpatterns.behavioral.chain.ticket;

public class ManagerSupportHandler extends SupportHandler {

  @Override
  protected boolean canHandle(Ticket ticket) {
    if (ticket.getPriority() == PriorityType.CRITICAL) {
      return true;
    }
    return false;
  }

  @Override
  protected void handleTicket(Ticket ticket) {
    System.out.println("Level Manager Support handled: " + ticket.getTitle());
  }
}
