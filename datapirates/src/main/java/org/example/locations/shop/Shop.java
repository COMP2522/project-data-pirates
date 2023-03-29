package org.example.locations.shop;

import org.example.EntityColor;
import org.example.Window;
import org.example.locations.KeyLocationManager;
import processing.core.PVector;

/**
 * Extends Scene manager
 */
public class Shop implements KeyLocationManager {

  private Window scene;
  private Market market;

  public Shop(Window scene) {
    this.scene = scene;
    market = new Market(new PVector(scene.getWidth() / 5, 0),
            new PVector(0, 0)
    , 200, 0, EntityColor.getSpriteColors().get("Reload"), scene);
  }

  @Override
  public void renderLocation() {
    scene.background(255);
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

//      scene.setUpEnemies();
      if (scene.getPlayer().getPosition().x < 5) {
//    System.out.println(scene.getPlayer().getPosition().x);
//        scene.background(255);
        scene.setWorld(0);
        scene.background(0);
        scene.getClock().start();
        scene.getPlayer().getPosition().set(scene.getWidth() - 10, scene.getPlayer().getPosition().y);
        scene.setUpEnemies();
//        travelBoolean = false;
      }
    }


  public void draw() {
    market.draw();
  }
}
