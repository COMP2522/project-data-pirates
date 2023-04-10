package org.example.Main;

import org.example.music.MusicManager;
import org.example.spriteClasses.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * <h1>Data Pirates, A COMP 2522 Game!</h1>
 * <p>
 *   <strong>About</strong><br>
 *   Data Pirates is a survival game where you have to
 *   defend yourself against these space data creatures.
 *   <br>
 *   Data Pirates can be considered as a playground.
 *   The game is designed to be a bit challenging.
 *   However, it is still quite fun.
 *   <br><br>
 * </p>
 * @author The Team:
 *     Eric G,
 *     Ning,
 *     Jack,
 *     Teddy D
 *
 * @version JDK 18.
 */
public class Window extends PApplet {

  /* Only one player allowed. Single player. */
  protected static Player player;

  /*
    Screen's Width.
  */
  public static final int WIDTH = 1080;

  /*
    Screen's Height.
  */
  public static final int HEIGHT = 720;

  /*
    Preloader Class.
  */
  private Preloader preloader;

  /*
    The "Functions" class.
  */
  private WindowHelper wH;

  /**
   * Settings of the PApplet program.
   */
  public void settings() {
    size(WIDTH, HEIGHT);
  }

  @Override
  public void exit() {
    super.exit();
  }

  /**
   * Setup version 2.
   * Had to be in a separate class because Preloader uses Thread.
   * And this can throw an error because one of the values is not initialized.
   */
  public void getResource() {
    init();
    preloader.getClock().start();
    frameRate(100);
  }

  /**
   * Sets the game mechanics.
   */
  public void setup() {
    PImage icon = loadImage("datapirates\\src\\main\\java\\org\\example\\static_assets\\icon.png");
    surface.setTitle("Data Pirates");
    surface.setIcon(icon);
    wH = new WindowHelper(this);
    preloader = wH.getPreloader();
  }

  /**
   * Set player.
   */
  public void init() {
    preloader.getScore().setValue(0);
    player = Player.getInstance(
            new PVector((float) (WIDTH / 2), (float) (HEIGHT / 2)),
            new PVector(0, 1),
            75,
            5,
            EntityColor.getSpriteColors().get("Player"),
            this);

    preloader.getDpC().getSprites().add(player);
  }

  /**
   * Triggers when a key is pressed.
   * Spam bullet is now a feature.
   *
   * @param e KeyEvent, key input.
   *
   */
  @Override
  public void keyPressed(KeyEvent e) {
    /*
      If preloader is done, this function is enabled.
    */
    if (preloader.isFinished())
      wH.keyPressedHandler(e);
  }

  /**
   * Included this function to help smooth player movement.
   *
   * @param e keyboard input.
   *
   */
  @Override
  public void keyReleased(KeyEvent e) {
    /*
      Meaning, this function will only activate when the preloader is finished.
    */
    if (preloader.isFinished())
      wH.keyReleasedHandler(e);

  }

  /**
   * Calls when a MouseEvent has been triggered.
   * Specifically, clicking.
   */
  @Override
  public void mousePressed() {
    if (preloader.isFinished()) {
      if (wH.getWorld() == 3) {
        preloader.getMenu().update();
      }
      wH.shootFunction();
    }
  }


  /**
   * Threads... every frame.
   */
  public void draw() {
    /*
      TODO: If preloader is not finished loading everything
        display loading background. Duh.
    */
    if (!preloader.isFinished()) {
      if (preloader.getLoadingImage() != null)
        preloader.getLoadingImage().displayBackground();
    } else {
      wH.setWindow(this);
      /* If player dies. */
      if (wH.isPlayerDead()) {
        wH.resetStates();
        init();
      }

      /* Placeholder for travel function. */
      preloader.getSaferoom().travel();

      /*
        player.move() is placed here to ensure smooth player movement every frame.
      */
      player.move();

      /*
        For every world set the building blocks.
      */
      wH.renderNewLocation(wH.getWorld());

      /*
        Play the background music.
      */
      if (MusicManager.getObjectType() != wH.getWorld()) {
        if (MusicManager.isPlaying())
          MusicManager.stop();
        MusicManager.play(wH.getWorld());
      }

      /* Alternate Name: Start next wave. */
      wH.beginTheBattle();

      /* The compact function of draw and update. */
      wH.updateFrame();

      /* Warns player to reload. */
      if (!player.getWeapon().hasAmmo()) {
        textSize(60);
        fill(EntityColor.getSpriteColors().get("Reload").getRed(),
                EntityColor.getSpriteColors().get("Reload").getGreen(),
                EntityColor.getSpriteColors().get("Reload").getBlue());
        text("Reload!", player.getPosition().x + player.getSize(),
                player.getPosition().y + player.getSize());
      }
    }
  }

