package org.example.gui;

import org.example.Main.EntityColor;
import org.example.Main.Window;

public class Menu extends GuiManager{


  private Shape settings;

  private Shape loadData;

  private Shape exit;

  public Menu(Window scene) {
    super(scene);
    int firstMenuX = getScene().getWidth() / 12;
    int firstMenuY = getScene().getHeight() / 15;
    int size = 100;
    int marginY = 20;

    /* Will reformat soon. */
    firstMenuY+=(size + marginY);
    firstMenuY+=(size + marginY);
    firstMenuY+=(size + marginY);
    exit = new Shape(firstMenuX,
            firstMenuY,
            size,
            EntityColor.getSpriteColors().get("Menu Buttons"));
  }


  public void drawAtSafe() {
    getScene().pushStyle();
    int allSize = exit.getSize();
    int textSizee = allSize / 3;

    /* There are some things in here that need to be changed. */

    /* Get the leaderboard status and display it. */
    getScene().fill(exit.getColor().getRGB());
    getScene().rect(exit.getxPos(), exit.getyPos(), allSize + allSize, allSize);
    getScene().textSize(textSizee);
    getScene().fill(EntityColor.getSpriteColors().get("Terminator X").getRGB());
    getScene().text("Exit", (exit.getxPos()), exit.getyPos() + textSizee + (textSizee / 2));
    getScene().pushStyle();

//    super.draw();
  }

  public void update() {
    if (overRect(exit.getxPos(), exit.getyPos(), exit.getSize() + exit.getSize(), exit.getSize())) {
      getScene().exit();
    }
  }
}
