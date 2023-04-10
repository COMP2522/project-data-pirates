package org.example.spriteClasses;

import java.awt.Color;
<<<<<<< HEAD

import org.example.Main.EntityColor;
import org.example.spriteClasses.Player;

=======
import org.example.Main.Weapons;
>>>>>>> TD_01_javs
import org.example.Main.Window;
import org.example.music.MusicManager;
import processing.core.PVector;

/**
 * The enemy of the game.
 * Follows the player. Has a health system.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Enemy extends Sprite {

  /* Stats of the enemy. */
  private final SpriteStat enemyStat;

  /* Applies to enemies higher than level 1. */
  private final boolean canFire;

  /* Thread on a loop, used for shooting. */
  private Thread shootThread;

  /*
    The time in milliseconds it would take
    for the enemy to shoot again.
  */
  private int delayAmount;

  private Player targetPlayer;

  /**
   * Constructor. Sets a normal enemy with
   * the desired parameters.
   *
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param scene The Window or GUI where the sprite will appear.
   *
   */
<<<<<<< HEAD
  public Enemy(PVector pos, PVector direction, float size, float speed, Color clr, Window scene, String level) {
    super(pos, direction, size, speed, clr, scene);
    setMm(new GifManager(level, 36, getPosition(), getWindow(), this));
    // use map to get the HP, DEF, and DMG of an enemy
    enemyStat = new SpriteStat(this, 100, 25, 15);
    this.targetPlayer = Player.getInstance( new PVector(0, 0), new PVector(0, 0), 0, 0, EntityColor.getSpriteColors().get("Player"), getWindow());
=======
  public Enemy(int dmg, boolean canShoot, float size, float speed,
               Window scene, String level, int numSprites) {
    super(size, speed, scene);
    canFire = canShoot;

    if (level.contains("LVL_2"))
      delayAmount = 1000;
    else if (level.contains("LVL_5"))
      delayAmount = 500;
    else if (level.contains("LVL_10"))
      delayAmount = 250;

    setUp();

    setGm(new Gif(level, numSprites, getWindow(), this));
    enemyStat = new SpriteStat(this, 100, dmg);

>>>>>>> TD_01_javs
  }

  @Override
  public void setUp() {
    final PVector[] spawnPoint = {
      new PVector(window.random(0, window.width), window.height),
      new PVector(window.random(0, window.width), 0),
      new PVector(window.width, window.random(0, window.height)),
      new PVector(0, window.random(0, window.height))
    };

    PVector enemy = spawnPoint[(int) window.random(0, 4)];
    PVector enemyDirection = SpriteManager.calculateDirection(
            enemy, window.getPlayer().getPosition());

    setPosition(enemy);
    setDirection(enemyDirection);


    /* Initialize the shooting thread. */
    shootThread = new Thread(() -> {
      try {
        while (canFire && getEnemyStat().getHealth() > 0) {
          shoot();
          MusicManager.play(5);
          Thread.sleep(delayAmount);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void draw() {
    if (canFire && !shootThread.isAlive())
      shootThread.start();
    SpriteManager.assignGif(this, getGm());
  }

<<<<<<< HEAD
//  public void shoot(Window window) {
//    PVector dir = SpriteManager.calculateDirection(this.getPosition(), targetPlayer.getPosition());
//    Projectile projectile = new Projectile(this.getPosition().copy(), dir, 10, 5, EntityColor.getSpriteColors().get("EnemyProjectile"), window, this);
//    window.getPreloader().getDpC().getBullets().add(projectile);
//  }


  public void init(Enemy enemy) {}

  public void remove(Enemy enemy) {}

=======
  @Override
>>>>>>> TD_01_javs
  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

  /**
   * Shoot method for the enemy.
   * They get basic weapon because this game would be too
   * challenging.
   */
  public void shoot() {
    PVector enemyDirection = SpriteManager.calculateDirection(
            position, window.getPlayer().getPosition());

    Projectile projectile = new Projectile(position.copy(),
            enemyDirection, 10, 5, new Color(0xF32B2B),
            window, Weapons.getWeapon("Starter Laser"), 1);
    window.getPreloader().getDpC().getBullets().add(projectile);
  }

  /**
   * Gets the stats of the enemy.
   *
   * @return SpriteStat object.
   *
   */
  public SpriteStat getEnemyStat() {
    return enemyStat;
  }

}
