package org.example.spriteClasses;

import processing.core.PVector;

/**
 *
 */
public class SpriteManager {

  public static PVector calculateDirection(PVector curr, PVector dest) {
    PVector dir = PVector.sub(dest, curr);
    dir.normalize();
    dir.mult(0.5F);

    return dir;
  }


  public static void assignSprite(Sprite s, GifManager mmov) {
    if (s instanceof Player) {
//      if (mmov.isMoving((Player) s)) {
//        mmov.setSprites();
        mmov.display(s);
//      }
    }
    else {
      if (mmov.isMoving(s.getPosition())) {
        mmov.display(s);
      }
    }
  }
}
