package com.practice.solidandpatterns.creational.factory.notification.factory;

public class WhatsAppNotification implements Notification {
  @Override
  public void notifyUser() {
    System.out.println("Sending whatsapp ...");
  }
}
