package org.example.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.example.spriteClasses.Enemy;
import org.example.spriteClasses.Player;
import org.example.spriteClasses.Projectile;
import org.example.spriteClasses.Sprite;
import org.example.spriteClasses.SpriteManager;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;



/**
 * Data Pirates.
 *
 * @author The Team:
 *     Eric G,
 *     Ning,
 *     Jack,
 *     Teddy D
 *
 * @version JDK 18
 *     Documentary:
 *      Smooth Player Movement -> Diagonal Movement, don't have to press key again to move entirely.
 */
public class Window extends PApplet {


  /* Min size of entities. */
  private static final int MINSIZE = 50;

  /* Max size of entities. */
  private static final int MAXSIZE = 150;

  /* Only one player allowed. Single player. */
  protected static Player player;

  /* Number of Enemies. */
  private int numEnemies = 1;

  /* World ID value. */
  private int world = 3;

  /* Player stat HUD. */
  private Boolean isHudActive = false;

  /*
    Screen's Width.
  */
  final int width = 1080;

  /*
    Screen's Height.
  */
  final int height = 720;

  /*
    Menu HUD.
  */
  private boolean menuHudStatus = true;

  /*
    Entity Border Status.
  */
  private boolean entityBorderStatus = false;
  /*
    Preloader Class.
  */
  private Preloader preloader;

