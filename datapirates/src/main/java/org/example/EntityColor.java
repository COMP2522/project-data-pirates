package org.example;

import java.awt.*;
import java.util.HashMap;

public class EntityColor {

  /**
   * Collection of Colors for DataPirates.
   * Index:
   * 0 - Player
   * 1 - Enemy
   * 2 - Text
   */
  private static HashMap<String, Color> spriteColors = new HashMap<String, Color>();

  public static void setColors() {
    spriteColors.put("Player", new Color(0, 255, 0));
    spriteColors.put("Enemy", new Color(255, 0, 0));
    spriteColors.put("Text", new Color(43, 180, 219));
    spriteColors.put("Bullet", new Color(241, 169, 64));
    spriteColors.put("Reload", new Color(231, 28, 28));
  }

  public static HashMap<String, Color> getSpriteColors() {
    return spriteColors;
  }

//  public static void main(String[] args) {
//    EntityColor.setColors();
////    System.out.println(EntityColor.getSpriteColors().get("Player").getBlue());
//  }
}
