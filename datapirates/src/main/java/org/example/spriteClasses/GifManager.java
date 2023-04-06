package org.example.spriteClasses;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import org.example.Main.Window;
// Sprites only
// IDLE and Movin

/**
 * Displays gif animations, allows sprites to be more alive
 * and background to be less boring.
 *
 * @author Teddy Dumam-Ag
 */
public class GifManager {

  PVector prev = new PVector();
  String[] spriteFiles;

  PImage[] frameImages;
  int amountOfSprites;

  private Window scene;

  private PImage img;
  int frames;

  public GifManager(String fileNames, int numberOfSprites, PVector position, Window scene, Sprite s) {
    prev.set(position.x, position.y);
    spriteFiles = new String[numberOfSprites];
    amountOfSprites = numberOfSprites;
    this.scene = scene;
    frames = 0;
    frameImages = new PImage[numberOfSprites];
    setSprites(fileNames, s);
  }

  public GifManager(String fileNames, int numberOfSprites, Window scene) {
//    if (scene.getWorld() == 3) {

//    }
    spriteFiles = new String[numberOfSprites];
    amountOfSprites = numberOfSprites;
    this.scene = scene;
    frames = 0;
    frameImages = new PImage[numberOfSprites];


    setGifDisplays(fileNames);
  }



  public boolean isLoaded() {
    return frameImages[amountOfSprites - 1] != null;
  }
  public boolean isMoving(PVector position) {
//    if (prev.x != )
    return prev.x != position.x && prev.y != position.y;
  }

//  public boolean isMoving(Player p) {
//    return p.getDiag().aKeyPressed();
//  }
  public void move(PVector position) {
    prev.set(position.x, position.y);
  }
  public void setSprites(String fileNames, Sprite s) {
//    datapirates\\src\\main\\java\\org\\example\\music\\";
//    new Thread(() -> {
      for (int i = 0; i < amountOfSprites; i++) {
        spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\spriteClasses\\sprites\\" + fileNames + "(" + (i + 1) + ")" + ".png";
        frameImages[i] = scene.loadImage(spriteFiles[i]);
        frameImages[i].resize((int) s.getSize(), (int) s.getSize());
      }
//    }).start();
  }

  public void setGifDisplays(String fileNames) {
//    new Thread(() -> {
      for (int i = 0; i < amountOfSprites; i++) {
        spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\backgrounds\\" + fileNames + "(" + (i + 1) + ")" + ".png";
        frameImages[i] = scene.loadImage(spriteFiles[i]);
        frameImages[i].resize(scene.width, scene.height);
      }
//    }).start();
  }
  public void display(Sprite s) {
//    for (int i = 0; i < amountOfSprites; i++) {
    if (frames == (amountOfSprites - 1))
      frames = 0;
      scene.imageMode(PConstants.CENTER);
      scene.image(frameImages[frames], s.getPosition().x, s.getPosition().y, s.getSize(), s.getSize());
      scene.imageMode(PConstants.CORNER);
      frames++;

//    }
  }

  public void displayIdle(Sprite s) {
//    img = scene.loadImage(spriteFiles[0]);
//    img.resize((int) s.getSize(), (int) s.getSize());
    scene.imageMode(PConstants.CENTER);
    scene.image(frameImages[0], s.getPosition().x - s.getSize() / 2, s.getPosition().y - s.getSize() / 3, s.getSize(), s.getSize());
    scene.imageMode(PConstants.CORNER);
  }

  public void displayBackground() {
    if (frames == (amountOfSprites - 1))
      frames = 0;

    scene.image(frameImages[frames], 0, 0);
    frames++;
  }

  public int getAmountOfSprites() {
    return amountOfSprites;
  }
}
