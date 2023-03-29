package org.example.gui;

import org.example.Window;

public class GuiManager {

  private Window scene;

  public GuiManager(Window scene) {
    this.scene = scene;
  }

  public void draw() {}

  public boolean overRect(int x, int y, int width, int height) {
    return true;
  }

  public Window getScene() {
    return scene;
  }
}
