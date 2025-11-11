package com.practice.solidandpatterns.structural.facade;

public class HomeTheaterFacade {
  DVDPlayer dvdPlayer = new DVDPlayer();
  Lights lights = new Lights();
  Projector projector = new Projector();
  SoundSystem soundSystem = new SoundSystem();
  PopcornMaker popcornMaker = new PopcornMaker();

  void makePopcorn() {
    popcornMaker.on();
    popcornMaker.pop();
  };

  void watchMovie(String movie) {
    lights.dim(10);
    projector.on();
    projector.setInput("DVD");
    projector.wideScreenMode();
    soundSystem.on();
  };

  void endMovie() {
    projector.off();
    soundSystem.off();
  };

}
