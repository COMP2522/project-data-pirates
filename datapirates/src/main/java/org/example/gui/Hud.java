package org.example.gui;

import org.example.Main.EntityColor;
import org.example.Main.Preloader;
import org.example.Main.Window;
import org.example.Main.WindowHelper;
import org.example.spriteClasses.Player;

import java.awt.*;

/**
 * To be announced.
 *
 *
 */
public class Hud {

  /* Player stat HUD. */
  private boolean isHudActive = false;

  /* */
  private boolean menuHudStatus = true;

  /*
    Entity Border Status.
  */
  private boolean entityBorderStatus = false;

  private Window scene;

  private Preloader dataHolder;


  public Hud(Window w, Preloader data) {
    scene = w;
    dataHolder = data;

  }


  public void updateFrame() {
    /*
      Menu HUD. Contains the Play, Settings, Load Data, Exit buttons.
    */
    if (isMenuHudActive())
      dataHolder.getMenu().drawAtSafe();

    if (isHudActive())
      printDisplayText();

  }
  /**
   * Get the information of your stats and the wave time.
   */
  public void printDisplayText() {

    Player p = scene.getPlayer();
    final int scoreText = 20;
    final int playerTextSize = scoreText + 10;
    /*
      The score
    */
    scene.textSize(scoreText);
    Color textColor = EntityColor.getSpriteColors().get("Text");
    scene.fill(textColor.getRGB());

    scene.text(dataHolder.getScore().getValue() + "pts", 0, scoreText);


    /*
      Health Points.
    */
    scene.textSize(playerTextSize);
    scene.fill(textColor.getRGB());
    scene.text("HP " + p.getPlayerStat().getHealth()
                    + "/" + p.getPlayerStat().getMaxHealth(),
            0, scene.height - playerTextSize - playerTextSize);

    /*
      Ammo Capacity.
    */
    scene.fill(textColor.getRGB());
    scene.text("AMMO " + p.getWeapon().getCurrentAmmo()
            + "/" + p.getWeapon().getAmmoCapacity()
            + "", 0, scene.height - playerTextSize);

    /*
      Wave Time.
    */
    scene.fill(textColor.getRGB());
    scene.text("Next: " + (int) dataHolder.getClock().getEstimated()
            + "s", 0, playerTextSize + playerTextSize);

  }

  public void setMenuHudStatus(boolean menuHudStatus) {
    this.menuHudStatus = menuHudStatus;
  }

  public void setEntityBorderStatus(boolean entityBorderStatus) {
    this.entityBorderStatus = entityBorderStatus;
  }

  public void setHudStatus(boolean hudActive) {
    isHudActive = hudActive;
  }

  public boolean isHudActive() {
    return isHudActive;
  }


  public boolean isEntityBorderActive() {
    return entityBorderStatus;
  }

  public boolean isMenuHudActive() {
    return menuHudStatus;
  }
}
