package com.he4xi.firstgame.level.tile.mainLevel;

import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.level.tile.Tile;

/**
 * Class for main level bush tile.
 *
 * Created on 31.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class MainBushT extends Tile {

    public MainBushT(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
