package com.he4xi.firstgame.entity.projectiles;

import com.he4xi.firstgame.entity.Entity;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Blueprint for projectiles
 *
 * Created on 29.11.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public abstract class Projectile extends Entity {
    protected final int xInitial, yInitial;
    protected double xNew, yNew; // New x and y position after 1 update.
    protected double x, y; // Overwrites entity x and y coordinate in Entity class.
    protected double alpha; // Angle/Direction of projectile in radians.
    protected Sprite sprite; // Sprite of projectile.
    protected double distance; // Distance, the projectile has travelled.
    protected double damage, velocity, fireRate, range;

    /**
     * Constructor for projectiles.
     * Gives values to variables.
     * @param x Initial position of projectile on X axis (where it's shot from).
     * @param y Initial position of projectile on Y axis.
     * @param direction The direction of projectile in radians.
     */
    public Projectile(int x, int y, double direction) {
        xInitial = x;
        yInitial = y;
        this.x = x;
        this.y = y;
        alpha = direction;
    }

    /**
     * Template method for moving projectiles.
     */
    protected void move() {

    }

    /**
     * Template update method for projectiles.
     */
    public void update() {
        move();
    }

    /**
     * Getter for the sprite of projectile.
     * @return Sprite of the projectile.
     */
    public Sprite getSprite() {
        return sprite;
    }
}
