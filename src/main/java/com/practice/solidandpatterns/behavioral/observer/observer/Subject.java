package com.practice.solidandpatterns.behavioral.observer.observer;

public interface Subject {
  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObserver(String headline);
}
