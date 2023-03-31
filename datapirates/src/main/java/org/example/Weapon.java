package org.example;

import java.awt.*;

public class Weapon {

  private final Color bulletColor;

  private final int ammoCapacity;

  private String model;

  private int currentAmmo;

  public Weapon(String name, Color bulletColor, int maxAmmo) {
    model = name;
    this.bulletColor = bulletColor;
    ammoCapacity = maxAmmo;
    currentAmmo = maxAmmo;
  }

  //level up the weapon depends on the score of the player
  public void levelUp() {
    if (model.equals("Pistol")) {
      model = "Rifle";
      currentAmmo = ammoCapacity;
    } else if (model.equals("Rifle")) {
      model = "Shotgun";
      currentAmmo = ammoCapacity;
    } else if (model.equals("Shotgun")) {
      model = "Sniper";
      currentAmmo = ammoCapacity;
    }
  }

  //get the model of the weapon
    public String getModel() {
        return model;
    }

    //set the model of the weapon
    public void setModel(String model) {
        this.model = model;
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

  //set Ammo increase depending on the damage by the player
  public void increaseAmmo(int amount) {
    currentAmmo += amount;
    if (currentAmmo > ammoCapacity) {
      currentAmmo = ammoCapacity;
    }
  }
}
