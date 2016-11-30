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
    protected double xNew, yNew;
    protected double x, y; // Overwrites entity x and y coordinate in Entity class.
    protected double angle;
    protected Sprite sprite;
    protected double distance;
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
        angle = direction;
    }

    protected void move() {

    }

    public Sprite getSprite() {
        return sprite;
    }
}
