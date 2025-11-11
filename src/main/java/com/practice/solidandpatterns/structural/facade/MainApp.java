package com.practice.solidandpatterns.structural.facade;

public class MainApp {
  public static void main(String[] args) {
    // Create instances of all 5 subsystems

    DVDPlayer dvdPlayer = new DVDPlayer();
    Lights lights = new Lights();
    Projector projector = new Projector();
    SoundSystem soundSystem = new SoundSystem();
    PopcornMaker popcornMaker = new PopcornMaker();

    // To watch "Inception":
    popcornMaker.on();
    popcornMaker.pop();
    lights.dim(10);
    projector.on();
    projector.setInput("DVD");
    projector.wideScreenMode();
    soundSystem.on();
    soundSystem.setSurroundSound();
    soundSystem.setVolume(5);
    dvdPlayer.on();
    dvdPlayer.play("Inception");


    System.out.println();


    HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
    homeTheaterFacade.watchMovie("Inception");

  }
}
