package org.example.locations.chestroom;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import org.example.locations.KeyLocationManager;
import org.example.spriteClasses.GifManager;
import processing.core.PVector;

public class ChestRoom extends KeyLocationManager {

  private Chest chest;


  public ChestRoom(Window sketch, GifManager bg) {
    super(sketch, bg);
    chest = new Chest(new PVector(scene.width/ 2, scene.height / 2),
            new PVector(0, 0), 250, 0, EntityColor.getSpriteColors().get("Reload"), scene);
//
  }

  public Chest getChest() {
    return chest;
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
//    super.battleSetup();
    scene.setWorld(3);
    scene.background(0);
    scene.getPreloader().getClock().start();
    scene.getPlayer().getPosition().set(10, scene.getPlayer().getPosition().y);

  }

  @Override
  public boolean isAtEdge() {
    return scene.getPlayer().getPosition().x >= scene.width;
  }

  public void draw() {
//    chest.draw();
  }
}
