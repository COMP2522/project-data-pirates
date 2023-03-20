package org.example;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite {

  private int health;

  private int defense;

  private PShape shape;

  private PShape[] enemyShapes = new PShape[3]; // Array of three different shapes

  private Window scene;

  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr , scene);
    this.scene = scene;
    setHealth(100);
    setDefense(50);
    // Populate the array with different shapes
    enemyShapes[0] = createTriangleShape(size);
    enemyShapes[1] = createCircleShape(size);
    enemyShapes[2] = createSquareShape(size);

    // Randomly select a shape from the array
    this.shape = enemyShapes[(int) scene.random(enemyShapes.length)];
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

  private PShape createTriangleShape(float size) {
    PShape shape = scene.createShape();
    shape.beginShape();
    shape.fill(255, 0, 0);
    shape.vertex(0, -size / 2);
    shape.vertex(size / 2, size / 2);
    shape.vertex(-size / 2, size / 2);
    shape.endShape(PShape.CLOSE);
    return shape;
  }

  private PShape createCircleShape(float size) {
    PShape shape = scene.createShape(PShape.ELLIPSE, 0, 0, size, size);
    //shape.fill(0, 255, 0);
    return shape;
  }

  private PShape createSquareShape(float size) {
    PShape shape = scene.createShape(PShape.RECT, -size / 2, -size / 2, size, size);
    //shape.fill(0, 0, 255);
    return shape;
  }
}

