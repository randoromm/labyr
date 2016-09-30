package com.he4xi.firstgame.entity.mob;

import com.he4xi.firstgame.entity.Entity;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Blueprint/template for mobs
 *
 * Created on 30.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public abstract class Mob extends Entity {

    protected Sprite sprite; // can only be used within Mob class and in its subclasses (protected) (img in dev screens)
    protected int direction = 0;
    protected boolean moving = false;

    public void move(int xAxis, int yAxis) {
        if (xAxis > 0) direction = 1;
        if (xAxis < 0) direction = 3;
        if (yAxis > 0) direction = 2;
        if (yAxis < 0) direction = 4;

        if (!collision()) {
            x += xAxis;
            y += yAxis;
        }
    }

    public void update() {
        // TODO
    }

    public void render() {
        // TODO
    }

    private boolean collision() {
        return false;
    }
}
