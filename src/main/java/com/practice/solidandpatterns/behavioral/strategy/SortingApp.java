package com.practice.solidandpatterns.behavioral.strategy;

import java.util.*;
import com.practice.solidandpatterns.behavioral.strategy.sorting.BubbleSort;
import com.practice.solidandpatterns.behavioral.strategy.sorting.MergeSort;
import com.practice.solidandpatterns.behavioral.strategy.sorting.QuickSort;
import com.practice.solidandpatterns.behavioral.strategy.sorting.Sorting;

public class SortingApp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Step 1: Get input numbers from user
    System.out.println("Enter numbers separated by spaces:");
    String input = scanner.nextLine();
    List<Integer> numbers = parseInput(input);

    // Step 2: Ask user for sorting preference
    System.out.println("Choose sorting method:");
    System.out.println("1. Bubble");
    System.out.println("2. Quick");
    System.out.println("3. Merge");
    System.out.print("Your choice (1-3): ");
    int choice = scanner.nextInt();

    Sorting strategy = switch (choice) {
      case 1 -> new BubbleSort();
      case 2 -> new QuickSort();
      case 3 -> new MergeSort();
      default -> throw new IllegalArgumentException("Invalid choice");
    };

    strategy.sort(numbers);
  }

  private static List<Integer> parseInput(String input) {
    return Arrays.stream(input.trim().split("\\s+")).map(Integer::parseInt).collect(ArrayList::new,
        ArrayList::add, ArrayList::addAll);
  }
}
