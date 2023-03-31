package org.example;

import processing.core.PVector;

import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;

public class Player extends Sprite {

  private Weapon weapon;

  private static Player player;

  private static int coin;

  private int health;

  private int defense;

  private Player(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }

  public static Player getInstance(PVector position, PVector direction, float size, float speed, Color color, Window window) {
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

    player.setHealth(100);
    player.setDefense(50);

    return player;
  }


  public int getHealth() {
    return health;
  }

  public int getDefense() {
    return defense;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setDefense(int def) {
    defense = def;
  }

  @Override
  public void update() {

  }

  public void updateAmmo() {
    if (player.getWindow().frameCount % 60 == 0) { // every second
      int previousHealth = health;
      int healthChange = health - previousHealth;
      int previousDefense = defense;
      int defenseChange = defense - previousDefense;
      weapon.increaseAmmo(healthChange / 2);
      weapon.increaseAmmo(defenseChange / 2);
    }
  }

  public void draw() {
    player.getWindow().pushStyle();
    player.getWindow().fill(player.getColor().getRed(), player.getColor().getGreen(), player.getColor().getBlue());
    float startx = player.getPosition().x - (player.getSize() / 2 * Math.abs(player.getDirection().y));
    float starty = player.getPosition().y + (player.getDirection().x * -1 * player.getSize() / 2);

    float midx = player.getPosition().x + (player.getDirection().x * player.getSize() / 1);
    float midy = player.getPosition().y + (player.getDirection().y * player.getSize() / 1);

    float finalx = player.getPosition().x + (player.getSize() / 2 * Math.abs(player.getDirection().y));
    float finaly = player.getPosition().y - (player.getDirection().x * -1 * player.getSize() / 2);

    player.getWindow().triangle(
            startx,starty,
            midx,midy,finalx,finaly);
    player.getWindow().popStyle();
  }

  public void move(float x, float y) {
    player.setDirection(player.getPosition().mult(-1).add(new PVector(x, y)).normalize());
  }

  public void move(KeyEvent event) {
    char key = event.getKey();
    switch (key) {
      case 'w':

        player.setDirection(new PVector(0, -1));
        break;
      case 's':
        player.setDirection(new PVector(0, 1));
        break;
      case 'a':
        player.setDirection(new PVector(-1, 0));
        break;
      case 'd':
        player.setDirection(new PVector(1, 0));
        break;
      default:
        return;
//        System.out.println("Test");
//        break;
    }
    player.setPosition(player.getPosition().add(player.getDirection().copy().mult(player.getSpeed())));

//            .position = this.getPosition().add(this.direction.copy().mult(speed));
  }


  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public Weapon getWeapon() {
    return weapon;
  }

}
