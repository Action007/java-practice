package com.practice.solidandpatterns.behavioral.strategy.sorting;

import java.util.List;

public class MergeSort implements Sorting {
  @Override
  public void sort(List<Integer> arr) {
    System.out.println("Merge sort result: " + arr);
  }
}
