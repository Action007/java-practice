package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public class UserSmsNotification implements SmsNotification {
  @Override
  public void notifyUser() {
    System.out.println("Sending user sms notification");
  }
}
