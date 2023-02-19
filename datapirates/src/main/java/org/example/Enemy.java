package org.example;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite {
  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }

  public void init(Enemy enemy) {}

  public void remove(Enemy enemy) {}

  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

}
