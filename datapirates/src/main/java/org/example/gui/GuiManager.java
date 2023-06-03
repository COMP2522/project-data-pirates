package org.example.gui;

import org.example.Main.Window;

/**
 * Manager for the GUI part of Data Pirates.
 * Commonly is used for the buttons.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class GuiManager {

  private final Window scene;

  public GuiManager(Window scene) {
    this.scene = scene;
  }

  /**
   * Checks if the player is clicking within the borders of a button.
   *
   * @param x x coordinate of the button.
   * @param y y coordinate of the button.
   * @param width width of the button.
   * @param height height of the button.
   *
   * @return true when it is clicked in the borders of the button, false otherwise.
   */
  public boolean overRect(int x, int y, int width, int height) {
    return scene.mouseX >= x && scene.mouseX <= x + width
            && scene.mouseY >= y && scene.mouseY <= y + height;
  }

  /* TODO: Getters and Setters beyond this point. */
  public Window getScene() {
    return scene;
  }
}
