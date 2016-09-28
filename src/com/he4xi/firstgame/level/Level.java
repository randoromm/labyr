package com.he4xi.firstgame.level;

import com.he4xi.firstgame.graphics.Display;

/**
 * The main class for level control
 * Created on 28.09.2016.
 *
 * @author Rando Rommot
 * @version 0.1
 */
public class Level {

    private int width, height;
    private int[] tiles; // Tile ID's (which index for which Tile)

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    private void generateLevel() {
        // TODO
    }

    private void loadLevel(String path) {
        // TODO
    }

    public void update() {
        // TODO
    }

    private void time() {
        // TODO
    }

    public void render(int xScroll, int yScroll, Display display) {
        // TODO
    }
}
