package org.example.gui;
import org.example.Main.Window;
public class DeathGUI {

  // use json or database to get their deaths
  private static int deathCounter = 0;

  private Window scene;

  public DeathGUI(Window scene) {
    this.scene = scene;
  }

  public static void setDeathCounter(int deathCounter) {
    DeathGUI.deathCounter = deathCounter;
  }

  public void displayDeathScreen() {
    scene.background(0);
    // add a list of texts idk.

  }
}
