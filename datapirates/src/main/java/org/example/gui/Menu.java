package org.example.gui;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import processing.core.PConstants;
import processing.core.PImage;

public class Menu extends GuiManager {


  private Shape instructions;
  private Shape exit;

  private boolean instructionPanel = false;
  private PImage instructionPhoto;
  public Menu(Window scene) {
    super(scene);

    instructionPhoto = scene.loadImage("datapirates\\src\\main\\java\\org\\example\\gui\\instructions.png");
    instructionPhoto.resize(scene.getWidth(), scene.getHeight());
    int firstMenuX = getScene().getWidth() / 12;
    int firstMenuY = getScene().getHeight() / 15;
    int size = 100;
    int marginY = 20;



    /* Will reformat soon. */
    firstMenuY+=(size + marginY);
    firstMenuY+=(size + marginY);
    instructions = new Shape(firstMenuX,
            firstMenuY,
            size,
            EntityColor.getSpriteColors().get("Menu Buttons"));
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
    getScene().fill(instructions.getColor().getRGB());
    getScene().rect(instructions.getxPos(), instructions.getyPos(), allSize + allSize, allSize);
    getScene().textSize(textSizee);
    getScene().fill(EntityColor.getSpriteColors().get("Terminator X").getRGB());
    getScene().text("Instructions", (instructions.getxPos()), instructions.getyPos() + textSizee + (textSizee / 2));

    /* Get the leaderboard status and display it. */
    getScene().fill(exit.getColor().getRGB());
    getScene().rect(exit.getxPos(), exit.getyPos(), allSize + allSize, allSize);
    getScene().textSize(textSizee);
    getScene().fill(EntityColor.getSpriteColors().get("Terminator X").getRGB());
    getScene().text("Exit", (exit.getxPos()), exit.getyPos() + textSizee + (textSizee / 2));
    getScene().pushStyle();

    if (instructionPanel) {
      getScene().imageMode(PConstants.CENTER);
      getScene().image(instructionPhoto, getScene().getWidth() / 2, getScene().getHeight() / 2);
      getScene().imageMode(PConstants.CORNER);

    }

//    super.draw();
  }

  public void update() {
    if (overRect(exit.getxPos(), exit.getyPos(), exit.getSize() + exit.getSize(), exit.getSize())) {
      getScene().exit();
    } else if (overRect(instructions.getxPos(), instructions.getyPos(), instructions.getSize() + instructions.getSize(), instructions.getSize())) {
      instructionPanel = true;
    }
  }

  public boolean isINPanelActive() {
    return instructionPanel;
  }

  public void setInstructionPanel(boolean instructionPanel) {
    this.instructionPanel = instructionPanel;
  }
}
