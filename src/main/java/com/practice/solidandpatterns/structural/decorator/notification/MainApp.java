package com.practice.solidandpatterns.structural.decorator.notification;

import com.practice.solidandpatterns.structural.decorator.notification.decorator.PriorityDecorator;

public class MainApp {
  public static void main(String[] args) {
    Notification notification = new PriorityDecorator(new EmailNotification());
    notification.notifyUser("aqshin.bahirzade@gmail.com");
  }
}
