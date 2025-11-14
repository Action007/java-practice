package com.practice.solidandpatterns.behavioral.command;

public class RemoteControl {
  public void setLight(Light light) {}

  public void pressLightOnButton() {}

  public void pressLightOffButton() {}

  public void setThermostat(Thermostat thermostat) {}

  public void pressHeatButton() {}

  public void pressCoolButton() {}

  public void setSecuritySystem(SecuritySystem system) {}

  public void pressArmButton() {}

  public void pressDisarmButton() {}

  public void undoLastAction() {}
}
