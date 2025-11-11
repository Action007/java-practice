package com.practice.solidandpatterns.structural.decorator.notification.decorator;

import com.practice.solidandpatterns.structural.decorator.notification.Notification;

public class SignatureDecorator extends NotificationDecorator {

  private String signature;

  public SignatureDecorator(Notification notification, String signature) {
    super(notification);
    this.signature = signature;
  }

  @Override
  public void notifyUser(String message) {
    super.notifyUser(String.format("{%s} %s", signature, message));
  }
}
