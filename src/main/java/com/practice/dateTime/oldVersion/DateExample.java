package com.practice.dateTime.oldVersion;

import java.util.Date;

public class DateExample {
  public static void main(String[] args) {
    // Current moment in time (milliseconds since epoch)
    Date now = new Date();
    System.out.println("Current Date (toString): " + now);

    // Wait 2 seconds
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }

    // Create another Date object
    Date later = new Date();
    System.out.println("2 seconds later: " + later);

    // Compare them
    System.out.println("Is 'now' before 'later'? " + now.before(later));

    // BUT — Date is MUTABLE!
    later.setTime(now.getTime()); // now 'later' points to same time as 'now'
    System.out.println("After setTime(): later = " + later);
    System.out.println("Are they equal now? " + now.equals(later));
  }
}
