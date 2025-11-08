package com.practice.multithreading.Tets;

public class Main {
  public static void main(String[] args) {
    Transaction transaction = new Transaction();

    new Thread(transaction).start();
    new Thread(transaction).start();
  }
}
