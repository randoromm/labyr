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
        range = 200;
        damage = 20;
        fireRate = 15;
        velocity = 4;
        sprite = Sprite.nullSprite;

        xNew = velocity * Math.cos(angle);
        yNew = velocity * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        x += xNew;
        y += yNew;
    }

    public void render(Display display) {
        display.renderTile(x, y, sprite);
    }
}
