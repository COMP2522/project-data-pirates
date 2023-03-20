package org.example;

import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;

public class Sprite implements Comparable<Sprite> {

  protected PVector position;

  private PVector direction;

  private float size;

  /* Might include a speed buff thingy. */
  private float speed;

  public Color color;

  protected Window window;

  private PShape shape;

  private Window scene;

  private PShape[] enemyShapes = new PShape[3]; // Array of three different shapes
  public Sprite(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    position = pos;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    color = clr;
    this.window = scene;
    this.scene = scene;
    // Populate the array with different shapes
    enemyShapes[0] = createTriangleShape(size);
    enemyShapes[1] = createCircleShape(size);
    enemyShapes[2] = createSquareShape(size);
    this.shape = enemyShapes[(int) scene.random(enemyShapes.length)];
  }

  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.shape(this.shape, this.position.x, this.position.y);
    window.popStyle();
  }

  public void bounce() {
    if (this.position.x <= 0 ||
            this.position.x >= window.width ||
            this.position.y <= 0 ||
            this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  public boolean collided(Sprite obj) {
    float distance = PVector.dist(this.getPosition(), obj.getPosition());
    if (distance <= (this.getSize() + obj.getSize())) {
      return true;
    }
    return false;
  }

  public void update() {
//    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }
  public PVector getPosition() {
    return position;
  }

  public Color getColor() {
    return color;
  }

  public Window getWindow() {
    return window;
  }

  public float getSize() {
    return size;
  }

  public float getSpeed() {
    return speed;
  }

  public PVector getDirection() {
    return direction;
  }

  public void setWindow(Window window) {
    this.window = window;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
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

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   *
   * <p>The implementor must ensure {@link Integer#signum
   * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
   * all {@code x} and {@code y}.  (This implies that {@code
   * x.compareTo(y)} must throw an exception if and only if {@code
   * y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
   * {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code
   * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
   * == signum(y.compareTo(z))}, for all {@code z}.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   * class that implements the {@code Comparable} interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   */
  @Override
  public int compareTo(Sprite o) {
    return 0;
  }
}
