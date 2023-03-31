package org.example.spriteClasses;

import java.awt.Color;

import org.example.Main.Weapon;
import org.example.Main.Window;
import processing.core.PVector;

/**
 * Projectile program. Another sprite
 * that is shown as a bullet in-game.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Projectile extends Sprite {

  private Sprite projectile;

  private Weapon weapon;
  public Projectile(PVector pos, PVector direction, float size,
                    float speed, Color clr, Window scene, Weapon w) {
    super(pos, direction, size, speed, clr, scene);
  }

  public Weapon getWeapon() {
    return weapon;
  }

  //
//  public void move() {
//
//  }

}

/**
 * We dont know if we should do this one.
 */
class ProjectileManager {

//  private static Player player;
//
//  private Weapon weapon;
//
//  private Projectile projectile;
//
//  public ProjectileManager(Player p) {
//    player = p;
//  }
//

}
