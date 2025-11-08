package com.practice.solidandpatterns.creational.factory.ship.factory;

import com.practice.solidandpatterns.creational.factory.ship.ships.Serenity;
import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;

public class SerenityFactory extends SpaceShipFactory {
  @Override
  public SpaceShip createShip() {
    return new Serenity(10, 100, "Serenity Falcon", 1000);
  }
}
