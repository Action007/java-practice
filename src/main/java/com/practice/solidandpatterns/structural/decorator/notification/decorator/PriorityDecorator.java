package com.practice.solidandpatterns.structural.decorator.notification.decorator;

import com.practice.solidandpatterns.structural.decorator.notification.Notification;

public class PriorityDecorator extends NotificationDecorator {

  public PriorityDecorator(Notification wrappedNotification) {
    super(wrappedNotification);
  }

  @Override
  public void notifyUser(String message) {
    String priorityMessage = " [PRIORITY] " + message;
    super.notifyUser(priorityMessage);
  }
}
