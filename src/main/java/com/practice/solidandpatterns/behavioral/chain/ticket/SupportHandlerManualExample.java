package com.practice.solidandpatterns.behavioral.chain.ticket;

public abstract class SupportHandlerManualExample {
  protected SupportHandler nextHandler;

  public SupportHandler setNext(SupportHandler next) {
    this.nextHandler = next;
    return next;
  }

  // NO template method!
  // Concrete handlers fully control flow
  public abstract void processTicket(Ticket ticket);
}
