package org.example;

import java.awt.Color;
import java.util.HashMap;

/**
 * Basically, this is a misc program
 * that helps on obtaining entity colors.
 * Plan to create a package that holds
 * all identifications of the game.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class EntityColor {

  /**
   * Collection of Colors for DataPirates.
   * Index:
   * 0 - Player
   * 1 - Enemy
   * 2 - Text
   */
  private static HashMap<String, Color> spriteColors = new HashMap<String, Color>();

  /**
   * Start the setup of the program.
   * Sets the right colors to the HashMap.
   */
  public static void setColors() {
    spriteColors.put("Player", new Color(0, 255, 0));
    spriteColors.put("Enemy", new Color(255, 0, 0));
    spriteColors.put("Text", new Color(43, 180, 219));
    spriteColors.put("Reload", new Color(231, 28, 28));
    spriteColors.put("Hello", new Color(41, 171, 62));
    weaponColors();
    guiColors();
  }

  private static void weaponColors() {
    spriteColors.put("The Almighty", new Color(255, 144, 0));
    spriteColors.put("Star Destroyer", new Color(60, 4, 107));
    spriteColors.put("Minal Laser", new Color(255, 85, 105));
    spriteColors.put("Hyperion Blood", new Color(0, 171, 167));
    spriteColors.put("Fluff Cannon", new Color(182, 148, 211));
    spriteColors.put("Terminator X", new Color(0, 0, 0));
    spriteColors.put("Xenon", new Color(41, 171, 62));
    spriteColors.put("Toy Laser", new Color(255, 109, 51));
    spriteColors.put("First", new Color(241, 169, 64));
  }

  private static void guiColors() {
    spriteColors.put("Menu Buttons", new Color(64, 188, 211));
  }
  public static HashMap<String, Color> getSpriteColors() {
    return spriteColors;
  }

}
