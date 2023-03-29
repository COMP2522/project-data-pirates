package org.example.locations;

import processing.core.PApplet;

public interface KeyLocationManager {

//  PApplet mainScene = null;

  void renderLocation();

  boolean isInOrigin();

  void returnToOrigin();


}
