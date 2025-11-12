package com.practice.solidandpatterns.structural.bridge.notification.abstraction;

import com.practice.solidandpatterns.structural.bridge.notification.implementation.MessageSender;

public class CriticalNotification extends Notification {
  public CriticalNotification(MessageSender messageSender) {
    super(messageSender);
  }

  @Override
  public void send(String message) {
    messageSender.sendMessage("🚨 CRITICAL: " + message);
  }
}
