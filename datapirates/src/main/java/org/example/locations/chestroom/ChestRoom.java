package org.example.locations.chestroom;

import org.example.EntityColor;
import org.example.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.GifManager;
import processing.core.PApplet;
import processing.core.PVector;

public class ChestRoom implements KeyLocationManager {

  private Window scene;

  private Chest chest;

  private GifManager gM;
  public ChestRoom(Window scene) {
    this.scene = scene;
    chest = new Chest(new PVector(scene.getWidth() / 2, scene.getHeight() / 2),
            new PVector(0, 0), 250, 0, EntityColor.getSpriteColors().get("Reload"), scene);

    gM = new GifManager("world2\\frame ", 48, scene);
  }

  public Chest getChest() {
    return chest;
  }

  @Override
  public void renderLocation() {
//    scene.background(58, 49, 0);
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

    if (scene.getPlayer().getPosition().x >= scene.getWidth()) {
      scene.setWorld(0);
      scene.background(0);
      scene.getClock().start();
      scene.getPlayer().getPosition().set(10, scene.getPlayer().getPosition().y);
      scene.setUpEnemies();
    }
  }

  public void draw() {
    chest.draw();
  }
}
