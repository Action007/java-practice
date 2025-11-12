package com.practice.solidandpatterns.structural.bridge.notification.abstraction;

import com.practice.solidandpatterns.structural.bridge.notification.implementation.MessageSender;

public class UrgentNotification extends Notification {
  public UrgentNotification(MessageSender messageSender) {
    super(messageSender);
  }

  @Override
  public void send(String message) {
    messageSender.sendMessage("URGENT: " + message);
  }
}
