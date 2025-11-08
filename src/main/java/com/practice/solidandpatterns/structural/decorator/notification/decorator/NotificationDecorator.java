package com.practice.solidandpatterns.structural.decorator.notification.decorator;

import com.practice.solidandpatterns.structural.decorator.notification.Notification;

public abstract class NotificationDecorator implements Notification {
  private Notification notification;

  public NotificationDecorator(Notification notification) {
    this.notification = notification;
  }

  @Override
  public void notifyUser(String message) {
    notification.notifyUser(message);
  }
}
