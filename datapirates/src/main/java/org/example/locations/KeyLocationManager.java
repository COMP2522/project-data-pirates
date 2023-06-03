package org.example.locations;

import org.example.Main.Window;
import org.example.spriteClasses.Gif;
import org.example.spriteClasses.Player;
import processing.core.PVector;

/**
 * Manages the travelling to different rooms.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class KeyLocationManager {

  protected Window scene;
  protected Gif gM;

  public KeyLocationManager(Window sketch, Gif bg) {
    scene = sketch;
    gM = bg;
  }

  /**
   * Load up the location when the player is about to
   * enter.
   */
  public void renderLocation() {
    if (isAtEdge())
      battleSetup();
  }

  /**
   * Used as a placeholder.
   *
   * @return true when the player is 5 or 10 pixels from the edge.
   */
  public boolean isAtEdge() {
    return true;
  }

  /**
   * Start the wave.
   */
  public void battleSetup() {
    scene.setWorld(0);
    scene.background(0);
    scene.getPreloader().getClock().start();
  }

  /**
   * Used as a condition to check the player's position.
   */
  public void travel() {
    Player p = scene.getPlayer();
    PVector user_pos = p.getPosition();
    final int world = scene.getWorld();

    final int min = 5;
    final int max = 10;
    final boolean atRight = user_pos.x >= Window.WIDTH;
    final boolean atLeft = user_pos.x < min;
    final boolean atUp = user_pos.y < min;
    final boolean atDown = user_pos.y >= Window.HEIGHT;
    if (atRight)
      user_pos.set(Window.WIDTH - max, user_pos.y);
    else if (atLeft)
      user_pos.set(min, user_pos.y);
    else if (atUp)
      user_pos.set(user_pos.x, max);
    else if (atDown && world == 0)
      user_pos.set(user_pos.x, Window.HEIGHT - max);

  }


}
