package com.he4xi.firstgame.entity.projectiles;

import com.he4xi.firstgame.entity.emitter.ParticleEmitter;
import com.he4xi.firstgame.entity.particles.Particle;
import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Class for main projectiles
 *
 * Created on 29.11.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class MainProjectile extends Projectile{
    /**
     * Constructor for main projectiles.
     * Gives values to variables.
     * @param x Initial position of projectile on X axis (where it's shot from).
     * @param y Initial position of projectile on Y axis.
     * @param direction The direction of projectile in radians.
     */
    public MainProjectile(int x, int y, double direction) {
        super(x, y, direction);
        range = 150;
        damage = 20;
        velocity = 3;
        sprite = Sprite.projectileMain;

        xNew = velocity * Math.cos(alpha);
        yNew = velocity * Math.sin(alpha);
    }

    @Override
    public void update() {
        if (level.tileCollision(x, y, xNew, yNew, 10, 10, 6, 2)){
//            Particle p = new Particle((int)x, (int)y, 50, 50);
//            level.addEntity(p);
            level.addEntity(new ParticleEmitter((int)x, (int)y, 50, 50, level));
            remove();
        }
        move();
    }

    /**
     * Method for each moving of projectile.
     */
    protected void move() {
        // If x and y were integers, it would cast xNew and yNew to integers as well.
        x += xNew;
        y += yNew;
        if (distance() > range) {
            this.remove();
        }
    }

    /**
     * Method that calculates distance the projectile has travelled.
     * @return Distance travelled.
     */
    private double distance() {
        // This is just pythagorean theorem to calculate the distance the projectile has traveled.
        distance = Math.sqrt(Math.pow(xInitial - x, 2) + Math.pow(yInitial - y, 2));
        return distance;
    }

    /**
     * Render method for main projectile.
     * Calls the renderProjectile method on our display.
     * @param display Main display object.
     */
    public void render(Display display) {
        // Casting back to integer right b4 rendering.
        // Subtracting to readjust the projectile's initial position to exactly centre.
        display.renderProjectile((int)x - 8, (int)y - 4, this);
    }
}
