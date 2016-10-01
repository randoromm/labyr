package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * Class for high grass tile
 *
 * Created on 1.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class GrassHighTile extends Tile {

    public GrassHighTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Display display) {
        // ALWAYS REMEMBER TO SHIFT BACK TO PIXEL PRECISION BEFORE RENDERING!
        // In Level's render method i converted from pixel precision to tile precision.
        // Forgot to convert it back and there goes 5 hours of my life to searching for a bug
        display.renderTile(x << 4, y << 4, this); // (<< 4) = ( * 2^4)
    }
}
