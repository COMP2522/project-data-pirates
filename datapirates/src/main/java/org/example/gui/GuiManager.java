package org.example.gui;

import org.example.Main.Window;

public class GuiManager {

  private Window scene;

  public GuiManager(Window scene) {
    this.scene = scene;
  }

  public void draw() {}

  public boolean overRect(int x, int y, int width, int height) {
    if (scene.mouseX >= x && scene.mouseX <= x+width &&
            scene.mouseY >= y && scene.mouseY <= y+height) {
      return true;
    } else {
      return false;
    }
  }

  public Window getScene() {
    return scene;
  }
}
