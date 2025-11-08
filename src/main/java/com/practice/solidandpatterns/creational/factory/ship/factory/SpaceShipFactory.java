package com.practice.solidandpatterns.creational.factory.ship.factory;

import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;
import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShipType;

public abstract class SpaceShipFactory {
  public abstract SpaceShip createShip();
  // {
  // SpaceShip spaceShip;

  // switch (shipType) {
  // case MilleniumFalcon:
  // spaceShip = new MilleniumFalcon(10, 100, "Millenium Falcon", 1000);
  // break;
  // case UNSCInfinity:
  // spaceShip = new UNSCInfinity(10, 100, "UNSC Infinity", 1000);
  // break;
  // case USSEnterprise:
  // spaceShip = new USSEnterprise(10, 100, "USS Enterprise", 1000);
  // break;
  // case Serenity:
  // spaceShip = new Serenity(10, 100, "Millenium Falcon", 1000);
  // break;
  // default:
  // throw new IllegalArgumentException("Unknown ship type: " + shipType);
  // }

  // return spaceShip;
  // }
}
