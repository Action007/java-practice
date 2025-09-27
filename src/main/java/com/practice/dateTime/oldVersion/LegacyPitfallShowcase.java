package com.practice.dateTime.oldVersion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LegacyPitfallShowcase {
  public static void main(String[] args) throws Exception {
    // 1. Create a date string
    String input = "2025-02-30"; // Invalid date! February 30?

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false); // Strict parsing

    try {
      Date parsed = sdf.parse(input);
      System.out.println("Parsed date: " + parsed);
    } catch (Exception e) {
      System.out.println("Correctly rejected invalid date: " + e.getMessage());
    }

    // 2. Calendar month trap
    Calendar cal = Calendar.getInstance();
    cal.set(2025, 1, 30); // Feb 30, 2025? → becomes March 2, 2025 (in lenient mode)
    System.out.println("Calendar set to (2025, 1, 30): " + cal.getTime());

    // 3. SimpleDateFormat thread danger (simulate by reusing)
    sdf.applyPattern("MM/dd/yyyy");
    System.out.println("Reused formatter with new pattern: " + sdf.format(new Date()));

    // 4. Date mutability
    Date d1 = new Date();
    Date d2 = d1; // same reference!
    d2.setTime(0); // modifies d1 too!
    System.out.println("After setTime(0) on d2, d1 = " + d1); // also epoch!
  }
}
