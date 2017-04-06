package com.he4xi.firstgame.level.tile.mainLevel;

import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.level.tile.Tile;

/**
 * Class for main level rock (in grass) tile.
 *
 * Created on 1.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class MainRockT extends Tile {

    public MainRockT(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
