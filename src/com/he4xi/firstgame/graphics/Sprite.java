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

    public static Sprite grass = new Sprite(16, 0, 0, TileSheet.tiles); // creates a tile and stores it in an object
    public static Sprite rLetter = new Sprite(16, 1, 0, TileSheet.tiles);
    public static Sprite nullSprite = new Sprite(16, 0xfb0000);

    public static Sprite playerNorth = new Sprite(32, 0, 5, TileSheet.tiles);
    public static Sprite playerNorth1 = new Sprite(32, 0, 6, TileSheet.tiles);
    public static Sprite playerNorth2 = new Sprite(32, 0, 7, TileSheet.tiles);
    public static Sprite playerEast = new Sprite(32, 1, 5, TileSheet.tiles);
    public static Sprite playerEast1 = new Sprite(32, 1, 6, TileSheet.tiles);
    public static Sprite playerEast2 = new Sprite(32, 1, 7, TileSheet.tiles);
    public static Sprite playerSouth = new Sprite(32, 2, 5, TileSheet.tiles);
    public static Sprite playerSouth1 = new Sprite(32, 2, 6, TileSheet.tiles);
    public static Sprite playerSouth2 = new Sprite(32, 2, 7, TileSheet.tiles);
    public static Sprite playerWest = new Sprite(32, 3, 5, TileSheet.tiles);
    public static Sprite playerWest1 = new Sprite(32, 3, 6, TileSheet.tiles);
    public static Sprite playerWest2 = new Sprite(32, 3, 7, TileSheet.tiles);

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
