package org.example.locations.startArea;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.GifManager;

/**
 * Safe Room known as the Menu.
 *
 */
public class SafeRoom extends KeyLocationManager {

  public SafeRoom(Window sketch, GifManager bg) {
    super(sketch, bg);
  }
  @Override
  public void renderLocation() {
    if (!isAtEdge()) {
      gM.displayBackground();
      draw();
    }
    super.renderLocation();
  }


  @Override
  public void battleSetup() {
    super.battleSetup();
    scene.getPlayer().getPosition().set(scene.getPlayer().getPosition().x, 10);
  }

  @Override
  public boolean isAtEdge() {
    return scene.getPlayer().getPosition().y >= scene.getHeight();
  }

  public void draw() {
    final int titleTextSize = scene.getWidth() / 10;
    scene.textSize(titleTextSize);
    scene.fill(EntityColor.getSpriteColors().get("Text").getRGB());
    scene.text("Data\nPirates", scene.getWidth() / 2, scene.getHeight() / 5);

  }
}
