package org.example.spriteClasses;

import org.example.Main.Window;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * Displays gif animations, allows sprites to be more alive
 * and background to be less boring.
 *
 * @author Data Pirates Team and many attempts with ChatGPT.
 *
 * @version JDK 18.
 */
public class Gif {

  /* The same phrase that identifies the pngs. */
  private final String fileName;

  /* File names in a list. */
  private final String[] spriteFiles;

  /* Load them first because they cause loss of performance. */
  private final PImage[] frameImages;

  /* Amount of files. */
  private final int amountOfSprites;
  private final Window scene;

  /* Current frame index in the display system. */
  private int frames;


  /**
   * Construct a Gif Manager for Sprites.
   *
   * @param fileNames general file name.
   * @param numberOfSprites amount of image files.
   * @param scene sketch where the Gif will be displayed.
   * @param s sprite object.
   *
   */
  public Gif(String fileNames, int numberOfSprites, Window scene, Sprite s) {
    fileName = fileNames;
    spriteFiles = new String[numberOfSprites];
    amountOfSprites = numberOfSprites;
    this.scene = scene;
    frames = 0;
    frameImages = new PImage[numberOfSprites];
    setSprites(s);
  }

  /**
   * Construct a Gif Manager for backgrounds.
   *
   * @param fileNames general file name.
   * @param numberOfSprites amount of image files.
   * @param scene sketch where the Gif will be displayed.
   *
   */
  public Gif(String fileNames, int numberOfSprites, Window scene) {
    fileName = fileNames;
    spriteFiles = new String[numberOfSprites];
    amountOfSprites = numberOfSprites;
    this.scene = scene;
    frames = 0;
    frameImages = new PImage[numberOfSprites];

    setGifDisplays();
  }

  /**
   * Setup method for sprites.
   * Load up the files.
   *
   * @param s sprite object.
   *
   */
  public void setSprites(Sprite s) {
    for (int i = 0; i < amountOfSprites; i++) {
      spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\spriteClasses\\sprites\\"
              + fileName + "(" + (i + 1) + ")" + ".png";
      frameImages[i] = scene.loadImage(spriteFiles[i]);
      frameImages[i].resize((int) s.getSize(), (int) s.getSize());
    }
  }

  /**
   * Setup method for gif backgrounds.
   * Load up the files.
   */
  public void setGifDisplays() {
    for (int i = 0; i < amountOfSprites; i++) {
      spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\backgrounds\\"
              + fileName + "(" + (i + 1) + ")" + ".png";
      frameImages[i] = scene.loadImage(spriteFiles[i]);
      frameImages[i].resize(scene.width, scene.height);
    }
  }

  /**
   * Display gif for sprite.
   *
   * @param s sprite object.
   *
   */
  public void display(Sprite s) {
    if (frames == (amountOfSprites - 1))
      frames = 0;
    scene.imageMode(PConstants.CENTER);
    scene.image(frameImages[frames],
            s.getPosition().x, s.getPosition().y,
            s.getSize(), s.getSize());
    scene.imageMode(PConstants.CORNER);
    frames++;

  }

  /**
   * Display gif for backgrounds.
   */
  public void displayBackground() {
    if (frames == (amountOfSprites - 1))
      frames = 0;
    scene.image(frameImages[frames], 0, 0);
    frames++;
  }

}
