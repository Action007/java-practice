package com.practice.solidandpatterns.structural.decorator.notification;

import com.practice.solidandpatterns.structural.decorator.notification.decorator.PriorityDecorator;
import com.practice.solidandpatterns.structural.decorator.notification.decorator.SignatureDecorator;
import com.practice.solidandpatterns.structural.decorator.notification.decorator.UppercaseDecorator;

public class MainApp {
  public static void main(String[] args) {

    Notification notification = new PriorityDecorator(new EmailNotification());
    notification.notifyUser("aqshin.bahirzade@gmail.com");

    Notification notification2 = new UppercaseDecorator(notification);
    notification2.notifyUser("aqshin.bahirzade@gmail.com");

    Notification signatureNotification = new SignatureDecorator(notification2, "Action");
    signatureNotification.notifyUser("aqshin.baghirzade@gmail.com");
  }
}
