package com.practice.dateTime.oldVersion;

import java.util.Calendar;

public class CalendarExample {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    System.out.println("Default Calendar: " + cal.getTime());

    // Set to January 1, 2025
    cal.set(Calendar.YEAR, 2025);
    cal.set(Calendar.MONTH, Calendar.JANUARY); // JANUARY = 0
    cal.set(Calendar.DAY_OF_MONTH, 1);
    System.out.println("Set to Jan 1, 2025: " + cal.getTime());

    // Now try setting MONTH = 12 (DECEMBER?) → NO! It becomes January 2026!
    cal.set(Calendar.MONTH, 12); // There is no month 12 → rolls to next year
    System.out.println("Set MONTH=12: " + cal.getTime());

    // Add 1 month → goes to February 2026
    cal.add(Calendar.MONTH, 1);
    System.out.println("After add(1 month): " + cal.getTime());

    // Roll vs Add — roll doesn't change higher fields
    cal.set(Calendar.MONTH, Calendar.DECEMBER); // Dec 2026
    cal.set(Calendar.DAY_OF_MONTH, 30);
    System.out.println("Before roll: " + cal.getTime());
    cal.roll(Calendar.DAY_OF_MONTH, true); // rolls day → 1, but month/year unchanged
    System.out.println("After roll DAY_OF_MONTH: " + cal.getTime());
  }
}
