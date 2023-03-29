package org.example.locations.chestroom;

import org.example.spriteClasses.GifManager;
import org.example.spriteClasses.Sprite;
import org.example.Window;
import org.example.spriteClasses.SpriteManager;
import processing.core.PVector;

import java.awt.*;

public class Chest extends Sprite {

  private boolean opened = false;

  /**
   * Create a Sprite object.
   *
   * @param pos       Current position of the sprite.
   * @param direction Current direction of the sprite.
   * @param size      Size of the sprite.
   * @param speed     Speed of the sprite.
   * @param clr       Color of the sprite.
   * @param scene     The Frame where the sprite is displayed.
   */
  public Chest(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
    this.setMm(new GifManager("chest\\frame ", 150, getPosition(), getWindow(), this));
  }


  @Override
  public void draw() {
    SpriteManager.assignSprite(this, getMm());
//    this.getWindow().pushStyle();
//    Color color = this.getColor();
//    this.getWindow().fill(color.getRed(), color.getGreen(), color.getBlue());
//    this.getWindow().rect(this.getPosition().x, this.getPosition().y, this.getSize() * 3, this.getSize());
//    this.getWindow().popStyle();
  }

  public boolean isOpened() {
    return opened;
  }

  public void setOpened(boolean opened) {
    this.opened = opened;
  }
}
