package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public class AdminNotificationFactory implements NotificationFactory {

  @Override
  public EmailNotification emailNotificatio() {
    return new AdminEmailNotification();
  }

  @Override
  public SmsNotification smsNotification() {
    return new AdminSmsNotification();
  }

}
