package org.example.spriteClasses;

import java.awt.Color;

import org.example.Main.Window;
import processing.core.PVector;

/**
 * The entity / drawing object in the game.
 * Contains: Enemy, Player, and Projectile.
 * Others: Barriers, Cards...
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Sprite implements Comparable<Sprite> {

  protected PVector position;

  private PVector direction;

  private GifManager spriteMoveFunctions;
  private float size;

  /* Might include a speed buff thingy. */
  private float speed;

  private Color color;

  protected Window window;

  private GifManager mm;

  /**
   * Create a Sprite object.
   *
   * @param pos Current position of the sprite.
   * @param direction Current direction of the sprite.
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param clr Color of the sprite.
   * @param scene The Frame where the sprite is displayed.
   */
  public Sprite(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    position = pos;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    color = clr;
    this.window = scene;
    // Have a Map that uses filenames
    // and add the amount of sprites
    // then pvector the pos.
//    spriteMoveFunctions = new MoveManager();
  }

  public void setMm(GifManager mm) {
    this.mm = mm;
  }

  public GifManager getMm() {
    return mm;
  }

  /**
   * Draws the sprite into the Frame.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  /**
   * Will be used probably on explosive weapons.
   */
  public void bounce() {
    if (this.position.x <= 0
            || this.position.x >= window.getWidth()
            || this.position.y <= 0
            || this.position.y >= window.getHeight()) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  /**
   * Checks if this current Sprite is colliding
   * with another Sprite.
   *
   * @param obj The Sprite possibly colliding.
   *
   * @return true if collided, otherwise false.
   */
  public boolean collided(Sprite obj) {
    float distance = PVector.dist(this.getPosition(), obj.getPosition());
    if (distance <= (this.getSize() + obj.getSize())) {
      return true;
    }
    return false;
  }

  // this is the moves
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
   *
   * @return a negative integer, zero, or a positive integer as this object
   *        is less than, equal to, or greater than the specified object.
   *
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   *
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   *        {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
   *        class that implements the {@code Comparable} interface and violates
   *        this condition should clearly indicate this fact.  The recommended
   *        language is "Note: this class has a natural ordering that is
   *        inconsistent with equals."
   */
  @Override
  public int compareTo(Sprite o) {
    return 0;
  }
}
