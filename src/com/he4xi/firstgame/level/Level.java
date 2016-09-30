package com.he4xi.firstgame.level;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.level.tile.Tile;

/**
 * The main class for level control
 * Created on 28.09.2016.
 *
 * @author Rando Rommot
 * @version 0.1
 */

/*
* Level class holds an list of tiles, and it organizes which tiles must be rendered
* Tiles render themselves, level class doesn't call it out. Specific tile class (ex. GrassTile.java)
* calls the render method for tiles and the method itself is in Display class.
*/
public class Level {

    protected int width, height;
    protected int[] tiles; // Tile ID's (which index for which Tile)

    public Level(int width, int height) { // Load/generate a random level (1st constructor)
        this.width = width; // Level width in tiles
        this.height = height; // Level height in tiles
        tiles = new int[width * height]; // Array for tiles ( it holds ID's of tiles (integer))
        generateLevel();
    }

    public Level(String path) { // Load/generate level from file (2nd constructor)
        loadLevel(path);
    }

    protected void generateLevel() { // Protected instead of private to overwrite methods etc...
        // TODO
    }

    private void loadLevel(String path) {
        // TODO
    }

    private void time() {
        // TODO
    }

    public void update() { // Updates our level (for bots, AI, enemies, entities that move etc..)
        // TODO
    }

    public void render(int xScroll, int yScroll, Display display) {
        display.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // ( xSc / 2^4 ) jumping from pixel precision to tile precision
        int x1 = (xScroll + display.width + 16) >> 4; // rightmost display side (vertical tiles)
        int y0 = yScroll >> 4; // top side of display (horizontal tiles)
        int y1 = (yScroll + display.height + 16) >> 4; // bottom. Adding 16 to avoid black borders

        for (int y = y0; y < y1; y++) { // make sure that only tiles that are visible, get rendered
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, display); // render tile at x, y pos

            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.nullTile;
        if (tiles[x + y * width] == 0) return Tile.grass;
        return Tile.nullTile;
    }
}
