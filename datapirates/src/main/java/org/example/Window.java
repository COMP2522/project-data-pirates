package org.example;

import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class Window extends PApplet {

  protected Player player;

  private Timer timer;

  private int numEnemies;

  private GameCharacter character;


  /* Min size of entities. */
  private int minSize;

  /* Max size of entities. */
  private int maxSize;
  int width = 1080;
  int height = 720;

  public void settings() {
    size(width, height);
  }
  public void setup() {
    settings();
    this.init();
    character = new GameCharacter(this);
    character.setup();
  }

  public void init() {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    super.keyPressed(e);
  }

  @Override
  public void mouseClicked() {
    super.mouseClicked();
  }

  @Override
  public void draw() {
    super.draw();
  }

  public void remove(Sprite s) {}

  public void removeBullet(Sprite b) {}

  public static void main(String[] args) {
    PApplet.runSketch(new String[] {"Window"}, new Window());
  }
}
