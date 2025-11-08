package com.practice.solidandpatterns.creational.factory.ship;

import com.practice.solidandpatterns.creational.factory.ship.factory.MilleniumFalconFactory;
import com.practice.solidandpatterns.creational.factory.ship.factory.SerenityFactory;
import com.practice.solidandpatterns.creational.factory.ship.factory.UNSCInfinityFactory;
import com.practice.solidandpatterns.creational.factory.ship.factory.USSEnterpriseFactory;
import com.practice.solidandpatterns.creational.factory.ship.ships.SpaceShip;

public class SpaceShipDemo {
  public static void main(String[] args) {
    SpaceShip milleniumFalcon = new MilleniumFalconFactory().createShip();
    SpaceShip uNSCInfinity = new UNSCInfinityFactory().createShip();
    SpaceShip uSSEnterprise = new USSEnterpriseFactory().createShip();
    SpaceShip serenity = new SerenityFactory().createShip();

    milleniumFalcon.startEngine();
    uNSCInfinity.startEngine();
    uSSEnterprise.startEngine();
    serenity.startEngine();
  }
}
