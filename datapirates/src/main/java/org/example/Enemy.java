package org.example;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite {

  private int health;

  private int defense;

  private Window scene;

  private PImage image;

  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene, PImage image) {
    super(pos, direction, size, speed, clr , scene);
    this.scene = scene;
    this.image = image;
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

  public void draw() {
    window.image(image, position.x - image.width / 2, position.y - image.height / 2);
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

