package org.example;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Menu {

    PApplet parent;
    private boolean isVisible;
    public final HashMap<String, PVector> buttons;

    private PImage startButton;
    private PImage highScoreButton;
    private PImage creditsButton;
    private PImage quitButton;

    public Menu(PApplet parent) {
        this.parent = parent;
        isVisible = false;
        buttons = new HashMap<>();
        this.startButton = parent.loadImage("datapirates\\src\\main\\play0.png");
        this.highScoreButton = parent.loadImage("datapirates\\src\\main\\highscore0.png");
        this.creditsButton = parent.loadImage("datapirates\\src\\main\\credits0.png");
        this.quitButton = parent.loadImage("datapirates\\src\\main\\quit0.png");
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void draw() {
        if (!isVisible) return;

        parent.pushStyle();

        // Set the size of the buttons
        int buttonWidth = 200;
        int buttonHeight = 75;

        // Set the padding between buttons
        int buttonPadding = 20;

        // Set the positions of the buttons
        PVector startPosition = new PVector(parent.width / 2 - buttonWidth / 2, parent.height / 2 - 2 * (buttonHeight + buttonPadding));
        PVector highScorePosition = new PVector(parent.width / 2 - buttonWidth / 2, startPosition.y + buttonHeight + buttonPadding);
        PVector creditsPosition = new PVector(parent.width / 2 - buttonWidth / 2, highScorePosition.y + buttonHeight + buttonPadding);
        PVector quitPosition = new PVector(parent.width / 2 - buttonWidth / 2, creditsPosition.y + buttonHeight + buttonPadding);

        // Draw the button images and store the button positions
        parent.image(startButton, startPosition.x, startPosition.y, buttonWidth, buttonHeight);
        buttons.put("start", startPosition);

        parent.image(highScoreButton, highScorePosition.x, highScorePosition.y, buttonWidth, buttonHeight);
        buttons.put("highScore", highScorePosition);

        parent.image(creditsButton, creditsPosition.x, creditsPosition.y, buttonWidth, buttonHeight);
        buttons.put("credits", creditsPosition);

        parent.image(quitButton, quitPosition.x, quitPosition.y, buttonWidth, buttonHeight);
        buttons.put("quit", quitPosition);

        parent.popStyle();
    }
}
