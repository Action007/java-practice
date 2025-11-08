package com.practice.solidandpatterns.creational.factory.notification.factory;

public class MainApp {
  public static void main(String[] args) {
    Notification emailNotification = NotificationFactory.createNotification("email");
    Notification smsNotification = NotificationFactory.createNotification("sms");
    Notification whatsappNotification = NotificationFactory.createNotification("whatsapp");

    emailNotification.notifyUser();
    smsNotification.notifyUser();
    whatsappNotification.notifyUser();
  }
}
