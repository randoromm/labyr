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
