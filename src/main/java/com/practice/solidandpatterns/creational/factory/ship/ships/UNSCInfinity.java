package com.practice.solidandpatterns.creational.factory.ship.ships;

public class UNSCInfinity extends SpaceShip {
  public UNSCInfinity(int position, int size, String displayName, int speed) {
    super(position, size, displayName, speed);
  }

  @Override
  public void startEngine() {
    System.err.println("UNSC Infinity - engine started!");
  }
}
