package org.example.spriteClasses;

import processing.core.PVector;

/**
 * Manages the sprite's functionality.
 * Includes:
 * <ul>
 *   <li>Calculating direction to a destination.</li>
 *   <li>Assign Gif.</li>
 * </ul>
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class SpriteManager {

  /**
   * Calculate direction on where to go.
   *
   * @param curr The current object.
   * @param dest The location its trying to go to.
   * @return direction as PVector.
   *
   */
  public static PVector calculateDirection(PVector curr, PVector dest) {
    PVector dir = PVector.sub(dest, curr);
    dir.normalize();
    dir.mult(0.5F);

    return dir;
  }

  /**
   * Assign the sprite's gif.
   *
   * @param s Sprite entity.
   * @param gM gif class.
   *
   */
  public static void assignGif(Sprite s, Gif gM) {
    if (s instanceof Player)
      gM.display(s);
    else
      gM.display(s);
  }
}
