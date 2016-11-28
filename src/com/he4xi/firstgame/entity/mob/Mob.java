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

    /**
     * Template move method for mobs. Sets direction variables (0 - N, 1 - E, 2 - S, 3 - W).
     * It adds or removes specific amount of pixels to/from mob position each time an update occurs.
     * @param xAxis Direction of movement on X-Axis (ex. -1 is left(W), +1 is right(E))
     * @param yAxis Direction of Movement on Y-Axis (ex. -1 is Up(N), +1 is Down(S))
     */
    public void move(int xAxis, int yAxis) {
        if (xAxis > 0) direction = 1;
        if (xAxis < 0) direction = 3;
        if (yAxis > 0) direction = 2;
        if (yAxis < 0) direction = 0;

        // Separate to enable wall sliding.
        if (!collision(xAxis, 0)) { x += xAxis; }
        if (!collision(0, yAxis)) { y += yAxis; }
    }

    /**
     * Template update method for mobs.
     * Deals with updating mob position and controlling animation speed (overwritten in specific mob class).
     */
    public void update() {}

    /**
     * Template rendering method for mobs.
     */
    public void render() {}

    /**
     * Template collision method for mobs.
     * @return True if mob is colliding, false if not.
     */
    private boolean collision(int xAxis, int yAxis) {
        boolean solid = false;

        // Check for all four corners of a tile.
        for(int corner = 0; corner < 4; corner++) {

            // Corner % 2  = (left corners)even&zero: 0 or  (right corners)odd: 1
            // Corner / 2 =  (top corners)zero&one: 0 or (bottom corners)two&three: 1
            // xc = ((pos + 1px in moving direction) + left/right corners * collision box width +/- offset) / tile size
            // Dividing by 16 to get into tile precision.
            System.out.println(1 / 2);
            int xc = ((x + xAxis) + corner % 2 * 13 - 7) / 16;
            int yc = ((y + yAxis) + corner / 2 * 12 + 3) / 16;

            if (level.getTile(xc, yc).solid()) solid = true;
        }

        return solid;
    }
}
