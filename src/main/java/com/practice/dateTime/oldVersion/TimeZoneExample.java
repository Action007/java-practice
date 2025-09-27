package com.practice.dateTime.oldVersion;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneExample {
  public static void main(String[] args) {
    Calendar utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    Calendar localCal = Calendar.getInstance(); // your system default

    System.out.println("UTC Time:   " + utcCal.getTime());
    System.out.println("Local Time: " + localCal.getTime());

    // Get raw offset (in milliseconds)
    TimeZone localTZ = localCal.getTimeZone();
    int offsetMs = localTZ.getRawOffset();
    int offsetHours = offsetMs / (1000 * 60 * 60);
    System.out.println("Your timezone offset from UTC: " + offsetHours + " hours");

    // Check if currently in DST
    System.out.println("Is DST active now? " + localTZ.inDaylightTime(localCal.getTime()));

    // Convert local time to UTC manually
    long localMillis = localCal.getTimeInMillis();
    long utcMillis = localMillis - localTZ.getOffset(localMillis);
    Calendar manualUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    manualUTC.setTimeInMillis(utcMillis);
    System.out.println("Manually converted to UTC: " + manualUTC.getTime());
  }
}
