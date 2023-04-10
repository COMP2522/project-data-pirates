package org.example.locations.startArea;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.Gif;

/**
 * Title page room / Menu / Safe room.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class SafeRoom extends KeyLocationManager {

  /**
   * Construct a SafeRoom object.
   *
   * @param sketch sketch where the room is going to be placed.
   * @param bg gif background.
   */
  public SafeRoom(Window sketch, Gif bg) {
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
    return scene.getPlayer().getPosition().y >= scene.height;
  }

  /**
   * Display other components in the safe room.
   */
  public void draw() {
    final int titleTextSize = scene.height / 15;
    scene.textSize(titleTextSize);
    scene.fill(EntityColor.getSpriteColors().get("Text").getRGB());
    scene.text("High Score: " + scene.getPreloader().getScore().getHighScore() + "pts",
            (float) Window.WIDTH / 2, Window.HEIGHT - titleTextSize);

  }
}
