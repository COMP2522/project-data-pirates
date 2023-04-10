package org.example.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.example.gui.Hud;
import org.example.music.MusicManager;
import org.example.spriteClasses.Enemy;
import org.example.spriteClasses.Player;
import org.example.spriteClasses.Projectile;
import org.example.spriteClasses.Sprite;
import org.example.spriteClasses.SpriteManager;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;


/**
 * Behemoth of a program.
 * Data Pirates' functionality.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class WindowHelper {

  /* Min size of entities. */
  private static final int MINSIZE = 50;

  /* Max size of entities. */
  private static final int MAXSIZE = 150;

  /* Number of Enemies. */
  private int numEnemies = 1;

  /* World ID value. */
  private int world = 3;

  private Window window;

  private final Preloader preloader;

  /**
   * Construct the helper. Start the preloader.
   *
   * @param sketch the window which the helper is going to use.
   */
  public WindowHelper(Window sketch) {
    window = sketch;
    preloader = new Preloader(window);
  }

  /**
   * Check weather the player is dead. Health <= 0.
   *
   * @return true when Health <= 0, false otherwise.
   *
   */
  public boolean isPlayerDead() {
    return window.getPlayer().getPlayerStat().getHealth() <= 0;
  }

  /**
   * Used on death moment.
   */
  public void resetStates() {
    world = 3; /* Assign to title page. */
    numEnemies = 1; /* Reset counter. */
    preloader.getDpC().getTrash().addAll(preloader.getDpC().getBullets()); /* Remove Sprites 1. */
    preloader.getDpC().getTrash().addAll(preloader.getDpC().getSprites()); /* Remove Sprites 2. */
  }

  /**
   * Called within the draw function. Handles and performs such conditions to ensure
   * efficiency player and enemy elimination.
   */
  public void collideManager() {
    Player p = window.getPlayer();
    /* Bullet Vs Enemies */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    ArrayList<Sprite> conCurrentModHelper2 = new ArrayList<>(preloader.getDpC().getBullets());
    for (Sprite bullet : conCurrentModHelper2) {
      /* Enemy Bullet are weak. Lore of this game, the ship is built different. */
      if (bullet.collided(p) && ((Projectile) bullet).type == 1) {
        p.getPlayerStat().setHealth(p.getPlayerStat().getHealth() - 5);
        MusicManager.play(7);
        preloader.getDpC().getTrash().add(bullet);
      }
      for (Sprite sprite : conCurrentModHelper) {
        /* Your bullet vs enemies. */
        if (sprite instanceof Enemy && bullet.collided(sprite) && ((Projectile) bullet).type == 0) {
          ((Enemy) sprite).getEnemyStat().setHealth(
                  ((Enemy) sprite).getEnemyStat().getHealth() - p.getWeapon().getDmg());
          if (((Enemy) sprite).getEnemyStat().getHealth() <= 0) {
            preloader.getDpC().getRemove().put((Projectile) bullet, (Enemy) sprite);
            preloader.getScore().setValue(preloader.getScore().getValue() + 2);
          } else /* Hi, I am my name is Code Above! */
            preloader.getDpC().getTrash().add(bullet);
        }
        /* TODO {Note} : Code Above is for bullets going beyond bounds. */
      }
      if (bullet.getPosition().x >= Window.WIDTH || bullet.getPosition().y >= Window.HEIGHT)
        preloader.getDpC().getTrash().add(bullet);
    }

    /* Player colliding themselves to Enemies. */
    for (Sprite enemy : conCurrentModHelper)
      if (enemy instanceof Enemy)
        if (p.collided(enemy)) {
          p.getPlayerStat().setHealth(((Enemy) enemy).getEnemyStat().getCalculatedDamage(p));
          /* TODO: You kill it instantly! */
          MusicManager.play(7);
          ((Enemy) enemy).getEnemyStat().setHealth(0);
          preloader.getScore().setValue(preloader.getScore().getValue() + 1);
          preloader.getDpC().getTrash().add(enemy);
        }

    /* For each 'Bullet Vs Enemy' in 'Bullet colliding Enemy', remove it. */
    for (Map.Entry<Projectile, Enemy> mapElement : preloader.getDpC().getRemove().entrySet()) {
      removeBullet(mapElement.getKey());
      removeEnemies(mapElement.getValue());
    }

    /*
      Remove enemies that touched the player.
      Remove bullets that are out of bounds.
     */
    for (Sprite s : preloader.getDpC().getTrash()) {
      if (s instanceof Projectile)
        removeBullet(s);
      else if (s instanceof Enemy)
        removeEnemies(s);
    }
  }

  /**
   * Removes an enemy in the enemy
   * and sprite list.
   *
   * @param s Enemy as a Sprite object.
   */
  public void removeEnemies(Sprite s) {
    ((Enemy) s).getEnemyStat().setHealth(0);
    preloader.getDpC().getSprites().remove(s);
  }

  /**
   * Remove both enemies and bullets.
   * Used for resetting stats. (Death moment)
   *
   * @param s Sprite entity.
   *
   */
  public void removeEnemiesNBullets(Sprite s) {
    if (s instanceof Enemy)
      removeEnemies(s);
    else if (s instanceof Projectile)
      removeBullet(s);
  }

  /**
   * Removes a bullet from the bullet
   * and sprite list.
   *
   * @param b Bullet as a Sprite object.
   *
   */
  public void removeBullet(Sprite b) {
    preloader.getDpC().getBullets().remove(b);
    preloader.getDpC().getSprites().remove(b);
  }

  /**
   * Display enemies. Adds a random amount of enemies to sprite.
   */
  public void showEnemies() {

    /*
      Spawn Enemies.
      Randomize the amount of enemies.
    */
    for (int i = 0; i < (Preloader.RNG.nextInt(numEnemies) + 1); i++) {
      new Thread(() -> {
        /* In between position of player's bounds and enemy bounds */

        /* TODO: Level 1, the Iron Sucker. */
        Sprite e = null;
        if (preloader.getScore().getValue() < 100) {
          e = new Enemy(
                  15,
                  false,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(0, 2),
                  window, "LVL_1\\frame ", 36
          );
        } else if (preloader.getScore().getValue() >= 100
                && preloader.getScore().getValue() < 1000) {
          /* TODO: Level 2, 4th Wall's lil machines. */
          e = new Enemy(
                  25,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(1, 4),
                  window, "LVL_2\\frame ", 6
          );
        } else if (preloader.getScore().getValue() >= 1000
                && preloader.getScore().getValue() < 5000) {
          /* TODO: Level 5, Error Glitches. */
          e = new Enemy(
                  45,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(5, 10),
                  window, "LVL_5\\frame ", 9
          );
        } else if (preloader.getScore().getValue() >= 5000) {
          /* TODO: Level 10, HIM's creations. */
          e = new Enemy(
                  75,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(8, 12),
                  window, "LVL_10\\frame ", 29
          );
        }

        preloader.getDpC().getSprites().add(e);
      }).start();
    }
  }

  /**
   * Renders the current locations.
   *
   * <p>
   *   Worlds (May contain the deleted concepts)<br>
   *          0 -> Arena<br>
   *          1 -> Shop<br>
   *          2 -> Daily Chest<br>
   *          3 -> Menu<br>
   *          4 -> Kathy (Boss Room)
   * </p>
   *
   * @param worldID - current world value.
   *
   */
  public void renderNewLocation(int worldID) {

    /*
      Procedure: stop and reset the timer. Delete the enemies.
      Every room except the arena room (world 0) is a safe room.
    */
    if (world != 0) {
      preloader.getClock().stop();
      preloader.getClock().reset();
      for (Sprite s : preloader.getDpC().getSprites())
        if (s instanceof Enemy)
          preloader.getDpC().getTrash().add(s);

      for (Sprite s : preloader.getDpC().getTrash())
        removeEnemiesNBullets(s);

    }

    Hud hudStatus = preloader.getDp_Hud();
    if (worldID == 3) {
      preloader.getSaferoom().renderLocation();
      hudStatus.setMenuHudStatus(true);
      hudStatus.setHudStatus(false);
    }
  }

  /**
   * The behemoth steps of update().
   */
  public void updateFrame() {

    /* Bullet sprites. */
    ArrayList<Sprite> bulletConCurrentModHelper = new ArrayList<>(preloader.getDpC().getBullets());
    for (Sprite s : bulletConCurrentModHelper) {
      if (preloader.getDp_Hud().isEntityBorderActive())
        s.displayBoundaries();
      s.draw();
      s.update();
    }

    /* All sprites except bullets. */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    for (Sprite s : conCurrentModHelper) {
      if (preloader.getDp_Hud().isEntityBorderActive())
        s.displayBoundaries();
      s.update();
      s.draw();
      if (s instanceof Enemy) {
        PVector dir = SpriteManager.calculateDirection(
                s.getPosition(),
                window.getPlayer().getPosition());
        s.setDirection(dir);
      }
    }
    collideManager();
  }

  /**
   * Start the wave.
   */
  public void beginTheBattle() {
    if (world == 0) {
      preloader.getGifBackground().displayBackground();
      preloader.getDp_Hud().setMenuHudStatus(false);
      if (preloader.getClock().isStopped()
              && (preloader.getDpC().getSprites()
              .stream().noneMatch(n -> n instanceof Enemy))) {
        /*
          TODO: For each wave, clean the previous trash to prevent performance issues.
        */
        preloader.getDpC().setTrash(new ArrayList<Sprite>());
        preloader.getDpC().setRemove(new HashMap<Projectile, Enemy>());
        numEnemies += window.random(0, 8);
        showEnemies();
        preloader.getClock().start();
      }
      preloader.getClock().stop();
    }

    preloader.getDp_Hud().updateFrame();
  }

  /**
   * By the words.
   * Shoot.
   */
  public void shootFunction() {
    Player player = window.getPlayer();
    if (player.getWeapon().hasAmmo()) {
      player.getWeapon().shoot();
      /* Play shooting sounds. */
      MusicManager.play(4);

      /*
        TODO: Enables the player to shoot at any direction.
        src: https://processing.org/tutorials/pvector/#vectors-interactivity
      */

      PVector mouse = new PVector(window.mouseX, window.mouseY);

      PVector dir = SpriteManager
              .calculateDirection(player.getPosition(), mouse);

      Projectile projectile = new Projectile(player.getPosition().copy(),
              dir, 10, 5, player.getWeapon().getBulletColor(), window, player.getWeapon(), 0);
      preloader.getDpC().getBullets().add(projectile);
    }
  }

  /**
   * The behemoth steps of keyPressed.
   *
   * @param e keyEvent, from keyboard.
   *
   */
  public void keyPressedHandler(KeyEvent e) {
    Player player = window.getPlayer();

    switch (e.getKey()) {
      case 'w' -> player.getDiag().setUpPressed(true);
      case 's' -> player.getDiag().setDownPressed(true);
      case 'a' -> player.getDiag().setLeftPressed(true);
      case 'd' -> player.getDiag().setRightPressed(true);
      /* Fast reload is on in this game. */
      case 'r' -> player.getWeapon().reload();
      /* Show boundaries. */
      case 'q' ->  preloader.getDp_Hud().setEntityBorderStatus(
              !preloader.getDp_Hud().isEntityBorderActive());
      case 'c' -> {
        if (world == 0)
          preloader.getDp_Hud().setHudStatus(!preloader.getDp_Hud().isHudActive());
      }
      default -> {
        /* Space. */
        if (e.getKeyCode() == 32)
          shootFunction();
      }
    }
  }

  /**
   * The behemoth steps of keyReleased.
   *
   * @param e keyEvent, from keyboard.
   *
   */
  public void keyReleasedHandler(KeyEvent e) {
    Player player = window.getPlayer();
    switch (e.getKey()) {
      case 'w' -> player.getDiag().setUpPressed(false);
      case 's' -> player.getDiag().setDownPressed(false);
      case 'a' -> player.getDiag().setLeftPressed(false);
      case 'd' -> player.getDiag().setRightPressed(false);
      default -> { }
    }
  }

  /* TODO: Getters and Setters beyond this point. */

  public PApplet getWindow() {
    return window;
  }

  public int getWorld() {
    return world;
  }

  public void setWorld(int world) {
    this.world = world;
  }

  public void setWindow(Window window) {
    this.window = window;
  }

  public Preloader getPreloader() {
    return preloader;
  }
}
