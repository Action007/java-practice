package com.practice.solidandpatterns.behavioral.chain.ticket;

public class Level2SupportHandler extends SupportHandler {

  @Override
  protected boolean canHandle(Ticket ticket) {
    if (ticket.getPriority() == PriorityType.MEDIUM
        && ticket.getComplexity() == ComplexityType.MODERATE) {
      return true;
    }
    return false;
  }

  @Override
  protected void handleTicket(Ticket ticket) {
    System.out.println("Level 2 Support handled: " + ticket.getTitle());
  }
}
