package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Void or Null tile.
 *
 * Created on 30.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class NullTile extends Tile {

    public NullTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Display display) {
        // ALWAYS REMEMBER TO SHIFT BACK TO PIXEL PRECISION BEFORE RENDERING!
        display.renderTile(x << 4, y << 4, this); // (<< 4) = ( * 2^4)
    }

    @Override
    public boolean solid() {
        return true;
    }
}
