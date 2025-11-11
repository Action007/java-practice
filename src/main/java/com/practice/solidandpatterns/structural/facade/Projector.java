package com.practice.solidandpatterns.structural.facade;

public class Projector {
  public void on() {
    System.out.println("Projector: on()");
  }

  public void off() {
    System.out.println("Projector: off()");
  }

  public void setInput(String type) {
    System.out.println("Projector: Setting input to " + type);
  }

  public void wideScreenMode() {
    System.out.println("Projector: wideScreenMode()");
  }
}
