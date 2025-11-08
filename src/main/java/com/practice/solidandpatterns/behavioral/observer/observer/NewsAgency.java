package com.practice.solidandpatterns.behavioral.observer.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject {
  List<Observer> observers = new ArrayList<>();

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObserver(String headline) {
    for (Observer observer : observers) {
      observer.update(headline);
    }
  }

}
