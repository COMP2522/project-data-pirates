package org.example;

import processing.core.PVector;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

  private Weapon weapon;



  public Player(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }


  public void move(KeyEvent event) {}

  public void shoot() {}

}
