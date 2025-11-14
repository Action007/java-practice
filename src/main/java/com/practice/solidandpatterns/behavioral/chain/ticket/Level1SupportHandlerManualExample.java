package com.practice.solidandpatterns.behavioral.chain.ticket;

public class Level1SupportHandlerManualExample extends SupportHandlerManualExample {

  @Override
  public void processTicket(Ticket ticket) {
    // Handler decides everything
    if (ticket.getPriority() == PriorityType.LOW
        && ticket.getComplexity() == ComplexityType.SIMPLE) {

      System.out.println("Level 1 Support handled: " + ticket.getTitle());
    } else {
      // Can't handle → decide whether to pass
      if (nextHandler != null) {
        nextHandler.processTicket(ticket); // Manual delegation
      } else {
        System.out.println("Ticket unhandled: " + ticket.getTitle());
      }
    }
  }
}
