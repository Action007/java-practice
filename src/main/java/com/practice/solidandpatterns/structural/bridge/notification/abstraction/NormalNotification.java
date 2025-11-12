package com.practice.solidandpatterns.structural.bridge.notification.abstraction;

import com.practice.solidandpatterns.structural.bridge.notification.implementation.MessageSender;

public class NormalNotification extends Notification {
  public NormalNotification(MessageSender messageSender) {
    super(messageSender);
  }

  @Override
  public void send(String message) {
    messageSender.sendMessage("Normal: " + message);
  }
}
