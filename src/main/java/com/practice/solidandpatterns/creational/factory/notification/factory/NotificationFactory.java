package com.practice.solidandpatterns.creational.factory.notification.factory;

public class NotificationFactory {
  public static Notification createNotification(String type) {
    return switch (type) {
      case "sms" -> new SmsNotification();
      case "whatsapp" -> new WhatsAppNotification();
      case "email" -> new EmailNotification();
      default -> throw new IllegalStateException("no such type");
    };
  }
}
