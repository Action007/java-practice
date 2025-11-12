package com.practice.solidandpatterns.structural.bridge.notification.implementation;

public class EmailSender extends MessageSender {

  @Override
  public void sendMessage(String message) {
    System.out.println("Sending via Email: ");
  }
}
