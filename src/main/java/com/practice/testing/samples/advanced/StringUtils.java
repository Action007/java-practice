package com.practice.testing.samples.advanced;

public class StringUtils {
  public static boolean isPalindrome(String str) {
    if (str == null)
      return false;
    String clean = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    String reversed = new StringBuilder(clean).reverse().toString();
    return clean.equals(reversed);
  }
}
