package com.practice.solidandpatterns.structural.bridge.notification;

import com.practice.solidandpatterns.structural.bridge.notification.abstraction.CriticalNotification;
import com.practice.solidandpatterns.structural.bridge.notification.abstraction.NormalNotification;
import com.practice.solidandpatterns.structural.bridge.notification.abstraction.Notification;
import com.practice.solidandpatterns.structural.bridge.notification.abstraction.UrgentNotification;
import com.practice.solidandpatterns.structural.bridge.notification.implementation.EmailSender;
import com.practice.solidandpatterns.structural.bridge.notification.implementation.SMSSender;
import com.practice.solidandpatterns.structural.bridge.notification.implementation.SlackSender;

public class MainApp {
  public static void main(String[] args) {
    Notification urgent = new UrgentNotification(new EmailSender());
    urgent.send("Server down");

    Notification critical = new CriticalNotification(new SMSSender());
    critical.send("Database failure");

    Notification normal = new NormalNotification(new SlackSender());
    normal.send("Deployment complete");
  }
}
