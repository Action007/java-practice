package com.practice.solidandpatterns.structural.bridge.notification.abstraction;

import com.practice.solidandpatterns.structural.bridge.notification.implementation.MessageSender;

public abstract class Notification {
  MessageSender messageSender;

  public Notification(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  public abstract void send(String message);
}
