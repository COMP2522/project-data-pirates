package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Weapon {

  private final Color bulletColor;

  private final int ammoCapacity;

  private String model;

  private int currentAmmo;

  private ArrayList<Projectile> projectiles = new ArrayList<>();

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
  public void addProjectile(Projectile projectile) {
    projectiles.add(projectile);
  }

  public void update() {
    for (int i = 0; i < projectiles.size(); i++) {
      Projectile p = projectiles.get(i);
      p.update();

      // Remove projectiles if they go off-screen
      if (p.getPosition().x < 0 || p.getPosition().x > p.getWindow().width || p.getPosition().y < 0 || p.getPosition().y > p.getWindow().height) {
        projectiles.remove(i);
        i--;
      }
    }
  }

  public void draw() {
    for (Projectile p : projectiles) {
      p.draw();
    }
  }
}
