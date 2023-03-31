package org.example.spriteClasses;

import java.awt.Color;

import org.example.Main.Items;
import org.example.Main.Preloader;
import org.example.Main.Weapon;
import org.example.Main.Window;
import processing.core.PVector;

/**
 * Player class that is controlled by
 * the user. Can move, shoot, and obtain items.
 *
 * @author Teddy Dumam-Ag
 *
 * @version JDK 18
 */
public class Player extends Sprite {

  private Weapon weapon;

  private static Player player;

  private static int balance;

  private int health;

  private int maxHealth;

  private int defense;

  private DiagonalMove diag;

  private SpriteStat playerStat;


  private Player(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }

  /**
   * Gets the only Player object in the entire program.
   *
   * @param position Starting position of the sprite.
   * @param direction Starting direction of the sprite.
   * @param size Size of the sprite.
   * @param speed Speed of the sprite.
   * @param color Color that identifies the sprite.
   * @param window The scene / Window where the sprite is shown.
   *
   * @return The only player object.
   */
  public static Player getInstance(PVector position, PVector direction,
                                   float size, float speed, Color color, Window window) {
    if (player == null) {
      player = new Player(position, direction, size, speed, color, window);
    } else {
      player.setPosition(position);
      player.setDirection(direction);
      player.setSize(size);
      player.setSpeed(speed);
      player.setColor(color);
      player.setWindow(window);
    }


    player.setWeapon(Items.getWeapon(Items.getWeapons().get(Preloader.RNG.nextInt(Items.getWeapons().size())).getModel()));
    System.out.println(player.getWeapon().getModel());

    if (!window.getPreloader().isFinished()) {
      player.diag = new DiagonalMove();
      player.setMm(new GifManager("player\\frame ", 6, player.getPosition(), player.getWindow(), player));
    }
    player.setPlayerStat(new SpriteStat(player, 100, 50, player.getWeapon().getDmg()));
    return player;
  }


  private void setPlayerStat(SpriteStat playerStat) {
    this.playerStat = playerStat;
  }

  public SpriteStat getPlayerStat() {
    return playerStat;
  }

//  public int getHealth() {
//    return health;
//  }
//
//  public int getDefense() {
//    return defense;
//  }
//
//  public int getMaxHealth() {
//    return maxHealth;
//  }

//  public void setMaxHealth(int maxHealth) {
//    this.maxHealth = maxHealth;
//  }
//
//  public void setHealth(int health) {
//    this.health = health;
//  }

  public void setDefense(int def) {
    defense = def;
  }

  /**
   * With disabling its super method, the player sprite
   * will not constantly move all the time.
   */
  @Override
  public void update() {
    //    super.update();
  }

  /**
   * Draws the shape of the player.
   */
  public void draw() {
      SpriteManager.assignSprite(this, getMm());
  }

  public void move(float x, float y) {
    player.setDirection(player.getPosition().mult(-1).add(new PVector(x, y)).normalize());
  }

  /**
   * Function shows how the player will move.
   *
//   * @param event KeyEvent -> keyboard button.
   */
  public  void move() {
    player.setDirection(diag.getDirection());
    player.setPosition(player.getPosition().add(
            player.getDirection().copy().mult(player.getSpeed())));
  }


  public static int getBalance() {
    return balance;
  }

  public static void setBalance(int balance) {
    Player.balance = balance;
  }


  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public DiagonalMove getDiag() {
    return diag;
  }
}
