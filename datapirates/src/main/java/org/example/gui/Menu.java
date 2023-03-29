package org.example.gui;

import org.example.EntityColor;
import org.example.Window;

import javax.swing.*;
import java.awt.*;

public class Menu extends GuiManager{


  // Play or Resume
  private Shape play;

  private Shape settings;

  private Shape loadData;

  private Shape exit;

  public Menu(Window scene) {
    super(scene);
    int firstMenuX = getScene().getWidth() / 12;
    int firstMenuY = getScene().getHeight() / 15;
    play = new Shape(firstMenuX,
            firstMenuY,
            100,
            EntityColor.getSpriteColors().get("Menu Buttons"));

    firstMenuY+=(play.getSize() + 20);
    settings = new Shape(firstMenuX,
            firstMenuY,
            play.getSize(),
            EntityColor.getSpriteColors().get("Minal Laser"));
    firstMenuY+=(play.getSize()+ 20);
    loadData = new Shape(firstMenuX,
            firstMenuY,
            play.getSize(),
            EntityColor.getSpriteColors().get("Menu Buttons"));
    firstMenuY+=(play.getSize() + 20);
    exit = new Shape(firstMenuX,
            firstMenuY,
            play.getSize(),
            EntityColor.getSpriteColors().get("Menu Buttons"));
  }

  public void drawAtSafe() {
    getScene().pushStyle();
//    getScene().stroke(play.getColor().getRGB());
    getScene().fill(play.getColor().getRGB());
    getScene().rect(play.getxPos(), play.getyPos(), play.getSize() + (int) (play.getSize() * 1.5), play.getSize());
    getScene().textSize(100);
    getScene().fill(EntityColor.getSpriteColors().get("Terminator X").getRGB());
    getScene().text("Play", (play.getxPos() + (play.getSize() / 2)), (play.getyPos() + (play.getSize() / 2)));
//    getScene().popStyle();
    getScene().fill(settings.getColor().getRGB());
    getScene().rect(settings.getxPos(), settings.getyPos(), settings.getSize() + (int) (settings.getSize() * 1.5), settings.getSize());

    getScene().fill(loadData.getColor().getRGB());
    getScene().rect(loadData.getxPos(), loadData.getyPos(), loadData.getSize() + (int) (loadData.getSize() * 1.5), loadData.getSize());

    getScene().fill(exit.getColor().getRGB());
    getScene().rect(exit.getxPos(), exit.getyPos(), exit.getSize() + (int) (exit.getSize() * 1.5), exit.getSize());
    getScene().pushStyle();
//    super.draw();
  }

  @Override
  public void draw() {

  }
}
