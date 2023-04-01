package org.example;

import processing.core.PVector;

import java.awt.*;

public class ProjectileType1 extends Projectile {

    public ProjectileType1(PVector pos, PVector direction, float size, float speed, Color clr, Window scene) {
        super(pos, direction, size, speed, clr, scene);
    }

//    @Override
//    public void display() {
//        scene.pushMatrix();
//        scene.translate(pos.x, pos.y);
//        scene.fill(clr.getRGB());
//        scene.ellipse(0, 0, size, size);
//        scene.popMatrix();
//    }

}
