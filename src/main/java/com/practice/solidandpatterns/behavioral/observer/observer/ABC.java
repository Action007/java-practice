package com.practice.solidandpatterns.behavioral.observer.observer;

public class ABC implements Observer {
  @Override
  public void update(String headline) {
    System.out.println("ABC - " + headline);
  }
}
