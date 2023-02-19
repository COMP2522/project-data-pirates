package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPiratesCollection {

  private ArrayList<Sprite> enemies;
  private  ArrayList<Sprite> sprites;
  private  ArrayList<Sprite> bullets;

  private  HashMap<Projectile, Enemy> remove;

  private ArrayList<Projectile> outOfBondsBullets;

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
      dpCollection.setOutOfBondsBullets(new ArrayList<Projectile>());
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

  public ArrayList<Projectile> getOutOfBondsBullets() {
    return outOfBondsBullets;
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

  public void setOutOfBondsBullets(ArrayList<Projectile> outOfBondsBullets) {
    this.outOfBondsBullets = outOfBondsBullets;
  }
}