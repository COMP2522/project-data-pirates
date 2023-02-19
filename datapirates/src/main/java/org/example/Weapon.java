package org.example;

import java.awt.*;

public class Weapon {

  private Color bulletColor;

  private int ammoCapacity;

  private String model;

  private int currentAmmo;

  public Weapon(String name, Color bulletColor, int maxAmmo) {
    model = name;
    this.bulletColor = bulletColor;
    ammoCapacity = maxAmmo;
    currentAmmo = maxAmmo;
  }
  public void levelUp() {

  }

  public void shoot() {
    if (hasAmmo()) {
      currentAmmo--;
    }
  }

  public boolean hasAmmo() {
    if (currentAmmo <= 0)
      return false;
    return true;
  }

  public void reload() {
    currentAmmo = ammoCapacity;
  }

  public int getAmmoCapacity() {
    return ammoCapacity;
  }

  public int getCurrentAmmo() {
    return currentAmmo;
  }
}
