package org.example.locations.shop;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.GifManager;
import processing.core.PVector;

/**
 * Extends Scene manager
 */
public class Shop extends KeyLocationManager {

  private Market market;

  public Shop(Window sketch, GifManager bg) {
    super(sketch, bg);
    market = new Market(new PVector(scene.width / 5, 0),
            new PVector(0, 0)
    , 200, 0, EntityColor.getSpriteColors().get("Reload"), scene);
  }

  @Override
  public void renderLocation() {
    if (!isAtEdge()) {
      scene.background(255);
      draw();
    }
    super.renderLocation();
  }


  @Override
  public void battleSetup() {
    super.battleSetup();
    scene.getPlayer().getPosition().set(scene.width - 10, scene.getPlayer().getPosition().y);
  }

  @Override
  public boolean isAtEdge() {
    return scene.getPlayer().getPosition().x < 5;
  }

  public void draw() {
    market.draw();
  }
}
