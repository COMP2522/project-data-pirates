package org.example;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite {

  private int health;

  private int defense;

  private Projectile projectile;

  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
    setHealth(100);
    setDefense(50);
  }

  public void setHealth(int health) {
//    Enemy.health = health;
    this.health = health;
  }

  public void setDefense(int def) {
    defense = def;
  }

  public int getHealth() {
    return health;
  }

  public int getDefense() {
    return defense;
  }

  public void init(Enemy enemy, Player player) {
    // ... copy properties from the enemy object ...
    this.setProjectile(enemy.getProjectile());
    // let enemy shoot projectiles
    enemy.getProjectile().enemyshoot(player);
  }

  public void shoot(Player player, Window window) {
    // Calculate the direction from the enemy to the player
    PVector direction = player.getPosition().copy().sub(this.getPosition()).normalize();

    // Create a new projectile with the enemy's position, direction, and color
    Projectile projectile = new Projectile(this.getPosition().copy(), direction, 3, 5, Color.RED, window);

    // Add the projectile to the window's list of projectiles
    window.getProjectiles().add(projectile);
  }


  public void remove(Enemy enemy) {}

  //let the enemy shoot projectiles towards the player
  public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
  }

  public Projectile getProjectile() {
        return projectile;
  }

  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

}
