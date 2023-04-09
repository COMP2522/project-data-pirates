package org.example.Main;

import java.awt.Color;

import org.example.spriteClasses.Player;
import org.example.spriteClasses.Projectile;
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



  /* Only one player allowed. Single player. */
  protected static Player player;

  /* World ID value. */
  private int world = 3;

  /*
    Screen's Width.
  */
  public static final int WIDTH = 1080;

  /*
    Screen's Height.
  */
  public static final int HEIGHT = 720;

  /*
    Menu HUD.
  */

  /*
    Preloader Class.
  */
  private Preloader preloader;

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
    surface.setTitle("Data Pirates");
    wH = new WindowHelper(this);
    preloader = wH.getPreloader();
//            new Preloader(this); /* TODO: Removal */

  }

  /**
   * Sets extra important things.
   *
   */
  public void init() {
    preloader.getScore().setValue(0);
    player = Player.getInstance(
            new PVector((float) (WIDTH / 2), (float) (this.HEIGHT / 2)),
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
      else if (e.getKey() == 'c' && wH.getWorld() == 0)
        wH.getPreloader().getDp_Hud().setHudStatus(!wH.getPreloader().getDp_Hud().isHudActive());
      else if (e.getKey() == 'q') {
        /* Show boundaries. */
        wH.getPreloader().getDp_Hud().setEntityBorderStatus(!wH.getPreloader().getDp_Hud().isEntityBorderActive());
      } else if (e.getKey() == 'e' && preloader.getMenu().isINPanelActive())
        preloader.getMenu().setInstructionPanel(false);

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
      if (wH.getWorld() == 3) {
        preloader.getMenu().update();
      }
      shootFunction();
    }
  }

  public void shootFunction() {
    if (player.getWeapon().hasAmmo()) {
      player.getWeapon().shoot();
//        preloader.getMusic().p
      preloader.getMusic().play(4);

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
            0, HEIGHT - playerTextSize - playerTextSize);

    /*
      Ammo Capacity.
    */
    fill(textColor.getRGB());
    text("AMMO " + player.getWeapon().getCurrentAmmo()
            + "/" + player.getWeapon().getAmmoCapacity()
            + "", 0, HEIGHT - playerTextSize);

    /*
      Wave Time.
    */
    fill(textColor.getRGB());
    text("Next: " + (int) preloader.getClock().getEstimated()
            + "s", 0, playerTextSize + playerTextSize);

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
    }
    else {
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
//      !preloader.getMusic().isPlaying()
      if (preloader.getMusic().getObjectType() != wH.getWorld()) {
        if (preloader.getMusic().isPlaying())
          preloader.getMusic().stop();
        preloader.getMusic().play(wH.getWorld());
      }

      /* Alternate Name: Start next wave. */
      wH.beginTheBattle();

      /* The compact function of draw and update. */
      wH.updateFrame();

      /* Heads-Up Display. */
      preloader.getDp_Hud().updateFrame();

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
   * Gets the world number.
   * Used for scene generation.
   *
   * @return world.
   */
  public int getWorld() {
    return wH.getWorld();
  }

  /**
   * Sets the world number.
   * Used when the player moves into specific places.
   *
   * @param world number, worldID.
   */
  public void setWorld(int world) {
    wH.setWorld(world);
  }

  /**
   * Drives the program.
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("org.example.Main.Window");
  }
}

