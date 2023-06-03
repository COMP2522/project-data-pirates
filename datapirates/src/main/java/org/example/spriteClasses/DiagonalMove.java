package org.example.spriteClasses;

import processing.core.PVector;

/**
 * Helper Module / Method class for allowing the player to move
 * smoothly.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 *
 */
public class DiagonalMove {

  private boolean upPressed = false;
  private boolean downPressed = false;
  private boolean leftPressed = false;
  private boolean rightPressed = false;

  /**
   * Works with update, allows for better player movement.
   *
   * @return new translation.
   *
   */
  public PVector translateDirection() {
    PVector tempDirection = new PVector();
    if (upPressed)
      tempDirection.y = -1;
    if (downPressed)
      tempDirection.y = 1;
    if (leftPressed)
      tempDirection.x = -1;
    if (rightPressed)
      tempDirection.x = 1;

    return tempDirection;
  }

  /* TODO: Setters beyond this point. */
  public void setDownPressed(boolean downPressed) {
    this.downPressed = downPressed;
  }

  public void setLeftPressed(boolean leftPressed) {
    this.leftPressed = leftPressed;
  }

  public void setRightPressed(boolean rightPressed) {
    this.rightPressed = rightPressed;
  }

  public void setUpPressed(boolean upPressed) {
    this.upPressed = upPressed;
  }
}