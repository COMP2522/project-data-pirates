package org.example.Main;

import org.example.gui.Hud;
import org.example.spriteClasses.*;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

  private Preloader preloader ;

  public WindowHelper(Window sketch) {
    window = sketch;
    preloader = new Preloader((Window) window);
  }

  public boolean isPlayerDead() {
    return ((Window) window).getPlayer().getPlayerStat().getHealth() <= 0;
  }

  public void resetStates() {
    world = 3;
    numEnemies = 1;
    preloader.getDpC().getTrash().addAll(preloader.getDpC().getBullets());
    preloader.getDpC().getTrash().addAll(preloader.getDpC().getSprites());
  }

  /**
   * Called within the draw function. Handles and performs such conditions to ensure
   * efficiency player and enemy elimination.
   */
  public void collideManager() {
    Player p = ((Window) window).getPlayer();
    /* Bullet Vs Enemies */
    /* Fixed Issue for ConcurrentModification. TODO {Warning} Duplicate Code! */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    ArrayList<Sprite> conCurrentModHelper2 = new ArrayList<>(preloader.getDpC().getBullets());
    for (Sprite bullet : conCurrentModHelper2) {
      if (bullet.collided(p) && ((Projectile) bullet).type == 1) {
        p.getPlayerStat().setHealth(p.getPlayerStat().getHealth() - 10);
        System.out.println("Ouch!");
        preloader.getDpC().getTrash().add(bullet);
      }
      for (Sprite sprite : conCurrentModHelper) {
        if (sprite instanceof Enemy && bullet.collided(sprite) && ((Projectile) bullet).type == 0) {
          ((Enemy) sprite).getEnemyStat().setHealth(((Enemy) sprite).getEnemyStat().getHealth() - p.getWeapon().getDmg());
          if (((Enemy) sprite).getEnemyStat().getHealth() <= 0) {
            System.out.println("Killed an sprite!");
            Player.setBalance(Player.getBalance() + 2);
            preloader.getDpC().getRemove().put((Projectile) bullet, (Enemy) sprite);
            preloader.getScore().setValue(preloader.getScore().getValue() + 50);
          } else
            preloader.getDpC().getTrash().add(bullet);
        }
        /* TODO {Note} : Code above is for bullets going beyond bounds. */
      }
      if (bullet.getPosition().x >= Window.WIDTH || bullet.getPosition().y >= Window.HEIGHT)
        preloader.getDpC().getTrash().add(bullet);
    }


    /* Player colliding themselves to Enemies. */
    for (Sprite enemy : conCurrentModHelper)
      if (enemy instanceof Enemy)
        if (p.collided(enemy)) {
          p.getPlayerStat().setHealth(((Enemy) enemy).getEnemyStat().getCalculatedDamage(p));
//            ((Enemy) enemy).getEnemyStat().setHealth(pl); /* Calculated Damage */
          /* For now make the enemy lose their health. */
          ((Enemy) enemy).getEnemyStat().setHealth(0);
          preloader.getScore().setValue(preloader.getScore().getValue() + 1);
          preloader.getDpC().getTrash().add(enemy);
          System.out.println("collided with enemy");
        }


    /* For each 'Bullet Vs Enemy' in 'Bullet colliding Enemy', remove it. */
    for (Map.Entry<Projectile, Enemy> mapElement : preloader.getDpC().getRemove().entrySet()) {
      removeBullet(mapElement.getKey());
      removeEnemies(mapElement.getValue());
    }

    /*
      Remove enemies that touched the player.
      Remove bullets that are out of bounds.
      See the TODO above.
     */
    for (Sprite s : preloader.getDpC().getTrash()) {
      if (s instanceof Projectile)
        removeBullet(s);
//      else if (s instanceof Player)
//        preloader.getDpC().getSprites().remove(s);
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
  public void removeEnemies (Sprite s){
    ((Enemy) s).getEnemyStat().setHealth(0);
    preloader.getDpC().getSprites().remove(s);
  }

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

        Sprite e = null;
        if (preloader.getScore().getValue() < 100) {
          e = new Enemy(
                  15,
                  false,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(0, 2),
                  ((Window) window), "LVL_1\\frame ", 36
          );
        } else if (preloader.getScore().getValue() >= 100 && preloader.getScore().getValue() < 1000) {
          e = new Enemy(
                  25,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(1, 4),
                  ((Window) window), "LVL_2\\frame ", 6
          );
        } else if (preloader.getScore().getValue() >= 1000 && preloader.getScore().getValue() < 5000) {
          e = new Enemy(
                  45,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(5, 10),
                  ((Window) window), "LVL_5\\frame ", 9
          );
        } else if (preloader.getScore().getValue() >= 5000) {
          e = new Enemy(
                  75,
                  true,
                  window.random(MINSIZE, MAXSIZE),
                  window.random(8, 12),
                  ((Window) window), "LVL_10\\frame ", 29
          );
        }

        preloader.getDpC().getSprites().add(e);
      }).start();
    }
  }

  /**
   * Renders the current locations.
   *
   * @Worlds: <!-- Ignore Wrong Tags -->
   *      0 -> Arena <br>
   *      1 -> Shop <br>
   *      2 -> Daily Chest <br>
   *      3 -> Menu <br>
   *      4 -> Kathy
   * @param worldID - current world value
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
    /* TODO {Issue}: Change the names of returnToOrigin() to loadLocation() */
    switch (worldID) {
      case 3:
        preloader.getSaferoom().renderLocation();
        hudStatus.setMenuHudStatus(true);
        hudStatus.setHudStatus(false);
        break;
      default:
        break;
    }
  }

  public void updateFrame() {

          /*
        TODO: This is placed here to prevent the ConCurrentMod Exception. Because preloader.getSprites() has player
          and it is added everytime err.. see the next -> */ /* TODO.

     */
//      player.update();
//      player.draw();
    ArrayList<Sprite> bulletConCurrentModHelper = new ArrayList<>(preloader.getDpC().getBullets());
//    System.out.println(bulletConCurrentModHelper.size());
    for (Sprite s : bulletConCurrentModHelper) {
      if (preloader.getDp_Hud().isEntityBorderActive())
        s.showBorders();
      s.draw();
      s.update();
    }

      /*
        Foreach method removed because new enemies appear using thread (to avoid delays from loading gifs)
        will modify the ArrayList.
        TODO: ConCurrentMod Handler.
       */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    for (Sprite s : conCurrentModHelper) {
//        if (!(s instanceof Player)) {
      if (preloader.getDp_Hud().isEntityBorderActive())
        s.showBorders();
      s.update();
      s.draw();
      /* TODO: Show the enemy health. */
      if (s instanceof Enemy) {
        PVector dir = SpriteManager.calculateDirection(s.getPosition(), ((Window) window).getPlayer().getPosition());
        s.setDirection(dir);
      }
    }
    collideManager();
  }
  public void beginTheBattle() {
    if (world == 0) {
      preloader.getGifBackground().displayBackground();
      preloader.getDp_Hud().setMenuHudStatus(false);
      if (preloader.getClock().isStopped() && (preloader.getDpC().getSprites().stream().noneMatch(n -> n instanceof Enemy))) {
          /*
            TODO: For each wave, clean the previous trash to prevent performance issues.
          */
        preloader.getDpC().setTrash(new ArrayList<Sprite>());
        preloader.getDpC().setRemove(new HashMap<Projectile, Enemy>());
        numEnemies += window.random(0, 8);
        showEnemies();
//        setUpEnemies();
        preloader.getClock().start();
      }
      preloader.getClock().stop();

    }
  }

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
