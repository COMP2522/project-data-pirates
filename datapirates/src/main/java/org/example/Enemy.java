package org.example;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite {

  private int health;

  private int defense;

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

  public void init(Enemy enemy) {}

  public void remove(Enemy enemy) {}

  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

}
