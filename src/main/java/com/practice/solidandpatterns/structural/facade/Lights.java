package com.practice.solidandpatterns.structural.facade;

public class Lights {
  public void dim(int dim) {
    System.out.println("Lights: Dimming to " + dim + "%");
  }

  public void on() {
    System.out.println("Lights: on()");
  }
}
