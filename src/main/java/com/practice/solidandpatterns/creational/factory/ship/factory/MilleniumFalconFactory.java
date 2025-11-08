package com.practice.solidandpatterns.creational.factory.ship.factory;

import com.practice.solidandpatterns.creational.factory.ship.ships.MilleniumFalcon;
import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;

public class MilleniumFalconFactory extends SpaceShipFactory {
  @Override
  public SpaceShip createShip() {
    return new MilleniumFalcon(10, 100, "Millenium Falcon", 1000);
  }
}
