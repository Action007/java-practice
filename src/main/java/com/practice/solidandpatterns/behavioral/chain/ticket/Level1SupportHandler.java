package com.practice.solidandpatterns.behavioral.chain.ticket;

public class Level1SupportHandler extends SupportHandler {

  @Override
  protected boolean canHandle(Ticket ticket) {
    if (ticket.getPriority() == PriorityType.LOW
        && ticket.getComplexity() == ComplexityType.SIMPLE) {
      return true;
    }
    return false;
  }

  @Override
  protected void handleTicket(Ticket ticket) {
    System.out.println("Level 1 Support handled: " + ticket.getTitle());
  }
}
