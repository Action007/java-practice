package com.practice.solidandpatterns.creational.factory.notification.factory;

public class SmsNotification implements Notification {
  @Override
  public void notifyUser() {
    System.out.println("Sending sms ...");
  }
}
