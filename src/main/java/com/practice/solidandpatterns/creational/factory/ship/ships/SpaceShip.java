package com.practice.solidandpatterns.creational.factory.ship.ships;

public abstract class SpaceShip {
  protected int position;
  protected int size;
  protected String displayName;
  protected int speed;

  public SpaceShip(int position, int size, String displayName, int speed) {
    this.position = position;
    this.size = size;
    this.displayName = displayName;
    this.speed = speed;
  }

  public abstract void startEngine();

  public int getPosition() {
    return position;
  }

  public int getSize() {
    return size;
  }

  public String getDisplayName() {
    return displayName;
  }

  public int getSpeed() {
    return speed;
  }
}
