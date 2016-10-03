package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Class for purple flower (in grass) tile.
 *
 * Created on 1.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class FlowerPurpleTile extends Tile {

    public FlowerPurpleTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Display display) {
        // ALWAYS REMEMBER TO SHIFT BACK TO PIXEL PRECISION BEFORE RENDERING!
        display.renderTile(x << 4, y << 4, this); // (<< 4) = ( * 2^4)
    }
}
