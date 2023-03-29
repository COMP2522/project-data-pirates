package org.example;

import org.example.gui.Menu;
import org.example.locations.chestroom.ChestRoom;
import org.example.locations.shop.Shop;
import org.example.locations.startArea.SafeRoom;
import org.example.music.MusicManager;
import org.example.spriteClasses.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.sql.Array;
import java.util.Random;
import java.lang.Object.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.*;
//import gifAnimation.*;

/**
 * The program where the game
 * will start operating at.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Window extends PApplet {


  private static Shop shop;

  private static ChestRoom cchest;

  private static SafeRoom saferoom;

  /* Min size of entities. */
  private static final int MINSIZE = 50;

  /* Max size of entities. */
  private static final int MAXSIZE = 150;

  /** Only one player allowed. Single player. */
  protected static Player player;

  private int numEnemies = 1;

  // start from menu
  private int world = 3;

  /**
   * World 0 = Main
   * World 1 = Shop
   * World 2 = Something
   */

  private Timer clock;

  private Menu menu;
  private Boolean isHUDActive = false;
  int width = 1080;
  int height = 720;
  DataPiratesCollection dpC;
  Score score;

  Items item;

  MusicManager music;
  private final int appendNum = 1;

  BufferedImage background1;

  private int before;
  private int funnyNumber = 1;
  private boolean MenuHUDStatus = false;

  private GifManager gifBackground;
  public void settings() {
    size(width, height);
  }

  /**
   * Sets the game mechanics.
   */
  public void setup() {
    //    settings();
//    numEnemies = numEnemiesUnChanged;

    dpC = DataPiratesCollection.getInstance();
    score = Score.getInstance();
    EntityColor.setColors();
    item = Items.getInstance();
    gifBackground = new GifManager("world0\\frame ", 61, this);
//    System.out.println(EntityColor.getSpriteColors().get("The Almighty"));
    clock = new Timer();
    shop = new Shop(this);
    cchest = new ChestRoom(this);
    saferoom = new SafeRoom(this);
    music = MusicManager.getInstance();
    this.init();
    clock.start();
    music.play(0);
//    background1 = loadImage("datapirates\\src\\main\\java\\org\\example\\pictures\\map3.gif");
//    background1.set
//    background1.resize(this.width, this.height);
    menu = new Menu(this);
    frameRate(100);

//    myAnimation = new Gif(this, "datapirates\\src\\main\\java\\org\\example\\pictures\\map3.gif");
//    myAnimation.loop();
//    PImage[] allFrames = Gif.getPImages(this, "lavalamp.gif");
//    myAnimation.play();
//    myAnimation.resize(this.width, this.height);

//    background1.
    // Convert MP3 TO WAV.
//    try {
//      File file = new File("datapirates\\src\\main\\Devourer.wav");
//      AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//      Clip clip = AudioSystem.getClip();
//      clip.open(audioStream);
//      clip.start();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

  }

  /**
   * Creates enemies based on how many numEnemies are there.
   */
  public void setUpEnemies() {
    new Thread(() -> {
      // levels of enemies
//      for (int i = 0; i < numEnemies; i++) {
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
        dpC.getEnemies().add(e);
//      }
//        dpC.getSprites().addAll(dpC.getEnemies());
    }).start();
  }

  public void showEnemies() {
//    new Thread(() -> {
//      if (funnyNumber != numEnemies)
//        funnyNumber+=1;
    Random a = new Random();

    // make a random for players that have higher level
    // randomize the level of enemies spawned
      for (int i = 0; i < (a.nextInt(numEnemies) + 1); i++) {
//        System.out.println("hello");
//        dpC.getSprites().add(dpC.getEnemies().get(0));
//        AtomicReference<Sprite> e;
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
        dpC.getSprites().add(e);

        }).start();
      }
