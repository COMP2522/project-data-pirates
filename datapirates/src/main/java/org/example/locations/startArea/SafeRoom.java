package org.example.locations.startArea;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.GifManager;

/**
 * Safe Room known as the Menu.
 *
 */
public class SafeRoom implements KeyLocationManager {

  private Window scene;

  private GifManager gM;
  public SafeRoom(Window scene) {
    this.scene = scene;
    gM = new GifManager("world3\\frame ", 33, scene);
  }
  @Override
  public void renderLocation() {
    gM.displayBackground();
    draw();
  }

  @Override
  public boolean isInOrigin() {
    return scene.getWorld() == 0;
  }

  @Override
  public void returnToOrigin() {
    if (!isInOrigin()) {
      renderLocation();
    }

    if (scene.getPlayer().getPosition().y >= scene.getHeight()) {
      scene.setWorld(0);
      scene.background(0);
      scene.getPreloader().getClock().start();
      scene.getPlayer().getPosition().set(scene.getPlayer().getPosition().x, 10);
    }
  }

  public void draw() {
    final int titleTextSize = scene.getWidth() / 10;
    scene.textSize(titleTextSize);
    scene.fill(EntityColor.getSpriteColors().get("Text").getRGB());
    scene.text("Data\nPirates", scene.getWidth() / 2, scene.getHeight() / 5);

  }
}
