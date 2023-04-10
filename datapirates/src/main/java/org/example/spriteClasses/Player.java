package org.example.spriteClasses;

import java.awt.Color;
import org.example.Main.Preloader;
import org.example.Main.Weapon;
import org.example.Main.Weapons;
import org.example.Main.Window;
import processing.core.PVector;

/**
 * Player class that is controlled by
 * the user. Can move, shoot, and obtain items.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Player extends Sprite {

  private Weapon weapon;
  private static Player player;
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
   *
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

    player.setUp();

    return player;
  }

  @Override
  public void setUp() {
    player.setWeapon(Weapons.getWeapon(Weapons.getWeapons().get(
            Preloader.RNG.nextInt(Weapons.getWeapons().size())).getModel()));

    if (!window.getPreloader().isFinished()) {
      player.diag = new DiagonalMove();
      player.setGm(new Gif("player\\frame ", 6, player.getWindow(), player));
    }
    player.setPlayerStat(new SpriteStat(player, 1000, player.getWeapon().getDmg()));
  }

  /**
   * Must disable the super method so that the player will not
   * constantly be walking.
   */
  @Override
  public void update() { }

  /**
   * Draws the shape of the player.
   */
  public void draw() {
    SpriteManager.assignGif(this, getGm());
  }

  /**
   * Function shows how the player will move.
   */
  public  void move() {
    player.setDirection(diag.translateDirection());
    player.setPosition(player.getPosition().add(
            player.getDirection().copy().mult(player.getSpeed())));
  }

  /* TODO: Setters and Getters beyond this point! */

  private void setPlayerStat(SpriteStat playerStat) {
    this.playerStat = playerStat;
  }

  public SpriteStat getPlayerStat() {
    return playerStat;
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
