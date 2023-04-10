package org.example.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains all the Weapons in the game.
 * This also contains the calculation of RNGs.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Weapons {


  /* Weapon list. */
  private static ArrayList<Weapon> weapons;

  /**
   * Initialize the list.
   */
  public static void weaponStash() {
    weapons = new ArrayList<>();

    HashMap<String, Color> colors = EntityColor.getSpriteColors();

    Weapon almighty = new Weapon("The Almighty", colors.get("The Almighty"), 9999, 2500);
    Weapon stardust = new Weapon("Star Destroyer Blaster",
            colors.get("Star Destroyer"), 250, 50000);
    Weapon terminal = new Weapon("Melle's Laser", colors.get("Minal Laser"), 25, 999999);
    Weapon finale = new Weapon("Hype Gun", colors.get("Hyperion Blood"), 500, 10000);
    Weapon fluff = new Weapon("FurK-47", colors.get("Fluff Cannon"), 1000, 1540);
    Weapon terminator = new Weapon("Unique Bow", colors.get("Terminator X"), 30, 6660);
    Weapon xen = new Weapon("Cyber Pistol", colors.get("Xenon"), 50, 125);
    Weapon laser1 = new Weapon("Starter Laser", colors.get("Toy Laser"), 20, 30);
    Weapon starter = new Weapon("Your Pistol", colors.get("First"), 100, 10);

    weapons.add(almighty);
    weapons.add(stardust);
    weapons.add(terminal);
    weapons.add(finale);
    weapons.add(fluff);
    weapons.add(terminator);
    weapons.add(xen);
    weapons.add(laser1);
    weapons.add(starter);
  }

  /**
   * Get the Weapon object.
   *
   * @param name model / name of the weapon.
   * @return The Weapon if found, otherwise a weird weapon.
   */
  public static Weapon getWeapon(String name) {
    for (Weapon w : weapons) {
      if (w.getModel().equals(name))
        return w;
    }
    return new Weapon("NULL", EntityColor.getSpriteColors().get("Hello"), 1, 0);
  }

  /* TODO: Getters and Setters at this point. */

  public static ArrayList<Weapon> getWeapons() {
    return weapons;
  }




}
