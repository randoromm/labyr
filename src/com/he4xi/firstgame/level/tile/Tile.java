package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.level.tile.mainLevel.*;

/**
 * For tiles from texture sheet.
 *
 * Created on 29.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */

/*
* We want to have a sprite for each actual tile to diagnose possible errors in future
* Tiles must be rendered all the time and shown on screen
*
* Tile class is abstract. It is just a template for tiles, it's actually never ran.
* Render stuff is done in specific tile class (ex. GrassTile.java)
*/
public class Tile {

    public Sprite sprite;

    // Static - so i can invoke with Tile.
    public static Tile nullTile = new NullTile(Sprite.nullSprite);

    // Main Level tiles:
    public static Tile mainGrass = new Tile(Sprite.mainGrass) {
        public boolean solid () {
            return true;
        }
    };
    public static Tile mainGrassHigh = new MainGrassHighT(Sprite.mainGrassHigh);
    public static Tile mainFlowerPurple = new MainFlowerT(Sprite.mainFlowerPurple);
    public static Tile mainFlowerYellow = new MainFlowerT(Sprite.mainFlowerYellow);
    public static Tile mainRock = new MainRockT(Sprite.mainRock);
    public static Tile mainBush = new MainBushT(Sprite.mainBush);
    public static Tile mainWall = new MainWallT(Sprite.mainWall);
    public static Tile mainPlate1 = new MainPlateT(Sprite.mainPlate1);
    public static Tile mainPlate2 = new MainPlateT(Sprite.mainPlate2);
    public static Tile mainPlate3 = new MainPlateT(Sprite.mainPlate3);


    /**
     * Template constructor to create a tile for chosen sprite.
     * @param sprite Chosen sprite object.
     */
    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Template (polymorphic) method to render a specific tile.
     * @param x Tile index position on X-Axis in tile precision (Will be converted in this method to pixel precision).
     * @param y Tile index position on Y-Axis in tile precision (Will be converted in this method to pixel precision).
     * @param display Main display/screen object. (To use in extending classes when overwriting this method).
     */
    public void render(int x, int y, Display display) {
        // ALWAYS REMEMBER TO SHIFT BACK TO PIXEL PRECISION BEFORE RENDERING!
        display.renderTile(x << 4, y << 4, this); // (<< 4) = ( * 2^4)
    }

    /**
     * Template method to specify if the tile is solid or not.
     * (Can be walked through or NOT).
     * @return Boolean True if object is solid, False if object is NOT solid.
     */
    public boolean solid() { return false; }
}
