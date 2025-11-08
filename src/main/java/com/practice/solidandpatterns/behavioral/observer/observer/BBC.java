package com.practice.solidandpatterns.behavioral.observer.observer;

public class BBC implements Observer {
  @Override
  public void update(String headline) {
    System.out.println("BBC - " + headline);
  }
}
