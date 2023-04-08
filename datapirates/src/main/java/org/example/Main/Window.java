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
  int width = 1080;

  /*
    Screen's Height.
  */
  int height = 720;

  /*
    Menu HUD.
  */
  private boolean menuHudStatus = true;

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
        PVector enemy = new PVector(random(0, this.width), random(25, this.height));
        PVector enemyDirection = SpriteManager.calculateDirection(enemy, player.getPosition());
        Sprite e = new Enemy(
                enemy,
                enemyDirection,
                random(MINSIZE, MAXSIZE),
                random(0, 2),
                EntityColor.getSpriteColors().get("Enemy"),
                this, "LVL_1\\frame "
        );
        preloader.getDpC().getSprites().add(e);
      }).start();
    }
  }

  /**
   * Sets extra important things.
   *
   */
  public void init() {

    player = Player.getInstance(
            new PVector((float) (this.width / 2), (float) (this.height / 2)),
            new PVector(0, 1),
            50,
            5,
            EntityColor.getSpriteColors().get("Player"),
            this);

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
      if (e.getKey() == 'r')
        player.getWeapon().reload();
      else if (e.getKey() == 'c' && world == 0)
        isHudActive = !isHudActive;
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
                dir, 10, 5, player.getWeapon().getBulletColor(), this, player.getWeapon());
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
    if (worldID == 1)
      preloader.getShop().returnToOrigin();
    else if (worldID == 2)
      preloader.getCchest().returnToOrigin();
    else if (worldID == 3) {
      isHudActive = false;
      menuHudStatus = true;
      preloader.getSaferoom().returnToOrigin();
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
      /* World Zeros */
      if (player.getPosition().x >= this.width && world == 0) {
        /*
          The Shop.
          TODO {Note}: Might not be implemented.
          See locations\shop
        */
//        world = 1;
        player.getPosition().set(width / 2, player.getPosition().y);
      } else if (player.getPosition().x < 5 && world == 0) {
        /*
          The Daily Chest Room.
          See locations\chestroom
        */
        world = 2;
        player.getPosition().set(this.width - 10, player.getPosition().y);
      } else if (player.getPosition().y < 5 && world == 0) {
        /*
          The Menu / Safe Room.
          See locations\startArea
        */
        world = 3;
        player.getPosition().set(player.getPosition().x, this.height - 10);
      } else if ((player.getPosition().x >= width || player.getPosition().x < 5 || player.getPosition().y < 5) && world == 3)
        player.getPosition().set(width / 2, height / 2);
      else if ((player.getPosition().x < 5 || player.getPosition().y < 5 || player.getPosition().y >= height) && world == 2)
        player.getPosition().set(width / 2, height / 2);
      else if (player.getPosition().y >= height && world == 0)
        player.getPosition().set(width / 2, height / 2);
//      else if (player.getPosition().y >= this.height && world == 0) {
        /*
          Kathyrinollettebellja, the Twelveth Voidylss. (Boss Room)
          TODO {Note}: Might not be implemented in this term. Teddy might do this on his own, because he wants to.
          See locations\happyWorld
        */
//        world = 4;
//        player.getPosition().set(player.getPosition().x, 10);
//      }

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
      player.update();
      player.draw();

      for (Sprite s : preloader.getDpC().getBullets()) {
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
        if (!(s instanceof Player)) {
          s.update();
          s.draw();
          /* TODO: Show the enemy health. */
          if (s instanceof Enemy) {
            PVector dir = SpriteManager.calculateDirection(s.getPosition(), player.getPosition());
            s.setDirection(dir);
            textSize(10);
            fill(EntityColor.getSpriteColors().get("Text").getRed(),
                    EntityColor.getSpriteColors().get("Text").getGreen(),
                    EntityColor.getSpriteColors().get("Text").getBlue());
            text("health: " + ((Enemy) s).getEnemyStat().getHealth(),
                    s.getPosition().x - 10,
                    s.getPosition().y + 20);
          }
        }
      }
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

