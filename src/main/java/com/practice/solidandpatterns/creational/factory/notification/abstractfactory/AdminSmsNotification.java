package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public class AdminSmsNotification implements SmsNotification {
  @Override
  public void notifyUser() {
    System.out.println("Sending admin sms notification");
  }
}
