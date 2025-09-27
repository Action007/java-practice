package com.practice.dateTime.oldVersion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatExample {
  public static void main(String[] args) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Format current date
    Date now = new Date();
    String formatted = sdf.format(now);
    System.out.println("Formatted: " + formatted);

    // Parse it back
    Date parsed = sdf.parse(formatted);
    System.out.println("Parsed back: " + parsed);
    System.out.println("Original equals parsed? " + now.equals(parsed)); // Likely FALSE — precision
                                                                         // loss!

    // Try invalid format
    try {
      sdf.parse("2025/13/40 99:99:99");
    } catch (Exception e) {
      System.out.println("Parse failed as expected: " + e.getMessage());
    }

    // Show mutability danger — changing pattern affects future use
    sdf.applyPattern("dd/MM/yyyy");
    System.out.println("After changing pattern: " + sdf.format(now));
  }
}
