package org.example;

import processing.core.PVector;

import java.awt.*;

public class Projectile extends Sprite{

  private Sprite projectile;

  public Projectile(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
    super(pos, direction, size, speed, clr, scene);
  }


  //make the projectile shoot towards the player
  public void enemyshoot(Player player) {
    PVector playerPos = player.getPosition();
    PVector direction = playerPos.sub(this.getPosition()).normalize();
    this.setDirection(direction);
    // apply the same movement calculation as before
    float deltaTime = 1.0f/ window.frameCount; // assuming a constant frame rate
    PVector movement = this.getDirection().mult(this.getSpeed() * deltaTime);
    this.setPosition(this.getPosition().add(movement));
  }
}

class ProjectileManager {

//  private static Player player;
//
//  private Weapon weapon;
//
//  private Projectile projectile;
//
//  public ProjectileManager(Player p) {
//    player = p;
//  }
//

}
