package org.example.spriteClasses;

import processing.core.PVector;

public class DiagonalMove {

  private boolean upPressed = false;
  private boolean downPressed = false;
  private boolean leftPressed = false;
  private boolean rightPressed = false;

  public PVector getDirection() {
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
//      tempDirection.set(0, -1);
//      player.setDirection(new PVector(0, -1))
  }

  public boolean aKeyPressed() {
    return upPressed || downPressed || leftPressed || rightPressed;
  }
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