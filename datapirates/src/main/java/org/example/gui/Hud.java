package org.example.gui;

import java.awt.Color;
import org.example.Main.EntityColor;
import org.example.Main.Preloader;
import org.example.Main.Window;
import org.example.spriteClasses.Player;


/**
 * Contains the status of menu hud, stats hud, and entity border hud.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18
 */
public class Hud {

  /* Player stat HUD. */
  private boolean isHudActive = false;

  /* Images and buttons on the menu. */
  private boolean menuHudStatus = true;

  /* Those white boundaries around each sprite. */
  private boolean entityBorderStatus = false;

  private final Window scene;
  private final Preloader dataHolder;

  /**
   * Construct a Hud object.
   *
   * @param w sketch where the hud will be displayed.
   * @param data the data class where this will be monitored.
   */
  public Hud(Window w, Preloader data) {
    scene = w;
    dataHolder = data;
  }

  /**
   * Used concurrently with draw() from Window.
   */
  public void updateFrame() {
    if (isMenuHudActive())
      dataHolder.getMenu().drawMenuComponents();
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

    /* The score. */
    scene.textSize(scoreText);
    Color textColor = EntityColor.getSpriteColors().get("Text");
    scene.fill(textColor.getRGB());
    scene.text(dataHolder.getScore().getValue() + "pts", 0, scoreText);

    /* Health points. */
    scene.textSize(playerTextSize);
    scene.fill(textColor.getRGB());
    scene.text("HP " + p.getPlayerStat().getHealth()
                    + "/" + p.getPlayerStat().getMaxHealth(),
            0, scene.height - playerTextSize - playerTextSize);

    /* Ammo information. */
    scene.fill(textColor.getRGB());
    scene.text("AMMO " + p.getWeapon().getCurrentAmmo()
            + "/" + p.getWeapon().getAmmoCapacity()
            + "", 0, scene.height - playerTextSize);

    /* Time till next wave. */
    scene.fill(textColor.getRGB());
    scene.text("Next: " + (int) dataHolder.getClock().getEstimated()
            + "s", 0, playerTextSize + playerTextSize);

  }

  /* TODO: Getters and Setters beyond this point. */
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
