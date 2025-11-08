package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public class UserNotificationFactory implements NotificationFactory {
  @Override
  public EmailNotification emailNotificatio() {
    return new UserEmailNotification();
  }

  @Override
  public SmsNotification smsNotification() {
    return new UserSmsNotification();
  }
}
