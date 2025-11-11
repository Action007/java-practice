package com.practice.solidandpatterns.structural.facade;

public class SoundSystem {
  public void on() {
    System.out.println("SoundSystem: on()");
  }

  public void off() {
    System.out.println("SoundSystem: off()");
  }

  public void setVolume(int volume) {
    System.out.println("SoundSystem: Setting volume to " + volume);
  }

  public void setSurroundSound() {
    System.out.println("SoundSystem: wideScreenMode()");
  }
}
