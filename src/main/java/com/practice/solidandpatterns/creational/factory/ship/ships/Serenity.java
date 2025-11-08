package com.practice.solidandpatterns.creational.factory.ship.ships;

public class Serenity extends SpaceShip {
  public Serenity(int position, int size, String displayName, int speed) {
    super(position, size, displayName, speed);
  }

  @Override
  public void startEngine() {
    System.err.println("Serenity - engine started!");
  }
}