  /**
<<<<<<< HEAD
   * Called within the draw function. Handles and performs such conditions to ensure
   * efficiency player and enemy elimination.
   */
  public void collideManager() {
    /* Bullet Vs Enemies */
    /* Fixed Issue for ConcurrentModification. TODO {Warning} Duplicate Code! */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    ArrayList<Sprite> conCurrentModHelper2 = new ArrayList<>(preloader.getDpC().getBullets());
    for (Sprite bullet : conCurrentModHelper2)
      for (Sprite enemy : conCurrentModHelper) {
        if (enemy instanceof Enemy && bullet.collided(enemy)) {
          ((Enemy) enemy).getEnemyStat().setHealth(((Enemy) enemy).getEnemyStat().getHealth() - player.getWeapon().getDmg());
          if (((Enemy) enemy).getEnemyStat().getHealth() <= 0) {
            System.out.println("Killed an enemy!");
            Player.setBalance(Player.getBalance() + 2);
            preloader.getDpC().getRemove().put((Projectile) bullet, (Enemy) enemy);
            preloader.getScore().setValue(preloader.getScore().getValue() + 2);
          } else
            preloader.getDpC().getTrash().add(bullet);
        } else if (bullet.getPosition().x >= this.width || bullet.getPosition().y >= this.height)
          preloader.getDpC().getTrash().add(bullet);
        /* TODO {Note} : Code above is for bullets going beyond bounds. */
      }


    /* Player colliding themselves to Enemies. */
    for (Sprite enemy : conCurrentModHelper)
      if (enemy instanceof Enemy)
        if (!(player.getPlayerStat().getHealth() <= 0)) {
          if (player.collided(enemy)) {
            player.getPlayerStat().setHealth(((Enemy) enemy).getEnemyStat().getCalculatedDamage(player));
//            ((Enemy) enemy).getEnemyStat().setHealth(pl); /* Calculated Damage */
            preloader.getScore().setValue(preloader.getScore().getValue() + 1);
            preloader.getDpC().getTrash().add(enemy);
            System.out.println("collided with enemy");
          }
        } else {
          world = 3;
          numEnemies = 1;
          init();
        }

    /* For each 'Bullet Vs Enemy' in 'Bullet colliding Enemy', remove it. */
    for (Map.Entry<Projectile, Enemy> mapElement: preloader.getDpC().getRemove().entrySet()) {
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
    preloader.getDpC().getSprites().remove(s);
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
=======
>>>>>>> TD_01_javs
   * Gets the player, only this class has the player.
   *
   * @return player.
   *
   * @type <!-- Ignore Wrong tag --> Important Getter.
   *
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Gets the Preloader, only this class has the Preloader.
   *
   * @return the preloader.
   *
   * @type <!-- Ignore Wrong tag --> Important Getter.
   *
   */
  public Preloader getPreloader() {
    return preloader;
  }

  /**
   * Gets the world number.
   * Used for scene generation.
   *
   * @return world.
   *
   */
  public int getWorld() {
    return wH.getWorld();
  }

  /**
   * Sets the world number.
   * Used when the player moves into specific places.
   *
   * @param world number, worldID.
   *
   */
  public void setWorld(int world) {
    wH.setWorld(world);
  }

  /**
   * Drives the program.
   *
   * @param args unused.
   *
   */
  public static void main(String[] args) {
    PApplet.main("org.example.Main.Window");
  }
}

