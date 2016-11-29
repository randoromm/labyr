package com.he4xi.firstgame.entity;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.level.Level;

import java.util.Random;

/**
 * Main class for entities.
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

    public int x, y; // Entity location x and y in pixels.
    private boolean removed = false;
    protected Level level;  // Protected instead of private, available only in this class and  its subclasses.
    protected final Random random = new Random();

    /**
     * Template update method for all entities.
     * Deals with updating entity position and controlling animation speed (overwritten in specific mob/entity class).
     */
    public void update() {}

    /**
     * Template rendering method for entities.
     */
    public void render(Display display) {

    }

    /**
     * Method to remove an entity from level.
     */
    public void remove() {
        // TODO Remove from level
        removed = true;
    }

    /**
     * Method to specify if method is removed from the level or not.
     * @return Boolean True if entity is removed, False if it's not removed.
     */
    public boolean isRemoved() {
        return removed;
    }

    public void initLevel(Level level) {
        this.level = level;
    }
}
