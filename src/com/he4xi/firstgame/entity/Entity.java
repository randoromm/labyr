package com.he4xi.firstgame.entity;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.level.Level;

import java.util.Random;

/**
 * Main class for entities
 *
 * Created on 30.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public abstract class Entity {
    /*
    * Entity is something that might, or might not have a sprite but definitely needs to get rendered.
    * ...
    * Abstract - it's like a template for specific Entities
    */

    public int x, y;
    private boolean removed = false;
    protected Level level;  // Protected instead of private, available only in this class and  its subclasses.
    protected final Random random = new Random();

    public void update() {

    }

    // Entities can move, so we must have a separate coordinates and render/update method to control that.
    public void render(Display display) {

    }

    public void remove() {
        // Remove from level
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
