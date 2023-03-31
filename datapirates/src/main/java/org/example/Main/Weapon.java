package org.example.Main;

import java.awt.*;

/**
 * Data Pirates' Weapon class.
 * Weapon object, used for players.
 *
 * @author Data Pirates Team
 */
public class Weapon {

  /* Projectile color. */
  private final Color bulletColor;

  /* Weapon's maximum ammo. */
  private final int ammoCapacity;

  /* Weapon name. */
  private final String model;

  /* Weapon's current clip. */
  private int currentAmmo;

  /* Weapon damage. */
  private final int dmg;

  /**
   * Create a new weapon.
   * @param name weapon model
   * @param bulletColor weapon color
   * @param maxAmmo weapon max capacity
   * @param dmg weapon damage
   */
  public Weapon(String name, Color bulletColor, int maxAmmo, int dmg) {
    model = name;
    this.bulletColor = bulletColor;
    ammoCapacity = maxAmmo;
    currentAmmo = maxAmmo;
    this.dmg = dmg;
  }

  /**
   * Activates when player clicks.
   * It simply decreases the clip.
   */
  public void shoot() {
    if (hasAmmo()) {
      currentAmmo--;
    }
  }

  /**
   * Check if there are clips.
   * @return true when has clips, otherwise false
   */
  public boolean hasAmmo() {
    return currentAmmo >= 0;
  }

  /**
   * Get the weapon name.
   * @return model
   */
  public String getModel() {
    return model;
  }

  /**
   * Reload simply reloads the clip.
   * Infinite ammo!
   */
  public void reload() {
    currentAmmo = ammoCapacity;
  }

  /**
   * Get max ammo.
   * @return ammoCapacity
   */
  public int getAmmoCapacity() {
    return ammoCapacity;
  }

  /**
   * Get clip value.
   * @return currentAmmo
   */
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  /**
   * Get the weapon damage.
   * @return dmg
   */
  public int getDmg() {
    return dmg;
  }

  /**
   * Get the Projectile's color.
   * @return bulletColor
   */
  public Color getBulletColor() {
    return bulletColor;
  }
}
