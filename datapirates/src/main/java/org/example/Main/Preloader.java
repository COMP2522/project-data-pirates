package org.example.Main;

import org.example.database.DatabaseManagement;
import org.example.gui.Menu;
import org.example.locations.chestroom.ChestRoom;
import org.example.locations.shop.Shop;
import org.example.locations.startArea.SafeRoom;
import org.example.music.MusicManager;
import org.example.spriteClasses.GifManager;

import java.util.Random;

/**
 * Activates before the game starts.
 * This is to prevent the performance delays when starting the game.
 *
 * @author Data Pirates Team
 */
public class Preloader {

  /*
    Just a bunch of variables here. The things
      that make the program slow in the beginning.
        Will be created in a thread.
  */
  public static final Random RNG = new Random();
  private boolean isFinished = false;
  private GifManager gifBackground;
  private Window scene;
  public DataPiratesCollection dpC;
  private Score score;
  private Items item;
  private Timer clock;
  private Shop shop;
  private ChestRoom cchest;
  private SafeRoom saferoom;
  private MusicManager music;
  private GifManager loadingIMG;

  private Menu menu;

  private static DatabaseManagement db;

  /**
   * Create a new Preloader class.
   * @param scene the Window where the loading screen will be placed.
   */
  public Preloader(Window scene) {
    this.scene = scene;
    new Thread(() -> {
      loadingIMG = new GifManager("loading\\frame ", 60, scene);
    }).start();
    loadThings();
  }


  /**
   * This will be running in the background
   * while a loading screen is shown on the screen.
   */
  public void loadThings() {
    /*
      This goes in the background while there is a
        loading screen displaying.
    */
    new Thread(() -> {

      /* Load these resources. */
      gifBackground = new GifManager("world0\\frame ", 57, scene);
      db = new DatabaseManagement();
      dpC = DataPiratesCollection.getInstance();
      score = Score.getInstance();
      EntityColor.setColors();
      item = Items.getInstance();
      clock = new Timer();
      shop = new Shop(scene);
      cchest = new ChestRoom(scene);
      saferoom = new SafeRoom(scene);
      music = MusicManager.getInstance();
      menu = new Menu(scene);

      /*
        When everything above is finished loading.
       */
      scene.getResource();
      isFinished = true;
//      exit();
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

  /**
   * Checks if the Preloader is finished.
   * @return true if finished, false otherwise
   */
  public boolean isFinished() {
    return isFinished;
  }


  /* Getters and Setters. */

  public DatabaseManagement getDb() {
    return db;
  }

  public static void setDb(DatabaseManagement db) {
    Preloader.db = db;
  }

  public GifManager getLoadingImage() {
    return loadingIMG;
  }

  public GifManager getGifBackground() {
    return gifBackground;
  }
  public DataPiratesCollection getDpC() {
    return dpC;
  }

  public Score getScore() {
    return score;
  }

  public Items getItem() {
    return item;
  }

  public Timer getClock() {
    return clock;
  }

  public Shop getShop() {
    return shop;
  }

  public ChestRoom getCchest() {
    return cchest;
  }

  public SafeRoom getSaferoom() {
    return saferoom;
  }

  public MusicManager getMusic() {
    return music;
  }

  public Menu getMenu() {
    return menu;
  }

}