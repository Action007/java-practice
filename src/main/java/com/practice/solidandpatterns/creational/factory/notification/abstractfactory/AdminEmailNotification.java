package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public class AdminEmailNotification implements EmailNotification {
  @Override
  public void notifyUser() {
    System.out.println("Sending admin email notification");
  }
}
