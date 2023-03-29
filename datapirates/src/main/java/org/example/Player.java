package org.example;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import processing.event.KeyEvent;

import java.awt.*;

public class Player extends Sprite {

  private Weapon weapon;

  private static Player player;

  private static int coin;

  private int health;

  private int defense;

  private PImage image;

  private Player(PVector pos, PVector direction, float size, float speed, Color clr, Window scene, PImage image) {
    super(pos, direction, size, speed, clr, scene);
    this.image = image;
  }

  public static Player getInstance(PVector position, PVector direction, float size, float speed, Color color, Window window, PImage playerImage) {
    if (player == null) {
      player = new Player(position, direction, size, speed, color, window, playerImage);

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
//    super.update();
  }

  public void draw() {
    player.getWindow().pushMatrix(); // Save the current transformation matrix
    player.getWindow().translate(player.getPosition().x, player.getPosition().y); // Move the origin to the player's position

    // Calculate the angle between the player's direction and the positive X-axis
    float angle = PApplet.atan2(player.getDirection().y, player.getDirection().x);
    player.getWindow().rotate(angle); // Rotate the image by the calculated angle

    player.getWindow().imageMode(PApplet.CENTER); // Set the image mode so that the image is drawn centered at the origin
    player.getWindow().image(image, 0, 0); // Draw the image at the origin
    player.getWindow().popMatrix(); // Restore the previous transformation matrix
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

  public void fireProjectile() {
    if (this.weapon != null) {
      PVector projectilePos = this.position.copy();
      PVector projectileDirection = this.direction.copy();
      float projectileSize = 10; // Set the size of the projectile
      float projectileSpeed = 5; // Set the speed of the projectile
      Color projectileColor = new Color(255, 255, 0); // Set the color of the projectile

      Projectile projectile = new Projectile(projectilePos, projectileDirection, projectileSize, projectileSpeed, projectileColor, this.window);
      this.weapon.addProjectile(projectile);
    }
  }

}
