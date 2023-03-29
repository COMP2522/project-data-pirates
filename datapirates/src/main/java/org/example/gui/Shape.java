package org.example.gui;

import java.awt.*;

public class Shape {


  private int xPos;

  private int yPos;

  private int size;

  private final Color color;
  public Shape(int x, int y, int size, Color shapeColor) {
    xPos = x;
    yPos = y;
    this.size = size;
    color = shapeColor;
  }

  public Color getColor() {
    return color;
  }

  public int getSize() {
    return size;
  }

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

}
