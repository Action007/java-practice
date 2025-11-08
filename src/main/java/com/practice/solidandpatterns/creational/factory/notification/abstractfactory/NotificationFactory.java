package com.practice.solidandpatterns.creational.factory.notification.abstractfactory;

public interface NotificationFactory {
  EmailNotification emailNotificatio();

  SmsNotification smsNotification();
}
