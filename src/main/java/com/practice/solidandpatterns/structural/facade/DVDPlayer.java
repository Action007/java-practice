package com.practice.solidandpatterns.structural.facade;

public class DVDPlayer {

  public void on() {
    System.out.println("DVDPlayer: on()");
  }

  public void off() {
    System.out.println("DVDPlayer: off()");
  }

  public void play(String movie) {
    System.out.println("DVD Player: Playing movie '" + movie + "'");
  }

  public void stop() {
    System.out.println("DVDPlayer: stop()");
  }

  public void eject() {
    System.out.println("DVDPlayer: eject()");
  }
}
