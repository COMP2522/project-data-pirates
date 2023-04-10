package org.example.spriteClasses;

import java.awt.Color;
import org.example.Main.Weapon;
import org.example.Main.Window;
import processing.core.PVector;

/**
 * Projectile program. Another sprite
 * that is shown as a bullet in-game.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Projectile extends Sprite {

  /*
    Type is used to separate enemy and player bullets.
   */
  public final int type;
  private final Weapon weapon;

  /**
   * Create a projectile entity.
   *
   * @param pos position of the bullet.
   * @param direction direction of the entity.
   * @param size size of the sprite.
   * @param speed speed of the sprite.
   * @param clr color of the sprite.
   * @param scene sketch where the sprite is displayed.
   * @param w weapon used.
   * @param type Player or Enemy projectile.
   *
   */
  public Projectile(PVector pos, PVector direction, float size,
                    float speed, Color clr, Window scene, Weapon w, int type) {
    super(pos, direction, size, speed, clr, scene);
    this.type = type;
    weapon = w;
  }

  /* TODO: Getters and Setters are beyond this point. */
  public Weapon getWeapon() {
    return weapon;
  }
}
