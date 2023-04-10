package org.example.Main;

import java.util.ArrayList;
import java.util.HashMap;
import org.example.spriteClasses.Enemy;
import org.example.spriteClasses.Projectile;
import org.example.spriteClasses.Sprite;

/**
 * Data Pirates' sprite collection.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class DataPiratesCollection {

  /* Singleton. */
  private static DataPiratesCollection dpCollection;

  /* Current sprites in the frame. */
  private ArrayList<Sprite> sprites;

  /* All the projectiles and bullets in the game. */
  private ArrayList<Sprite> bullets;

  /* Trash collection 1. */
  private HashMap<Projectile, Enemy> remove;

  /* Trash collection 2. */
  private ArrayList<Sprite> trash;


  /**
   * Gets the instance for the Singleton. Only one
   * collection may be allowed.
   *
   * @return the dpCollection.
   */
  public static DataPiratesCollection getInstance() {
    if (dpCollection == null)
      dpCollection = new DataPiratesCollection();

    dpCollection.setSprites(new ArrayList<>());
    dpCollection.setBullets(new ArrayList<>());
    dpCollection.setRemove(new HashMap<>());
    dpCollection.setTrash(new ArrayList<>());

    return dpCollection;
  }

  /* TODO: Getters and Setters beyond this point. */
  public ArrayList<Sprite> getBullets() {
    return bullets;
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

  public void setRemove(HashMap<Projectile, Enemy> remove) {
    this.remove = remove;
  }

  public void setTrash(ArrayList<Sprite> trash) {
    this.trash = trash;
  }
}