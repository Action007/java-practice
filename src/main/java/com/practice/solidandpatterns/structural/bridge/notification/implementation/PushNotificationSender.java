package com.practice.solidandpatterns.structural.bridge.notification.implementation;

public class PushNotificationSender extends MessageSender {
  @Override
  public void sendMessage(String message) {
    System.out.println("Sending via Push Notification: ");
  }
}
