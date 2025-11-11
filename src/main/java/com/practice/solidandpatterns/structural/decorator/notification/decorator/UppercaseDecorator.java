package com.practice.solidandpatterns.structural.decorator.notification.decorator;

import com.practice.solidandpatterns.structural.decorator.notification.Notification;

public class UppercaseDecorator extends NotificationDecorator {

  public UppercaseDecorator(Notification notification) {
    super(notification);
  }

  @Override
  public void notifyUser(String message) {
    super.notifyUser(message.toUpperCase());
  }
}
