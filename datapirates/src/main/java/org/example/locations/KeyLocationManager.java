package org.example.locations;

import processing.core.PApplet;

/**
 * Will be used as a super class for all locations in the game.

 */
public interface KeyLocationManager {

//  PApplet mainScene = null;

  void renderLocation();

  boolean isInOrigin();

  void returnToOrigin();


}
