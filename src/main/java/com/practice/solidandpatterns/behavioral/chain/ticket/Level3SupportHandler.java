package com.practice.solidandpatterns.behavioral.chain.ticket;

public class Level3SupportHandler extends SupportHandler {

  @Override
  protected boolean canHandle(Ticket ticket) {
    if (ticket.getPriority() == PriorityType.HIGH
        && ticket.getComplexity() == ComplexityType.COMPLEX) {
      return true;
    }
    return false;
  }

  @Override
  protected void handleTicket(Ticket ticket) {
    System.out.println("Level 3 Support handled: " + ticket.getTitle());
  }
}
