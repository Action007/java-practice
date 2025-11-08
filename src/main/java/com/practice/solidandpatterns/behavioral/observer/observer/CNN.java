package com.practice.solidandpatterns.behavioral.observer.observer;

public class CNN implements Observer {
  @Override
  public void update(String headline) {
    System.out.println("CNN - " + headline);
  }
}
