package org.example.spriteClasses;

import processing.core.PImage;
import processing.core.PVector;

import org.example.Window;
// Sprites only
// IDLE and Movin
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
    spriteFiles = new String[numberOfSprites];
    amountOfSprites = numberOfSprites;
    this.scene = scene;
    frames = 0;
    frameImages = new PImage[numberOfSprites];
    setGifDisplays(fileNames);
  }
  public boolean isMoving(PVector position) {
//    if (prev.x != )
    return prev.x != position.x && prev.y != position.y;
  }

  public boolean isMoving(Player p) {
    return p.getDiag().aKeyPressed();
  }
  public void move(PVector position) {
    prev.set(position.x, position.y);
  }
  public void setSprites(String fileNames, Sprite s) {
//    datapirates\\src\\main\\java\\org\\example\\music\\";
    for (int i = 0; i < amountOfSprites; i++) {
      spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\spriteClasses\\sprites\\" + fileNames + "(" + (i + 1) + ")" + ".png";
      frameImages[i] = scene.loadImage(spriteFiles[i]);
      frameImages[i].resize((int) s.getSize(), (int) s.getSize());
    }
  }

  public void setGifDisplays(String fileNames) {
    for (int i = 0; i < amountOfSprites; i++) {
      spriteFiles[i] = "datapirates\\src\\main\\java\\org\\example\\backgrounds\\" + fileNames + "(" + (i + 1) + ")" + ".png";
      frameImages[i] = scene.loadImage(spriteFiles[i]);
      frameImages[i].resize(scene.getWidth(), scene.getHeight());
    }
  }
  public void display(Sprite s) {
//    for (int i = 0; i < amountOfSprites; i++) {
    if (frames == (amountOfSprites - 1))
      frames = 0;
      scene.image(frameImages[frames], s.getPosition().x - s.getSize() / 2, s.getPosition().y - s.getSize() / 3, s.getSize(), s.getSize());
      frames++;
//    }
  }

  public void displayIdle(Sprite s) {
//    img = scene.loadImage(spriteFiles[0]);
//    img.resize((int) s.getSize(), (int) s.getSize());
    scene.image(frameImages[0], s.getPosition().x - s.getSize() / 2, s.getPosition().y - s.getSize() / 3, s.getSize(), s.getSize());
  }

  public void displayBackground() {
    if (frames == (amountOfSprites - 1))
      frames = 0;
    scene.image(frameImages[frames], 0, 0);
    frames++;
  }
}
