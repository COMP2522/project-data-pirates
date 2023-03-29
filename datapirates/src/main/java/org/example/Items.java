package org.example;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains all the items in the game. Gear and weaponry.
 *
 * This also contains the calculation of RNGs.
 *
 */
public class Items {

  private static Items itemContainer;

  private static ArrayList<Weapon> weapons;

  // Armor
//  private ArrayList<>
  private Items() {
    weaponStash();
  }
  public static Items getInstance() {
    if (itemContainer == null)
      itemContainer = new Items();
    return itemContainer;
  }

//  public Weapon(String name, Color bulletColor, int maxAmmo) {
//    model = name;
//    this.bulletColor = bulletColor;
//    ammoCapacity = maxAmmo;
//    currentAmmo = maxAmmo;
//  }
  public static void weaponStash() {
//    ArrayList<Weapon> temp = new ArrayList<>();
    weapons = new ArrayList<>();
//    spriteColors.put("The Almighty", new Color(255, 144, 0));
//    spriteColors.put("Star Destroyer", new Color(60, 4, 107));
//    spriteColors.put("Minal Laser", new Color(255, 85, 105));
//    spriteColors.put("Hyperion Blood", new Color(0, 171, 167));
//    spriteColors.put("Fluff Cannon", new Color(182, 148, 211));
//    spriteColors.put("Terminator X", new Color(0, 0, 0));
//    spriteColors.put("Xenon", new Color(41, 171, 62));
//    spriteColors.put("Toy Laser", new Color(255, 109, 51));

    /**
     * Teddy's Unique Sets of Weaponry
     */
      Weapon almighty = new Weapon("The Almighty", EntityColor.getSpriteColors().get("The Almighty"), 9999, 2500);
      Weapon stardust = new Weapon("Star Destroyer Blaster", EntityColor.getSpriteColors().get("Star Destroyer"), 250, 50000);
      Weapon terminal = new Weapon("Melle's Laser", EntityColor.getSpriteColors().get("Minal Laser"), 1, 999999);
      Weapon finale = new Weapon("Hype Gun", EntityColor.getSpriteColors().get("Hyperion Blood"), 500, 10000);
      Weapon fluff = new Weapon("FurK-47", EntityColor.getSpriteColors().get("Fluff Cannon"), 1000, 1540);
      Weapon terminator = new Weapon("Unique Bow", EntityColor.getSpriteColors().get("Terminator X"), 30, 6660);
      Weapon xen = new Weapon("Cyber Pistol", EntityColor.getSpriteColors().get("Xenon"), 50, 125);

    weapons.add(almighty);
    weapons.add(stardust);
    weapons.add(terminal);
    weapons.add(finale);
    weapons.add(fluff);
    weapons.add(terminator);
    weapons.add(xen);

    /**
     * Explosive Category
     */
    Weapon key  = new Weapon("Implosion Glove", EntityColor.getSpriteColors().get("Xenon"), 20, 9000);
    Weapon joke1 = new Weapon("Surprise!", EntityColor.getSpriteColors().get("Xenon"), 10, 400);
    Weapon lore = new Weapon("Harbinger of Explosion", EntityColor.getSpriteColors().get("Xenon"), 10, 231);
    Weapon lore2 = new Weapon("Timelle", EntityColor.getSpriteColors().get("Xenon"), 10, 1024);
    Weapon fireboom = new Weapon("Flaming Blaster", EntityColor.getSpriteColors().get("Xenon"), 10, 32);
    Weapon brokenExplosive = new Weapon("Broken Cannon", EntityColor.getSpriteColors().get("Xenon"), 5, 43);
    Weapon launcher = new Weapon("Grenade Launcher", EntityColor.getSpriteColors().get("Xenon"), 10, 25);

    /**
     * Low Level Weapons
     */
    Weapon laser1 = new Weapon("Starter Laser", EntityColor.getSpriteColors().get("Toy Laser"), 20, 30);
    Weapon starter = new Weapon("Your Pistol", EntityColor.getSpriteColors().get("First"), 100, 10);

    weapons.add(laser1);
    weapons.add(starter);

    System.out.println(weapons.size());
//      Weapon fluff = new Weapon("The Almighty", EntityColor.getSpriteColors().get("The Almighty"), 9999);
      //      temp.add("The Almighty", EntityColor.getSpriteColors().get("The Almighty"), 9999);
  }


  public static Weapon getWeapon(String name) {
    for (Weapon w : weapons) {
      if (w.getModel().equals(name))
        return w;
    }
    return new Weapon("NULL", EntityColor.getSpriteColors().get("Hello"), 1, 0);
//    weapons.stream().map().filter(() -> if )
//    return weapons;
  }



}
