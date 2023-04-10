package org.example.gui;

/**
 * Kind of a placeholder for the buttons.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Shape {

  private final int xPos;

  private final int yPos;

  private final int size;

  /**
   * Construct a Shape object.
   *
   * @param x x coordinate of the shape.
   * @param y y coordinate of the shape.
   * @param size size of the shape.
   */
  public Shape(int x, int y, int size) {
    xPos = x;
    yPos = y;
    this.size = size;
  }

  /* TODO: Getters and Setters beyond this point. */

  public int getSize() {
    return size;
  }

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

}
