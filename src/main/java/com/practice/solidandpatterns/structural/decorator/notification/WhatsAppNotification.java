package com.practice.solidandpatterns.structural.decorator.notification;

public class WhatsAppNotification implements Notification {
  @Override
  public void notifyUser(String message) {
    System.out.println("Sending whatsapp message: " + message);
  }
}
