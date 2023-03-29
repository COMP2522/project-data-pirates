package org.example;

import java.awt.*;

public class Weapon {

  private final Color bulletColor;

  private final int ammoCapacity;

  private String model;

  private int currentAmmo;

  // turn dmg, health, enemy health to double
  private final int dmg;

  public Weapon(String name, Color bulletColor, int maxAmmo, int dmg) {
    model = name;
    this.bulletColor = bulletColor;
    ammoCapacity = maxAmmo;
    currentAmmo = maxAmmo;
    this.dmg = dmg;
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

  public String getModel() {
    return model;
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

  public int getDmg() {
    return dmg;
  }

  public Color getBulletColor() {
    return bulletColor;
  }
}
