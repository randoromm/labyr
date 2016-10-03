package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Class for rock (in grass) tile.
 *
 * Created on 1.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class RockTile extends Tile {

    public RockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Display display) {
        // ALWAYS REMEMBER TO SHIFT BACK TO PIXEL PRECISION BEFORE RENDERING!
        display.renderTile(x << 4, y << 4, this); // (<< 4) = ( * 2^4)
    }

    public boolean solid() {
        return true;
    }
}
