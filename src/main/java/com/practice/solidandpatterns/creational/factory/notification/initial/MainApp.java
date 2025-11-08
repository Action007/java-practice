package com.practice.solidandpatterns.creational.factory.notification.initial;

public class MainApp {
  public static void main(String[] args) {
    NotificationService notificationService = new NotificationService();
    notificationService.sendNotification("sms");
    notificationService.sendNotification("whatsapp");
    notificationService.sendNotification("email");
  }
}
