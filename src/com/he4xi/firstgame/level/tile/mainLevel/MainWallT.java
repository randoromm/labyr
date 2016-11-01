package com.he4xi.firstgame.level.tile.mainLevel;

import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.level.tile.Tile;

/**
 * Class for main level wall tile.
 *
 * Created on 31.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class MainWallT extends Tile {
    public MainWallT(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
