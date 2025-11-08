package com.practice.solidandpatterns.behavioral.strategy.sorting;

import java.util.List;

public class BubbleSort implements Sorting {
  @Override
  public void sort(List<Integer> arr) {
    System.out.println("Bubble sort result: " + arr);
  }
}
