package org.example.Main;

import java.awt.Color;
import java.util.HashMap;

/**
 * Data Pirates' colors.
 * Helps on obtaining entity colors.
 *
 * @author Data Pirates Team
 *
 * @version JDK 18
 */
public class EntityColor {

  /* Collection of Colors for DataPirates. */
  private static final HashMap<String, Color> spriteColors = new HashMap<String, Color>();

  /**
   * Start the setup of the program.
   * Sets the right colors to the HashMap.
   */
  public static void setColors() {
    spriteColors.put("Player", new Color(0, 255, 0));
    spriteColors.put("Enemy", new Color(255, 0, 0));
    spriteColors.put("Text", new Color(255, 255, 255));
    spriteColors.put("Reload", new Color(255, 255, 255));
    weaponColors();
    guiColors();
  }

  /**
   * Put weapon colors to the color collection.
   */
  private static void weaponColors() {
    spriteColors.put("The Almighty", new Color(255, 144, 0));
    spriteColors.put("Star Destroyer", new Color(60, 4, 107));
    spriteColors.put("Minal Laser", new Color(89, 184, 255));
    spriteColors.put("Hyperion Blood", new Color(0, 171, 167));
    spriteColors.put("Fluff Cannon", new Color(182, 148, 211));
    spriteColors.put("Terminator X", new Color(201, 157, 157));
    spriteColors.put("Xenon", new Color(41, 171, 62));
    spriteColors.put("Toy Laser", new Color(255, 176, 147));
    spriteColors.put("First", new Color(241, 169, 64));
  }

  /**
   * Put gui colors to the colors' collection.
   */
  private static void guiColors() {
    spriteColors.put("Menu Buttons", new Color(64, 188, 211));
  }

  /**
   * Get the HashMap collection to obtain every color.
   *
   * @return Sprite Colors collection.
   */
  public static HashMap<String, Color> getSpriteColors() {
    return spriteColors;
  }

}
