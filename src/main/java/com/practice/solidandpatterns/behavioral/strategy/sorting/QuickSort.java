package com.practice.solidandpatterns.behavioral.strategy.sorting;

import java.util.List;

public class QuickSort implements Sorting {
  @Override
  public void sort(List<Integer> arr) {
    System.out.println("Quick sort result: " + arr);
  }
}
