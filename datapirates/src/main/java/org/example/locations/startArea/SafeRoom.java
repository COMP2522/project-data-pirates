package org.example.locations.startArea;

import org.example.Window;
import org.example.locations.KeyLocationManager;

public class SafeRoom implements KeyLocationManager {

  private Window scene;

  public SafeRoom(Window scene) {
    this.scene = scene;
  }
  @Override
  public void renderLocation() {
    scene.background(129);
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
      scene.getClock().start();
      scene.getPlayer().getPosition().set(scene.getPlayer().getPosition().x, 10);
      scene.setUpEnemies();
    }
  }
}
