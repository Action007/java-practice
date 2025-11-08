package com.practice.solidandpatterns.creational.factory.notification.initial;

public class NotificationService {
  public void sendNotification(String type) {
    switch (type) {
      case "sms":
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.send();
        break;

      case "whatsapp":
        WhatsAppNotification whatsAppNotification = new WhatsAppNotification();
        whatsAppNotification.send();
        break;

      case "email":
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.send();
        break;

      default:
        throw new IllegalStateException("no such type");
    }
  }
}
