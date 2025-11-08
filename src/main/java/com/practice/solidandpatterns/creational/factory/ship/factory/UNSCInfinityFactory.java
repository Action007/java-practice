package com.practice.solidandpatterns.creational.factory.ship.factory;

import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;
import com.practice.solidandpatterns.creational.factory.ship.ships.UNSCInfinity;

public class UNSCInfinityFactory extends SpaceShipFactory {
  @Override
  public SpaceShip createShip() {
    return new UNSCInfinity(10, 100, "UNSCInfinity Falcon", 1000);
  }
}
