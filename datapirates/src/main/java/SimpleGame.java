import processing.core.PApplet;

/**
 * Made by Chat-GPT.
 *
 * This is just for fun. I'm curious of AI's design.
 * Put this outside of org.example to run.
 */
public class SimpleGame extends PApplet {
  int x, y;

  public static void main(String[] args) {
    PApplet.main("SimpleGame");
  }

  public void settings() {
    size(1920, 1080);
  }

  public void setup() {
    x = width/2;
    y = height/2;
    settings();
  }

  public void draw() {
    background(255);
    ellipse(x, y, 50, 50);
    if (keyPressed) {
      if (key == 'a') {
        x -= 5;
      } else if (key == 'd') {
        x += 5;
      } else if (key == 'w') {
        y -= 5;
      } else if (key == 's') {
        y += 5;
      }
    }
  }
}