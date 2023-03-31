package org.example.Main;

import org.example.spriteClasses.Enemy;
import org.example.spriteClasses.Projectile;
import org.example.spriteClasses.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Data Pirates' sprite collection.
 *
 * @author Data Pirates Team
 *
 * @version JDK 18
 */
public class DataPiratesCollection {

  /* Enemies list. */
  private ArrayList<Sprite> enemies;

  /* Current sprites in the frame. */
  private  ArrayList<Sprite> sprites;

  /* All the projectiles and bullets in the game. */
  private  ArrayList<Sprite> bullets;

  /* Trash collection 1. */
  private  HashMap<Projectile, Enemy> remove;

  /* Trash collection 2. */
  private ArrayList<Sprite> trash;

  /* Singleton. */
  private static DataPiratesCollection dpCollection;

  /**
   * Gets the instance for the Singleton. Only one
   * collection may be allowed.
   *
   * @return the dpCollection
   */
  public static DataPiratesCollection getInstance() {
    if (dpCollection == null) {
      dpCollection = new DataPiratesCollection();
    }
    dpCollection.setEnemies(new ArrayList<Sprite>());
    dpCollection.setSprites(new ArrayList<Sprite>());
    dpCollection.setBullets(new ArrayList<Sprite>());
    dpCollection.setRemove(new HashMap<Projectile, Enemy>());
    dpCollection.setTrash(new ArrayList<Sprite>());
    return dpCollection;
  }

  /* Getters and Setters. */
  public ArrayList<Sprite> getBullets() {
    return bullets;
  }

  public ArrayList<Sprite> getEnemies() {
    return enemies;
  }

  public ArrayList<Sprite> getSprites() {
    return sprites;
  }

  public HashMap<Projectile, Enemy> getRemove() {
    return remove;
  }

  public ArrayList<Sprite> getTrash() {
    return trash;
  }

  public void setSprites(ArrayList<Sprite> sprites) {
    this.sprites = sprites;
  }

  public void setBullets(ArrayList<Sprite> bullets) {
    this.bullets = bullets;
  }

  public void setEnemies(ArrayList<Sprite> enemies) {
    this.enemies = enemies;
  }

  public void setRemove(HashMap<Projectile, Enemy> remove) {
    this.remove = remove;
  }

  public void setTrash(ArrayList<Sprite> trash) {
    this.trash = trash;
  }
}