package com.he4xi.firstgame.graphics;

/**
 * This is class for individual tiles
 *
 * Created on 28.09.2016.
 * @author Rando Rommot
 * @
 */
public class Sprite {

    public final int T_SIZE; // Size of a tile
    private int x, y; // Starting coordinate x and y of tile (pixels)
    public int[] pixels; // Array for each pixel of the tile
    private TileSheet sheet; // Holds the chosen (with Tile object) tile sheet

    public static Sprite grass = new Sprite(16, 0, 0, TileSheet.sprites); // creates a tile and stores it in an object
    public static Sprite rLetter = new Sprite(16, 1, 0, TileSheet.sprites);
    public static Sprite nullSprite = new Sprite(16, 0xfb0000);

    public Sprite(int size, int x, int y, TileSheet sheet) {
        T_SIZE = size; // Sets tile size to selected tile size
        pixels = new int[T_SIZE * T_SIZE]; // Makes an array for all the pixels in the tile
        this.x = x * size; // Starting coordinate x of tile (pixels)
        this.y = y * size; // Starting coordinate y of tile (pixels)
        this.sheet = sheet; // Sets object sheet of this class equal to chosen tile sheet
        load(); // Loads the tile from tile sheet and stores it's pixels in this.pixels
    }

    public Sprite(int size, int colour) {
        T_SIZE = size;
        pixels = new int[T_SIZE * T_SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < T_SIZE * T_SIZE; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int y = 0; y < T_SIZE; y++) {
            for (int x = 0; x < T_SIZE; x++) {
                // Sets the tile array equal to the correct tile from tile sheet array
                // (y * T_SIZE) - row
                // x - column
                pixels[x + y * T_SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.TS_SIZE];
            }
        }
    }
}
