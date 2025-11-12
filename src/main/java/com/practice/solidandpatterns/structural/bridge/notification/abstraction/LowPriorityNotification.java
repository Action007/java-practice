package com.practice.solidandpatterns.structural.bridge.notification.abstraction;

import com.practice.solidandpatterns.structural.bridge.notification.implementation.MessageSender;

public class LowPriorityNotification extends Notification {
  public LowPriorityNotification(MessageSender messageSender) {
    super(messageSender);
  }

  @Override
  public void send(String message) {
    messageSender.sendMessage("Low" + message);
  }

}
