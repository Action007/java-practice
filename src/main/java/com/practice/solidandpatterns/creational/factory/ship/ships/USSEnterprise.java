package com.practice.solidandpatterns.creational.factory.ship.ships;

public class USSEnterprise extends SpaceShip {
  public USSEnterprise(int position, int size, String displayName, int speed) {
    super(position, size, displayName, speed);
  }

  @Override
  public void startEngine() {
    System.err.println("USS Enterprise - engine started!");
  }
}
