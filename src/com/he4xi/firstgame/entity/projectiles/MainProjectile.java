package com.he4xi.firstgame.entity.projectiles;

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
    public MainProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 4;
        damage = 20;
        fireRate = 15;
        velocity = 4;
        sprite = Sprite.projectileMain;

        xNew = velocity * Math.cos(angle);
        yNew = velocity * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        // If x and y were integers, it would cast xNew and yNew to integers as well.
        x += xNew;
        y += yNew;
        if (distance() > range) {
            this.remove();
        }
    }

    private double distance() {
        // This is just pythagorean theorem to calculate the distance the projectile has traveled.
        distance = Math.sqrt(Math.pow(xInitial - x, 2) + Math.pow(yInitial - y, 2));
        return distance;
    }

    public void render(Display display) {
        // Casting back to integer right b4 rendering.
        // Subtracting to readjust the projectile's initial position to exactly centre.
        display.renderProjectile((int)x - 8, (int)y - 4, this);
    }
}
