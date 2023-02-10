package org.example;

import java.util.ArrayList;

public class DataPiratesCollection {

  protected static ArrayList<Sprite> enemies;
  protected static ArrayList<Sprite> sprites;
  protected static ArrayList<Sprite> bullets;

  public static ArrayList<Sprite> getBullets() {
    return bullets;
  }

  public static ArrayList<Sprite> getEnemies() {
    return enemies;
  }

  public static ArrayList<Sprite> getSprites() {
    return sprites;
  }


}
