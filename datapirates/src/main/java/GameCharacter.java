import processing.core.PApplet;
import processing.core.PImage;

public class GameCharacter extends PApplet {
    private PApplet parent;
    private PImage characterImage;

    public GameCharacter(PApplet parent) {
        this.parent = parent;
        characterImage = createCharacterImage();
    }


    public void draw() {
        background(255);
        image(characterImage, 0, 0);
    }

    private PImage createCharacterImage() {
        PImage image = createImage(50, 50, RGB);

        image.loadPixels();
        for (int i = 0; i < image.width * image.height; i++) {
            image.pixels[i] = color(0, 0, 255);
        }
        image.pixels[11] = color(255);
        image.pixels[13] = color(255);
        image.pixels[36] = color(255);
        image.updatePixels();

        return image;
    }
}

