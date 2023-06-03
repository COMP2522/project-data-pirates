package org.example.Main;

import java.util.Random;
import org.example.database.DatabaseManagement;
import org.example.gui.Hud;
import org.example.gui.Menu;
import org.example.locations.startArea.SafeRoom;
import org.example.spriteClasses.Gif;

/**
 * Activates before the game starts.
 * This is to prevent the performance delays when starting the game.
 *
 * @author Data Pirates Team
 *
 * @version JDK 18.
 */
public class Preloader {

  /*
    Just a bunch of variables here. The things
      that make the program slow in the beginning.
        Will be created in a thread.
  */

  public static final Random RNG = new Random();
  public static DatabaseManagement db;
  public DataPiratesCollection dpC;
  private Gif gifBackground;
  private final Window scene;
  private Score score;
  private Timer clock;
  private SafeRoom saferoom;
  private Gif loadingIMG;
  private Menu menu;
  private Hud dp_Hud;

  /* Preloader loading status. */
  private boolean isFinished = false;

  /**
   * Create a new Preloader class.
   *
   * @param scene the Window where the loading screen will be placed.
   */
  public Preloader(Window scene) {
    this.scene = scene;
    new Thread(() -> loadingIMG = new Gif("loading\\frame ", 60, scene)).start();
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
      gifBackground = new Gif("world0\\frame ", 57, scene);
      saferoom = new SafeRoom(scene, new Gif("world3\\frame ", 33, scene));
      EntityColor.setColors();
      score = Score.getInstance();
      dp_Hud = new Hud(scene, this);
      db = new DatabaseManagement(score);
      dpC = DataPiratesCollection.getInstance();
      Weapons.weaponStash();
      clock = new Timer();
      menu = new Menu(scene);

      /*
        When everything above is finished loading.
       */
      scene.getResource();
      isFinished = true;
      try {
        Thread.sleep(100); /* constant is only used here. */
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

  /**
   * Checks if the Preloader is finished.
   *
   * @return true if finished, false otherwise.
   */
  public boolean isFinished() {
    return isFinished;
  }

  /* TODO: Getters and Setters. */

  public Gif getLoadingImage() {
    return loadingIMG;
  }

  public Gif getGifBackground() {
    return gifBackground;
  }

  public DataPiratesCollection getDpC() {
    return dpC;
  }

  public Hud getDp_Hud() {
    return dp_Hud;
  }

  public Score getScore() {
    return score;
  }

  public Timer getClock() {
    return clock;
  }

  public SafeRoom getSaferoom() {
    return saferoom;
  }


  public Menu getMenu() {
    return menu;
  }

}