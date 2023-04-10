package org.example.spriteClasses;

import java.awt.Color;
import org.example.Main.Window;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * The entity / drawing object in the game.
 * Contains: Enemy, Player, and Projectile.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Sprite {

  protected PVector position;
  /* Oh my goodness. */
  protected Window window;
  protected PVector direction;
  protected Color color;
  protected Gif gm;
  protected float size;
  protected float speed;

  /**
   * Construct a Sprite object for <strong>Player</strong> and <strong>Projectile</strong>.
   *
   * @param pos Current position of the sprite.
   * @param direction Current direction of the sprite.
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param clr Color of the sprite.
   * @param scene The Frame where the sprite is displayed.
   *
   */
  public Sprite(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    position = pos;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    color = clr;
    this.window = scene;
  }

  /**
   * Construct a Sprite object for <strong>Enemy</strong>.
   *
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param scene Sketch where the sprite is displayed.
   *
   */
  public Sprite(float size, float speed, Window scene) {
    this.speed = speed;
    window = scene;
    this.size = size;
  }

  /**
   * Placeholder.
   */
  public void setUp() {}

  /**
   * Draws the sprite into the frame.
   */
  public void draw() {
    window.pushStyle();
    window.fill(color.getRGB());
    window.ellipseMode(PConstants.CENTER);
    window.ellipse(position.x, position.y, size, size);
    window.ellipseMode(PConstants.CORNER);
    window.popStyle();

  }

  /**
   * Show that white square around a sprite.
   */
  public void displayBoundaries() {
    window.pushStyle();
    window.stroke(new Color(0xFFFFFF).getRGB());
    window.noFill();
    window.rectMode(PConstants.CENTER);
    window.rect(position.x, position.y, size, size);
    window.rectMode(PConstants.CORNER);
    window.popStyle();
  }

  /**
   * Checks if this current Sprite is colliding
   * with another Sprite.
   *
   * @param obj The Sprite possibly colliding.
   *
   * @return true if collided, otherwise false.
   *
   */
  public boolean collided(Sprite obj) {
    float distance = PVector.dist(position, obj.getPosition());
    return distance <= (size + 20);
  }

  /**
   * Translate sprite to new location.
   */
  public void update() {
    position = position.add(direction.copy().mult(speed));
  }

  public void setGm(Gif gm) {
    this.gm = gm;
  }

  public Gif getGm() {
    return gm;
  }

  public PVector getPosition() {
    return position;
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

}
