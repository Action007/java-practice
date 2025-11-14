package com.practice.solidandpatterns.behavioral.chain.ticket;

public class MainApp {
  public static void main(String[] args) {
    Ticket ticket1 = new Ticket("Password Reset", "User forgot password and needs reset",
        PriorityType.LOW, ComplexityType.SIMPLE);

    Ticket ticket2 = new Ticket("Printer Not Working", "Office printer shows error code E-42",
        PriorityType.MEDIUM, ComplexityType.MODERATE);

    Ticket ticket3 = new Ticket("Database Performance Issue",
        "Queries taking 10x longer than usual", PriorityType.HIGH, ComplexityType.COMPLEX);

    Ticket ticket4 = new Ticket("Production Server Down", "Entire e-commerce site is offline",
        PriorityType.CRITICAL, ComplexityType.COMPLEX);

    SupportHandler level1 = new Level1SupportHandler();
    SupportHandler level2 = new Level2SupportHandler();
    SupportHandler level3 = new Level3SupportHandler();
    SupportHandler manager = new ManagerSupportHandler();


    level1.setNext(level2).setNext(level3).setNext(manager);

    System.out.println("=== Processing Tickets ===");
    level1.processTicket(ticket1);
    level1.processTicket(ticket2);
    level1.processTicket(ticket3);
    level1.processTicket(ticket4);

    Ticket weirdTicket = new Ticket("Alien Invasion", "Extraterrestrials demanding coffee",
        PriorityType.LOW, ComplexityType.COMPLEX);
    level1.processTicket(weirdTicket);
  }
}
