package com.practice.solidandpatterns.creational.factory.ship.factory;

import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;
import com.practice.solidandpatterns.creational.factory.ship.ships.USSEnterprise;

public class USSEnterpriseFactory extends SpaceShipFactory {
  @Override
  public SpaceShip createShip() {
    return new USSEnterprise(10, 100, "USSEnterprise", 1000);
  }
}
