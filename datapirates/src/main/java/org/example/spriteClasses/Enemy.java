package org.example.spriteClasses;

import java.awt.Color;

import org.example.Main.Window;
import processing.core.PVector;

/**
 * The enemy of the game.
 * Follows the player. Has a health system.
 * Planned of having the ability
 * to shoot.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Enemy extends Sprite {



  // only applies to moving entities
//  private boolean visited;

  private SpriteStat enemyStat;

  /**
   * Constructor. Sets a normal enemy with
   * the desired parameters.
   *
   * @param pos Current position of the sprite.
   * @param direction Starting direction of the sprite.
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param clr Color that identifies the sprite.
   * @param scene The Window or GUI where the sprite will appear.
   */
  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene, String level) {
    super(pos, direction, size, speed, clr, scene);
    setMm(new GifManager(level, 36, getPosition(), getWindow(), this));
    // use map to get the HP, DEF, and DMG of an enemy
    enemyStat = new SpriteStat(this, 100, 25, 15);
  }

  public SpriteStat getEnemyStat() {
    return enemyStat;
  }

  @Override
  public void draw() {
    SpriteManager.assignSprite(this, getMm());
  }



  public void init(Enemy enemy) {}

  public void remove(Enemy enemy) {}

  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

}
