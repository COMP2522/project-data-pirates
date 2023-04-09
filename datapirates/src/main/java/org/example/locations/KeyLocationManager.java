package org.example.locations;

import org.example.Main.Window;
import org.example.spriteClasses.GifManager;
import org.example.spriteClasses.Player;
import processing.core.PVector;

/**
 * Will be used as a super class for all locations in the game.

 */
public class KeyLocationManager {

  protected Window scene;

  protected GifManager gM;


  public KeyLocationManager(Window sketch, GifManager bg) {
    scene = sketch;
    gM = bg;
  }
  public void renderLocation() {
    if (isAtEdge())
      battleSetup();
  }
  public boolean isAtEdge() {
    return true;
  }
  public void battleSetup() {
    scene.setWorld(0);
    scene.background(0);
    scene.getPreloader().getClock().start();
  }
  public void travel() {
    Player p = scene.getPlayer();
    PVector user_pos = p.getPosition();
    int width = scene.width;
    int height = scene.height;
    int world = scene.getWorld();

    final boolean atRight = user_pos.x >= width;
    final boolean atLeft = user_pos.x < 5;
    final boolean atUp = user_pos.y < 5;
    final boolean atDown = user_pos.y >= height;
      if (atRight)
        user_pos.set(width - 10, user_pos.y);
      else if (atLeft)
        user_pos.set(5, user_pos.y);
      else if (atUp)
        user_pos.set(user_pos.x, 10);

      else if (atDown && world == 0)
        user_pos.set(user_pos.x, height - 10);

  }


}
