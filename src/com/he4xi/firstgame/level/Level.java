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
* Level class can't be ABSTRACT because i want to instantiate some methods through level class directly,
* not through its subclasses.
*/
public class Level {

    protected int width, height;
    protected int[] tiles; // Tile ID's (which index for which Tile)

    /**
     * Constructor to create an array for available tiles (indexes) and to generate a random level.
     * @param width Chosen width for the level to  be generated (in tiles).
     * @param height Chosen height for the level to be generated (in tiles).
     */
    public Level(int width, int height) { // Load/generate a random level (1st constructor)
        this.width = width; // Level width in tiles
        this.height = height; // Level height in tiles
        tiles = new int[width * height]; // Array for tiles ( it holds ID's of tiles (integer))
        generateLevel();
    }

    /**
     * Constructor to load a level from a file.
     * @param path Path to the level file.
     */
    public Level(String path) { // Load/generate level from file (2nd constructor)
        loadLevel(path);
    }

    /**
     * Method to generate a level. Blueprint/template for specific level classes.
     */
    protected void generateLevel() {} // Protected instead of private to overwrite methods etc...

    /**
     * Method to load the level from the chosen level file.
     * @param path Path to the level file.
     */
    private void loadLevel(String path) {}

    private void time() {}

    public void update() {} // Updates our level (for bots, AI, enemies, entities that move etc..)

    /**
     * Default method to render a level and make sure that only visible tiles are rendered.
     * (Collect the tiles and display them in correct order and correct position).
     * @param xScroll Offset from centre of the screen (0, 0) to the left of the screen (in pixels).
     * @param yScroll Offset from centre of the screen (0, 0) to the top of the screen (in pixels).
     * @param display Object of the main display/screen class. (to use render method in specific tile class).
     */
    public void render(int xScroll, int yScroll, Display display) {
        display.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // ( xSc / 2^4 ) jumping from pixel precision to tile precision
        int x1 = (xScroll + display.width + 16) >> 4; // rightmost display side (vertical tiles)
        int y0 = yScroll >> 4; // top side of display (horizontal tiles)
        int y1 = (yScroll + display.height + 16) >> 4; // bottom. Adding 16 to avoid black borders

        for (int y = y0; y < y1; y++) { // make sure that only tiles that are visible, get rendered
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, display); // render tile at x, y pos (in tile precision)

            }
        }
    }

    /**
     * Method to give available tiles an index in array, and return them if called.
     * @param x Position of tile index on X-Axis (in tile precision)
     * @param y Position of tile index on Y-Axis (in tile precision)
     * @return The tile at the index defined by it's x and y position (in tile precision)
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.nullTile;
        if (tiles[x + y * width] == 0) return Tile.grass;
        if (tiles[x + y * width] == 1) return Tile.grassHigh;
        if (tiles[x + y * width] == 2) return Tile.flowerPurple;
        if (tiles[x + y * width] == 3) return Tile.flowerYellow;
        if (tiles[x + y * width] == 4) return Tile.rock;
        return Tile.nullTile;
    }
}
