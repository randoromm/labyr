package com.he4xi.firstgame.level.tile;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

/**
 * For tiles from texture sheet
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

    public int x, y; // coordinates
    public Sprite sprite;

    public static Tile nullTile = new NullTile(Sprite.nullSprite);
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile grassHigh = new GrassHighTile(Sprite.grassHigh);
    public static Tile flowerPurple = new FlowerPurpleTile(Sprite.flowerPurple);
    public static Tile flowerYellow = new FlowerYellowTile(Sprite.flowerYellow);
    public static Tile rock = new RockTile(Sprite.rock);



    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Display display) {
        // Tile class is just a template or blueprint for specific tiles, nothing here :)
    }

    public boolean solid() {
        return false;
    }
}
