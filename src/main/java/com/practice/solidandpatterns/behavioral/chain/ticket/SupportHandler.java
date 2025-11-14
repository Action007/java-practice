package com.practice.solidandpatterns.behavioral.chain.ticket;

public abstract class SupportHandler {
  SupportHandler nextHandler;

  public SupportHandler setNext(SupportHandler handler) {
    this.nextHandler = handler;
    return nextHandler;
  }

  public void processTicket(Ticket ticket) {
    if (canHandle(ticket)) {
      handleTicket(ticket);
    } else if (nextHandler != null) {
      nextHandler.processTicket(ticket);
    } else {
      System.out.println("Ticket unhandled: " + ticket.getTitle());
    }
  }

  protected abstract boolean canHandle(Ticket ticket);

  protected abstract void handleTicket(Ticket ticket);
}
