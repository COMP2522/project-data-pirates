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
      if (atRight && world == 3) {
        /*
          The Shop.
          TODO {Note}: Might not be implemented.
          See locations\shop
        */
//        world = 1;
        user_pos.set(width - 10, user_pos.y);
  } else if (atLeft && world == 3) {
        /*
          The Daily Chest Room.
          See locations\chestroom
        */
        scene.setWorld(2);
        user_pos.set(width - 10, user_pos.y);
  }
      /* Saferoom. */
      else if (atRight && world == 3)
        user_pos.set(width - 10, user_pos.y);
      else if (atUp && world == 3)
        user_pos.set(user_pos.x, 10);

      /* Chestroom. */
      else if (atLeft && world == 2)
        user_pos.set(5, user_pos.y);
      else if (atUp && world == 2)
        user_pos.set(user_pos.x, 5);
      else if (atDown && world == 2)
        user_pos.set(user_pos.x, height - 10);

      else if (atLeft && world == 0)
        user_pos.set(5, user_pos.y);
      else if (atDown && world == 0)
        user_pos.set(user_pos.x, height - 10);
      else if (atUp && world == 0)
        user_pos.set(user_pos.x, 5);

//      else if (atRight && world == 0)
        /* Shop. */

//      else if (player.getPosition().y >= this.height && world == 0) {
        /*
          Kathyrinollettebellja, the Twelveth Voidylss. (Boss Room)
          TODO {Note}: Might not be implemented in this term. Teddy might do this on his own, because he wants to.
          See locations\happyWorld
        */
//        world = 4;
//        player.getPosition().set(player.getPosition().x, 10);
//      }
  }


}
