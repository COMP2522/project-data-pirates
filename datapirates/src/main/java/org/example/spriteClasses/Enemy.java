package org.example.spriteClasses;

import java.awt.Color;

import org.example.Main.Items;
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

  private boolean canShoot;

  private Thread shootDelays;

  private int delayAmount;

  /**
   * Constructor. Sets a normal enemy with
   * the desired parameters.
   *
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param scene The Window or GUI where the sprite will appear.
   */
  public Enemy(int dmg, boolean canShoot, float size, float speed,  Window scene, String level, int numSprites) {
    super(size, speed, scene);
    // use map to get the HP, DEF, and DMG of an enemy
    PVector locations[] = {
            new PVector(scene.random(0, scene.width), scene.height),
            new PVector(scene.random(0, scene.width), 0),
            new PVector(scene.width, scene.random(0, scene.height)),
            new PVector(0, scene.random(0, scene.height))
    };
    if (level.contains("LVL_2"))
      delayAmount = 1000;
    else if (level.contains("LVL_5"))
      delayAmount = 500;
    else if (level.contains("LVL_10"))
      delayAmount = 250;

    this.canShoot = canShoot;

    PVector enemy = locations[(int) scene.random(0, 4)];
    PVector enemyDirection = SpriteManager.calculateDirection(enemy, scene.getPlayer().getPosition());

    setPosition(enemy);
    setDirection(enemyDirection);
    setMm(new GifManager(level, numSprites, getPosition(), getWindow(), this));
    enemyStat = new SpriteStat(this, 100, 25, dmg);

    shootDelays = new Thread(() -> {
      try {
        while (canShoot && getEnemyStat().getHealth() > 0) {
          shoot();
          scene.getPreloader().getMusic().play(5);
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  public SpriteStat getEnemyStat() {
    return enemyStat;
  }

  @Override
  public void draw() {
//    showBorders();
    if (canShoot && !shootDelays.isAlive())
      shootDelays.start();
    SpriteManager.assignSprite(this, getMm());
  }

  public void shoot() {
//    PVector mouse = new PVector(mouseX, mouseY);

        /* TODO {Note}: There is a function for
            this but I did not know that until few weeks I did this. */
    PVector enemyDirection = SpriteManager.calculateDirection(position, window.getPlayer().getPosition());

    Projectile projectile = new Projectile(position.copy(),
            enemyDirection, 10, 5, new Color(0xF32B2B), window, Items.getWeapon("Starter Laser"), 1);
    window.getPreloader().getDpC().getBullets().add(projectile);
  }
//      scene.image(frameImages[frames], s.getPosition().x - s.getSize(), s.getPosition().y, s.getSize(), s.getSize());


  public void init(Enemy enemy) {}

  public void remove(Enemy enemy) {}

  public void update() {
    this.position = this.getPosition().add(this.getDirection().copy().mult(getSpeed()));
  }

}
