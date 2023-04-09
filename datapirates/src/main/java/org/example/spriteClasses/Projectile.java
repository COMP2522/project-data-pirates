package org.example.spriteClasses;

import java.awt.Color;

import jdk.jshell.execution.JdiInitiator;
import org.example.Main.Weapon;
import org.example.Main.Window;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

import static processing.core.PConstants.*;

/**
 * Projectile program. Another sprite
 * that is shown as a bullet in-game.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Projectile extends Sprite {

  public final int type;
  private Sprite projectile;

  private Weapon weapon;


  public Projectile(PVector pos, PVector direction, float size,
                    float speed, Color clr, Window scene, Weapon w, int type) {
    super(pos, direction, size, speed, clr, scene);
    this.type = type;
//    getWindow().
  }


  public Weapon getWeapon() {
    return weapon;
  }

  //


//  public void update() {
//    //    this.bounce();
//    this.position = this.getPosition().add(this.direction.copy().mult(speed));
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
