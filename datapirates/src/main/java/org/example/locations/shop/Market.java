package org.example.locations.shop;

import org.example.spriteClasses.Sprite;
import org.example.Window;
import processing.core.PVector;

import java.awt.*;

public class Market extends Sprite {

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
  public Market(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }

  @Override
  public void draw() {
    this.getWindow().pushStyle();
//    player.getWindow().pushStyle();
    Color color = this.getColor();
    this.getWindow().fill(color.getRed(), color.getGreen(), color.getBlue());
    this.getWindow().rect(this.getPosition().x, this.getPosition().y, this.getSize() * 3, this.getSize());
    this.getWindow().popStyle();
  }
}
