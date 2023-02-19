package org.example;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.util.Map;

public class Window extends PApplet {

  protected Player player;

  private Timer timer;

  private int numEnemies = 1;

  /* Min size of entities. */
  private int minSize = 9;

  /* Max size of entities. */
  private int maxSize = 20;
  int width = 1080;
  int height = 520;
  DataPiratesCollection dpC;
  Score score;


  public void settings() {
    size(width, height);
  }

  public void setup() {
//    settings();
    dpC = DataPiratesCollection.getInstance();
    score = Score.getInstance();
    EntityColor.setColors();
    this.init();
  }

  public void init() {
    Player p = Player.getInstance(
            new PVector(this.width / 2,this.height / 2),
            new PVector(0,1),
            40,
            10,
            EntityColor.getSpriteColors().get("Player"),
            this);
//    dpC.getEnemies().add(e);
    player = p;
    Weapon basic = new Weapon("Basic", EntityColor.getSpriteColors().get("Bullet"), 100);
    player.setWeapon(basic);
    dpC.getSprites().add(p);

//    PVector plyer = new PVector(player.getPosition().x, player.getPosition().y);


    for (int i = 0; i < numEnemies; i++) {
      PVector enemy = new PVector(random(0, this.width), random(0, this.height));
      PVector enemyDirection = SpriteManager.calculateDirection(enemy, player.getPosition());
      Sprite e = new Enemy(
              enemy,
              enemyDirection,
              random(minSize, maxSize),
              random(0,2),
              EntityColor.getSpriteColors().get("Enemy"),
              this
      );
      dpC.getEnemies().add(e);
    }

    dpC.getSprites().addAll(dpC.getEnemies());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKey() == 'r')
      player.getWeapon().reload();
    player.move(e);
  }

  @Override
  public void mousePressed() {
    if (player.getWeapon().hasAmmo()) {
      player.getWeapon().shoot();
      // Direction Calculation
      // src: https://processing.org/tutorials/pvector/#vectors-interactivity
      PVector mouse = new PVector(mouseX, mouseY);
      PVector dir = SpriteManager.calculateDirection(player.getPosition(), mouse);

      Projectile projectile = new Projectile(player.getPosition().copy(), dir, 10, 5, EntityColor.getSpriteColors().get("Bullet"), this);
      dpC.getSprites().add(projectile);
      dpC.getBullets().add(projectile);
    }
    else {
      textSize(60);
      fill(EntityColor.getSpriteColors().get("Reload").getRed(), EntityColor.getSpriteColors().get("Reload").getGreen(), EntityColor.getSpriteColors().get("Reload").getBlue());
      text("Reload!", player.getPosition().x + player.getSize(), player.getPosition().y + player.getSize());
    }
  }


  /**
   * Make this function only available to certain
   * weapons.
   */
  public void mouseDragged() {
    mousePressed();
  }

  public void draw() {
    background(0);

    // example texts

    textSize(10);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    text("Score: " + score.getValue(), 40, 120);

    textSize(30);
    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    text("Ammo: " + player.getWeapon().getCurrentAmmo() + " / " + player.getWeapon().getAmmoCapacity() + "", 40, 100);

//    textSize(30);
//    text("Ammo: ", 40, 80);
//    fill(EntityColor.getSpriteColors().get("Text").getRed(), EntityColor.getSpriteColors().get("Text").getGreen(), EntityColor.getSpriteColors().get("Text").getBlue());
    dpC.getSprites().get(0).update();
    dpC.getSprites().get(0).draw();
//    while (true) {
    for (Sprite s : dpC.getSprites()) {
      if (!(s instanceof Player)) {
        if (s instanceof Enemy) {
          PVector dir = SpriteManager.calculateDirection(s.getPosition(), player.getPosition());
          s.setDirection(dir);
        }
        s.update();
        s.draw();
      }
    }

    // bullet vs enemy
    for (Sprite bullet : dpC.getBullets()) {
      for (Sprite enemy : dpC.getEnemies()) {
        if (bullet.collided(enemy)) {
          System.out.println("Killed an enemy!");
          dpC.getRemove().put((Projectile) bullet, (Enemy) enemy);
          score.setValue(score.getValue() + 1);
        } else if (bullet.getPosition().x >= this.width || bullet.getPosition().y >= this.height) {
          dpC.getOutOfBondsBullets().add((Projectile) bullet);
        }
      }
    }

    // player vs enemy
    for (Sprite enemy : dpC.getEnemies()) {
      if (player.collided(enemy)) {
        setup();
      }
    }

    // remove the collision between bullets and enemies
    for (Map.Entry<Projectile, Enemy> mapElement: dpC.getRemove().entrySet()) {
        removeBullet(mapElement.getKey());
        removeEnemies(mapElement.getValue());
    }

    // remove bullets that are out of bounds.
    for (Projectile b : dpC.getOutOfBondsBullets()) {
      removeBullet(b);
    }
  }

  /**
   * Removes an enemy in the enemy
   * and sprite list.
   *
   * @param s Enemy as a Sprite object.
   */
  public void removeEnemies(Sprite s) {
    dpC.getEnemies().remove(s);
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

  /**
   * Drives the program.
   * @param args
   */
  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}
