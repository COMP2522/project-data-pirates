package org.example;

import processing.core.PVector;

public class SpriteManager {

  public static PVector calculateDirection(PVector curr, PVector dest) {
//    PVector mouse = new PVector(mouseX, mouseY);
//    PVector curr = new PVector(this.position.x, this.position.y);
    PVector dir = PVector.sub(dest, curr);
    dir.normalize();
    dir.mult(0.5F);

    return dir;
  }
}
