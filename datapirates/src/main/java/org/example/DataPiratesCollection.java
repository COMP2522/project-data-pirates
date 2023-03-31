package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPiratesCollection {

  private ArrayList<Sprite> enemies;
  private  ArrayList<Sprite> sprites;
  private  ArrayList<Sprite> bullets;

  private ArrayList<Sprite> projectiles;

  private  HashMap<Projectile, Enemy> remove;

  private ArrayList<Sprite> trash;

  private static DataPiratesCollection dpCollection;

  public static DataPiratesCollection getInstance() {
    if (dpCollection == null) {
      dpCollection = new DataPiratesCollection();
    }
//    if (dpCollection.getEnemies() == null && dpCollection.getSprites() == null && dpCollection.getBullets() == null && dpCollection.getRemove() == null) {
      dpCollection.setEnemies(new ArrayList<Sprite>());
      dpCollection.setSprites(new ArrayList<Sprite>());
      dpCollection.setBullets(new ArrayList<Sprite>());
      dpCollection.setRemove(new HashMap<Projectile, Enemy>());
      dpCollection.setTrash(new ArrayList<Sprite>());
//      sprites = new ArrayList<Sprite>();
//      bullets = new ArrayList<Sprite>();
//      remove = new HashMap<Projectile, Enemy>();
//    }
    return dpCollection;
  }
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