//    }).start();
  }
  /**
   * Is called on setup. Sets the main
   * components. Such as players, enemies.
   *
   */
  public void init() {

    Player p = Player.getInstance(
            new PVector(this.width / 2, this.height / 2),
            new PVector(0, 1),
            50,
            5,
            EntityColor.getSpriteColors().get("Player"),
            this);
    //    dpC.getEnemies().add(e);
    player = p;
//    Weapon basic = new Weapon("Basic", EntityColor.getSpriteColors().get("Bullet"), 100, 10);

    //    player.setHealth(100);
//    dpC.getSprites().add(p);


    //    PVector plyer = new PVector(player.getPosition().x, player.getPosition().y);
    setUpEnemies();

  }

  @Override
  public synchronized void keyPressed(KeyEvent e) {

    if (e.getKey() == 'r')
      player.getWeapon().reload();
    else if (e.getKey() == 'c' && world == 0) {
        isHUDActive = !isHUDActive;
    } else if (e.getKey() == 'c' && world == 3) {
       MenuHUDStatus = !MenuHUDStatus;
    }
    if (e.getKey() == 'e' && world == 2) {
      cchest.getChest().setOpened(!cchest.getChest().isOpened());
    }

    switch (e.getKey()) {
      case 'w':
        player.getDiag().setUpPressed(true);
        break;
      case 's':
        player.getDiag().setDownPressed(true);
        break;
      case 'a':
        player.getDiag().setLeftPressed(true);
        break;
      case 'd':
        player.getDiag().setRightPressed(true);
        break;
      default:
        return;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
//    super.keyReleased(event);
    switch (e.getKey()) {
      case 'w':
        player.getDiag().setUpPressed(false);
        break;
      case 's':
        player.getDiag().setDownPressed(false);
        break;
      case 'a':
        player.getDiag().setLeftPressed(false);
        break;
      case 'd':
        player.getDiag().setRightPressed(false);
        break;
      default:
        return;
    }
  }

  @Override
  public void mousePressed() {
    if (player.getWeapon().hasAmmo()) {
      player.getWeapon().shoot();
      // Direction Calculation
      // src: https://processing.org/tutorials/pvector/#vectors-interactivity
      PVector mouse = new PVector(mouseX, mouseY);
      PVector dir = SpriteManager.calculateDirection(player.getPosition(), mouse);

      Projectile projectile = new Projectile(player.getPosition().copy(),
              dir, 10, 5, player.getWeapon().getBulletColor(), this, player.getWeapon());
//      dpC.getSprites().add(projectile);
      dpC.getBullets().add(projectile);
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
   * Make this function only available to certain
   * weapons.
   * REASON: IT IS TOO OVERPOWERED
   */
//  public void mouseDragged() {
//    mousePressed();
//  }

  void printDisplayText() {
    // example texts
    int textsize1 = 40;
    textSize(textsize1 - 20);
    fill(EntityColor.getSpriteColors().get("Text").getRed(),
            EntityColor.getSpriteColors().get("Text").getGreen(),
            EntityColor.getSpriteColors().get("Text").getBlue());
    text("" + score.getValue(), 0, textsize1);

    textSize(textsize1 - 10);
    fill(EntityColor.getSpriteColors().get("Text").getRed(),
            EntityColor.getSpriteColors().get("Text").getGreen(),
            EntityColor.getSpriteColors().get("Text").getBlue());
    text("HP " + player.getPlayerStat().getHealth() + "/" + player.getPlayerStat().getMaxHealth(), 0, textsize1 * 3);

    textSize(textsize1 - 10);
    fill(EntityColor.getSpriteColors().get("Text").getRed(),
            EntityColor.getSpriteColors().get("Text").getGreen(),
            EntityColor.getSpriteColors().get("Text").getBlue());
    text("AMMO " + player.getWeapon().getCurrentAmmo()
            + "/" + player.getWeapon().getAmmoCapacity()
            + "", 0, textsize1 * 2);

//    int clockSize = 40;
    textSize(textsize1);
    text("Next: " + (int) clock.getEstimated() + "s", this.width / 2, textsize1);
    fill(EntityColor.getSpriteColors().get("Text").getRed(),
            EntityColor.getSpriteColors().get("Text").getGreen(),
            EntityColor.getSpriteColors().get("Text").getBlue());

  }

  public void renderNewLocation(int worldID) {
//   if (worldID != 0) {
    if (world != 0) {
      clock.stop();
      clock.reset();
      for (Sprite s : dpC.getSprites())
        if (s instanceof Enemy)
          dpC.getTrash().add(s);

     for (Sprite s : dpC.getTrash())
       removeEnemies(s);
    }
//   }
    if (worldID == 1) {
      shop.returnToOrigin();
    } else if (worldID == 2) {
      cchest.returnToOrigin();
    } else if (worldID == 3) {
      saferoom.returnToOrigin();
    }
  }


  public Player getPlayer() {
    return player;
  }

  public Timer getClock() {
    return clock;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * Updates and gets called everytime.
   */
  public void draw() {


    // used to move to shop
    if (player.getPosition().x >= this.width && world == 0) {
      world = 1; // shop
      player.getPosition().set(10, player.getPosition().y);
    } else if (player.getPosition().x < 5 && world == 0) {
      world = 2; // chest
      player.getPosition().set(this.width - 10, player.getPosition().y);
    } else if (player.getPosition().y < 5 && world == 0) {
      world = 3; // menu / safe room
      player.getPosition().set(player.getPosition().x, this.height - 10);
    }

    player.move();
      renderNewLocation(world);


    if (MenuHUDStatus) {
      menu.drawAtSafe();
    }
    if (world == 0){
      MenuHUDStatus = false;
//      background(0);
      gifBackground.displayBackground();
//      System.out.println(!dpC.getSprites().stream().anyMatch(n -> n instanceof Enemy));
      if (clock.isStopped() && (!dpC.getSprites().stream().anyMatch(n -> n instanceof Enemy))) {
        // cleans the trash collection every 10 seconds IDK
        // boost performance by resetting the garbage
        // collection
        dpC.setTrash(new ArrayList<Sprite>());
        dpC.setRemove(new HashMap<Projectile, Enemy>());
        numEnemies += random(0, 8);
        showEnemies();
//        setUpEnemies();
        clock.start();
      }
        clock.stop();

    }

    // Images

//    PImage img = loadImage("datapirates\\src\\main\\java\\org\\example\\spriteClasses\\sprites\\sprite.jpg");
//    img.resize((int) player.getSize(), (int) player.getSize());
    if (isHUDActive) {
      printDisplayText();
    }
//    printDisplayText();
    player.update();
    player.draw();

    for (Sprite s : dpC.getBullets()) {
      s.draw();
      s.update();
    }

//            dpC.getSprites().iterator();
    ArrayList<Sprite> conCurrentModHelper = new ArrayList<>();
    conCurrentModHelper.addAll(dpC.getSprites());
    Iterator<Sprite> it = conCurrentModHelper.iterator();
    while (it.hasNext()) {
      Sprite s = it.next();
//    for (Sprite s : dpC.getSprites()) {
      if (!(s instanceof Player)) {
        s.update();
        s.draw();
        if (s instanceof Enemy) {
          PVector dir = SpriteManager.calculateDirection(s.getPosition(), player.getPosition());
          s.setDirection(dir);
//          if (!((Enemy) s).isVisited()) {
            textSize(10);
            fill(EntityColor.getSpriteColors().get("Text").getRed(),
                  EntityColor.getSpriteColors().get("Text").getGreen(),
                  EntityColor.getSpriteColors().get("Text").getBlue());
            text("health: " + ((Enemy) s).getEnemyStat().getHealth(),
                  s.getPosition().x - 10,
                  s.getPosition().y + 20);
//          ((Enemy) s).visit();
//          }
//        }

    }
//        imageMode(CENTER);
//        pushMatrix();
//        System.out.println(Math.toDegrees(player.getDirection().heading()));
//        rotate(radians((float) Math.toDegrees(player.getDirection().heading())));

//        popMatrix();
      }
    }


     collideManager();
  }

  public void collideManager() {
    // bullet vs enemy
    for (Sprite bullet : dpC.getBullets()) {
      for (Sprite enemy : dpC.getSprites()) {
        if (enemy instanceof Enemy && bullet.collided(enemy)) {
          //          if ((Enemy) enemy.)
          ((Enemy) enemy).getEnemyStat().setHealth(((Enemy) enemy).getEnemyStat().getHealth() - player.getWeapon().getDmg());
          if (((Enemy) enemy).getEnemyStat().getHealth() <= 0) {
            System.out.println("Killed an enemy!");
            Player.setBalance(Player.getBalance() + 2);
            dpC.getRemove().put((Projectile) bullet, (Enemy) enemy);
            score.setValue(score.getValue() + 2);
          } else {
            dpC.getTrash().add(bullet);
          }
        } else if (bullet.getPosition().x >= this.width || bullet.getPosition().y >= this.height) {
          dpC.getTrash().add(bullet);
        }
      }
    }

    // player vs enemy
    for (Sprite enemy : dpC.getSprites()) {
      if (enemy instanceof Enemy) {
        if (!(player.getPlayerStat().getHealth() <= 0)) {
          if (player.collided(enemy)) {
            player.getPlayerStat().setHealth(((Enemy) enemy).getEnemyStat().getCalculatedDamage(player));
            score.setValue(score.getValue() + 1);
            dpC.getTrash().add(enemy);
            System.out.println("collided with enemy");
          }
        } else {
          funnyNumber = 1;
          setup();
        }

      }
    }

    // remove the collision between bullets and enemies
    for (Map.Entry<Projectile, Enemy> mapElement: dpC.getRemove().entrySet()) {
      removeBullet(mapElement.getKey());
      removeEnemies(mapElement.getValue());
    }
    // remove bullets that are out of bounds
    // and enemies that have touched the player.
    for (Sprite s : dpC.getTrash()) {
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
//    dpC.getEnemies().remove(s);
    dpC.getSprites().remove(s);
  }

  /**
   * Removes a bullet from the bullet
   * and sprite list.
   *
   * @param b Bullet as a Sprite object.
   */
  public void removeBullet(Sprite b) {
    dpC.getBullets().remove(b);
    dpC.getSprites().remove(b);
  }

  public int getWorld() {
    return world;
  }

  public void setWorld(int world) {
    this.world = world;
  }

  /**
   * Drives the program.
   * @param args unused
   */
  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }


}

