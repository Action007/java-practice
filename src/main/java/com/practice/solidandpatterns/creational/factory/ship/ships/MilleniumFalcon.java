package com.practice.solidandpatterns.creational.factory.ship.ships;

public class MilleniumFalcon extends SpaceShip {
  public MilleniumFalcon(int position, int size, String displayName, int speed) {
    super(position, size, displayName, speed);
  }

  @Override
  public void startEngine() {
    System.err.println("Millenium Falcon - engine started!");
  }
}
