package org.example.gui;

import org.example.Main.EntityColor;
import org.example.Main.Window;
import processing.core.PImage;

/**
 * Used in the safe room or the first room the player
 * spawns in.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class Menu extends GuiManager {


  /* Name of the files. */
  private final String[] imagesSTR = {"DP_Title.png", "exit.png", "guide.png"};
  private Shape exit;
  private PImage[] images_Array = new PImage[3];

  public Menu(Window scene) {
    super(scene);
    setUp();
  }

  /**
   * Set up the resources.
   */
  public void setUp() {
    /* Directory where the buttons / title images are found. */
    final String path = "datapirates\\src\\main\\java\\org\\example\\static_assets\\";

    for (int i = 0; i < imagesSTR.length; i++) {
      images_Array[i] = getScene().loadImage(path + imagesSTR[i]);
    }
    final int size = 100;
    final int marginY = 20;
    int firstMenuX = getScene().width / 12;
    int firstMenuY = getScene().height / 15;
    firstMenuY += (size + marginY);

    exit = new Shape(firstMenuX,
            firstMenuY,
            size);
  }

  /**
   * Draw the objects at the title page room.
   */
  public void drawMenuComponents() {
    int allSize = exit.getSize();
    getScene().image(images_Array[1], exit.getxPos(), exit.getyPos(), allSize + allSize, allSize);
    getScene().image(
            images_Array[0],
            (float) Window.WIDTH / 2,
            (float) Window.HEIGHT / 100,
            allSize * 5,
            allSize * 5);

  }

  /**
   * Always check if the player clicks on the buttons.
   */
  public void update() {
    if (overRect(exit.getxPos(), exit.getyPos(), exit.getSize() + exit.getSize(), exit.getSize())) {
      getScene().exit();
    }
  }

}