  /**
   * Settings of the PApplet program.
   */
  public void settings() {
    size(width, height);
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
    surface.setTitle("Data Pirates");
    preloader = new Preloader(this);
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
                  random(MINSIZE, MAXSIZE),
                  random(0, 2),
                  this, "LVL_1\\frame ", 36
          );
        } else if (preloader.getScore().getValue() >= 100 && preloader.getScore().getValue() < 1000) {
          e = new Enemy(
                  25,
                  true,
                  random(MINSIZE, MAXSIZE),
                  random(1, 4),
                  this, "LVL_2\\frame ", 6
          );
        } else if (preloader.getScore().getValue() >= 1000 && preloader.getScore().getValue() < 5000) {
          e = new Enemy(
                  45,
                  true,
                  random(MINSIZE, MAXSIZE),
                  random(5, 10),
                  this, "LVL_5\\frame ", 9
          );
        } else if (preloader.getScore().getValue() >= 5000) {
          e = new Enemy(
                  75,
                  true,
                  random(MINSIZE, MAXSIZE),
                  random(8, 12),
                  this, "LVL_10\\frame ", 29
          );
        }

        preloader.getDpC().getSprites().add(e);
      }).start();
    }
  }

  /**
   * Sets extra important things.
   *
   */
  public void init() {
    preloader.getScore().setValue(0);
    player = Player.getInstance(
            new PVector((float) (this.width / 2), (float) (this.height / 2)),
            new PVector(0, 1),
            75,
            5,
            EntityColor.getSpriteColors().get("Player"),
            this);

    preloader.getDpC().getSprites().add(player);
  }

  /**
   * Triggers when a key is pressed.
   *
   * @param e KeyEvent, key input.
   */
  @Override
  public void keyPressed(KeyEvent e) {

    /*
      If preloader is done, this function is enabled.
    */
    if (preloader.isFinished()) {
      if (e.getKeyCode() == 32)
        shootFunction();

      if (e.getKey() == 'r') /* Delay on Reload */
        player.getWeapon().reload();
      else if (e.getKey() == 'c' && world == 0)
        isHudActive = !isHudActive;
      else if (e.getKey() == 'q') {
        /* Show boundaries. */
        entityBorderStatus = !entityBorderStatus;
      } else if (e.getKey() == 'e' && preloader.getMenu().isINPanelActive())
        preloader.getMenu().setInstructionPanel(false);
      if (e.getKey() == 'e' && world == 2)
        preloader.getCchest().getChest().setOpened(!preloader.getCchest().getChest().isOpened());

      /* Thank you for changing that IntelliJ, that is clean. */
      switch (e.getKey()) {
        case 'w' -> player.getDiag().setUpPressed(true);
        case 's' -> player.getDiag().setDownPressed(true);
        case 'a' -> player.getDiag().setLeftPressed(true);
        case 'd' -> player.getDiag().setRightPressed(true);
        default -> { }
      }
    }

  }


  /**
   * Keys that should not be hold.
   * @param event
   */
  @Override
  public void keyTyped(KeyEvent event) {

//    super.keyTyped(event)

  }

  /**
   * Included this function to help smooth player movement.
   * @param e keyboard input
   *
   */
  @Override
  public void keyReleased(KeyEvent e) {
    /*
      Meaning, this function will only activate when the preloader is finished.
    */
    if (preloader.isFinished())
      switch (e.getKey()) {
        case 'w' -> player.getDiag().setUpPressed(false);
        case 's' -> player.getDiag().setDownPressed(false);
        case 'a' -> player.getDiag().setLeftPressed(false);
        case 'd' -> player.getDiag().setRightPressed(false);
        default -> {
        }
      }
  }

  /**
   * Calls when a MouseEvent has been triggered.
   * Specifically, clicking.
   */
  @Override
  public void mousePressed() {
    if (preloader.isFinished()) {
      if (world == 3) {
        preloader.getMenu().update();
      }
      shootFunction();
    }
  }

  public void shootFunction() {
    if (player.getWeapon().hasAmmo()) {
      player.getWeapon().shoot();
//        preloader.getMusic().p

        /*
          TODO: Enables the player to shoot at any direction.
          src: https://processing.org/tutorials/pvector/#vectors-interactivity
        */

      PVector mouse = new PVector(mouseX, mouseY);

        /* TODO {Note}: There is a function for
            this but I did not know that until few weeks I did this. */
      PVector dir = SpriteManager
              .calculateDirection(player.getPosition(), mouse);

      Projectile projectile = new Projectile(player.getPosition().copy(),
              dir, 10, 5, player.getWeapon().getBulletColor(), this, player.getWeapon(), 0);
      preloader.getDpC().getBullets().add(projectile);
    } else {
      textSize(60);
      fill(EntityColor.getSpriteColors().get("Reload").getRed(),
              EntityColor.getSpriteColors().get("Reload").getGreen(),
              EntityColor.getSpriteColors().get("Reload").getBlue());
      text("Reload!", player.getPosition().x + player.getSize(),
              player.getPosition().y + player.getSize());
    }
  }


  /**
   * Get the information of your stats and the wave time.
   */
  public void printDisplayText() {

    final int scoreText = 20;
    final int playerTextSize = scoreText + 10;
    /*
      The score
    */
    textSize(scoreText);
    Color textColor = EntityColor.getSpriteColors().get("Text");
    fill(textColor.getRGB());

    text(preloader.getScore().getValue() + "pts", 0, scoreText);


    /*
      Health Points.
    */
    textSize(playerTextSize);
    fill(textColor.getRGB());
    text("HP " + player.getPlayerStat().getHealth()
            + "/" + player.getPlayerStat().getMaxHealth(),
            0, height - playerTextSize - playerTextSize);

    /*
      Ammo Capacity.
    */
    fill(textColor.getRGB());
    text("AMMO " + player.getWeapon().getCurrentAmmo()
            + "/" + player.getWeapon().getAmmoCapacity()
            + "", 0, height - playerTextSize);

    /*
      Wave Time.
    */
    fill(textColor.getRGB());
    text("Next: " + (int) preloader.getClock().getEstimated()
            + "s", 0, playerTextSize + playerTextSize);

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
        removeEnemies(s);
    }

    /* TODO {Issue}: Change the names of returnToOrigin() to loadLocation() */
    if (worldID == 1) {
      preloader.getShop().renderLocation();
      menuHudStatus = false;
    }
    else if (worldID == 2) {
      preloader.getCchest().renderLocation();
      menuHudStatus = false;
    }
    else if (worldID == 3) {
      isHudActive = false;
      menuHudStatus = true;
      preloader.getSaferoom().renderLocation();
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
//        System.out.println(preloader.isFinished());
    if (!preloader.isFinished()) {
      if (preloader.getLoadingImage() != null)
        preloader.getLoadingImage().displayBackground();
    }
    else {
      /* If player dies. */
      if (player.getPlayerStat().getHealth() <= 0) {
        world = 3;
        numEnemies = 1;
        preloader.getDpC().getTrash().addAll(preloader.getDpC().getBullets());
        preloader.getDpC().getTrash().addAll(preloader.getDpC().getSprites());
//
//
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
      renderNewLocation(world);

      /*
        Play the background music.
      */
      if (!preloader.getMusic().isPlaying()) {
        preloader.getMusic().play(world);
      }

      /*
        Menu HUD. Contains the Play, Settings, Load Data, Exit buttons.
      */
      if (menuHudStatus)
        preloader.getMenu().drawAtSafe();
//        menu.drawAtSafe();


      if (world == 0) {
        menuHudStatus = false;
        preloader.getGifBackground().displayBackground();
        if (preloader.getClock().isStopped() && (preloader.getDpC().getSprites().stream().noneMatch(n -> n instanceof Enemy))) {
          /*
            TODO: For each wave, clean the previous trash to prevent performance issues.
          */
          preloader.getDpC().setTrash(new ArrayList<Sprite>());
          preloader.getDpC().setRemove(new HashMap<Projectile, Enemy>());
          numEnemies += random(0, 8);
          showEnemies();
//        setUpEnemies();
          preloader.getClock().start();
        }
        preloader.getClock().stop();

      }

      if (isHudActive) {
        printDisplayText();
      }
      /*
        TODO: This is placed here to prevent the ConCurrentMod Exception. Because preloader.getSprites() has player
          and it is added everytime err.. see the next -> */ /* TODO.

      */
//      player.update();
//      player.draw();

      ArrayList<Sprite> bulletConCurrentModHelper = new ArrayList<>(preloader.getDpC().getBullets());
      for (Sprite s : bulletConCurrentModHelper) {
        if (entityBorderStatus)
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
          if (entityBorderStatus)
            s.showBorders();
          s.update();
          s.draw();
          /* TODO: Show the enemy health. */
          if (s instanceof Enemy) {
            PVector dir = SpriteManager.calculateDirection(s.getPosition(), player.getPosition());
            s.setDirection(dir);
          }
        }
//      }
      collideManager();
    }
  }

  /**
   * Called within the draw function. Handles and performs such conditions to ensure
   * efficiency player and enemy elimination.
   */
  public void collideManager() {
    /* Bullet Vs Enemies */
    /* Fixed Issue for ConcurrentModification. TODO {Warning} Duplicate Code! */
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>(preloader.getDpC().getSprites());
    ArrayList<Sprite> conCurrentModHelper2 = new ArrayList<>(preloader.getDpC().getBullets());
    for (Sprite bullet : conCurrentModHelper2) {
      if (bullet.collided(player) && ((Projectile) bullet).type == 1) {
        player.getPlayerStat().setHealth(player.getPlayerStat().getHealth() - 10);
        System.out.println("Ouch!");
        preloader.getDpC().getTrash().add(bullet);
      }
      for (Sprite sprite : conCurrentModHelper) {
        if (sprite instanceof Enemy && bullet.collided(sprite) && ((Projectile) bullet).type == 0) {
          ((Enemy) sprite).getEnemyStat().setHealth(((Enemy) sprite).getEnemyStat().getHealth() - player.getWeapon().getDmg());
          if (((Enemy) sprite).getEnemyStat().getHealth() <= 0) {
            System.out.println("Killed an sprite!");
            Player.setBalance(Player.getBalance() + 2);
            preloader.getDpC().getRemove().put((Projectile) bullet, (Enemy) sprite);
            preloader.getScore().setValue(preloader.getScore().getValue() + 2);
          } else
            preloader.getDpC().getTrash().add(bullet);
        }
        /* TODO {Note} : Code above is for bullets going beyond bounds. */
      }
    if (bullet.getPosition().x >= this.width || bullet.getPosition().y >= this.height)
      preloader.getDpC().getTrash().add(bullet);
  }


    /* Player colliding themselves to Enemies. */
    for (Sprite enemy : conCurrentModHelper)
      if (enemy instanceof Enemy)
          if (player.collided(enemy)) {
            player.getPlayerStat().setHealth(((Enemy) enemy).getEnemyStat().getCalculatedDamage(player));
//            ((Enemy) enemy).getEnemyStat().setHealth(pl); /* Calculated Damage */
            /* For now make the enemy lose their health. */
            ((Enemy) enemy).getEnemyStat().setHealth(0);
            preloader.getScore().setValue(preloader.getScore().getValue() + 1);
            preloader.getDpC().getTrash().add(enemy);
            System.out.println("collided with enemy");
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
      else if (s instanceof Player)
        preloader.getDpC().getSprites().remove(s);
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
   * Gets the player, only this class has the player.
   * @return player
   * @type <!-- Ignore Wrong tag --> Important Getter.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Gets the Preloader, only this class has the Preloader.
   * @return the preloader
   * @type <!-- Ignore Wrong tag --> Important Getter.
   */
  public Preloader getPreloader() {
    return preloader;
  }

  /**
   * Used for other classes, get the application's width.
   * @return screen width
   * @type <!-- Ignore Wrong tag --> Important Getter.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Used for other classes, get the application's height.
   * @return screen height
   * @type <!-- Ignore Wrong tag --> Important Getter.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the world number.
   * Used for scene generation.
   *
   * @return world.
   */
  public int getWorld() {
    return world;
  }

  /**
   * Sets the world number.
   * Used when the player moves into specific places.
   *
   * @param world number, worldID.
   */
  public void setWorld(int world) {
    this.world = world;
  }

  /**
   * Drives the program.
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("org.example.Main.Window");
  }
}

