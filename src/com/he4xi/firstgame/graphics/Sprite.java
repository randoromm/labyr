package com.he4xi.firstgame.graphics;

/**
 * This is class for individual sprites/tiles/textures.
 *
 * Created on 28.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class Sprite {

    /** SpriteSheet object */
    private SpriteSheet sheet; // Holds the chosen (with Sprite object) sprite sheet

    /** Starting coordinate x and y of the sprite on texture sheet (pixel precision) */
    private int x, y; // Starting coordinate x and y of sprite (pixels)

    /** Size of the sprite */
    public final int S_SIZE; // Size of a sprite

    /** Array for each pixel of the sprite */
    public int[] pixels; // Array for each pixel of the sprite

    public static Sprite nullSprite = new Sprite(16, 0xfb0000);
    public static Sprite testSprite = new Sprite(16, 0xff00ff);
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles); // creates a tile and stores it in an object
    public static Sprite grassHigh = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite flowerPurple = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite flowerYellow = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 4, 0, SpriteSheet.tiles);

    public static Sprite playerNorth = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite playerNorth1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite playerNorth2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite playerEast = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite playerEast1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite playerEast2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
    public static Sprite playerSouth = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite playerSouth1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite playerSouth2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
    public static Sprite playerWest = new Sprite(32, 3, 5, SpriteSheet.tiles);
    public static Sprite playerWest1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
    public static Sprite playerWest2 = new Sprite(32, 3, 7, SpriteSheet.tiles);

    /**
     * Constructor to create a sprite filled with single colour.
     * @param size Size of the sprite in pixels (Size * Size).
     * @param colour Chosen color of the sprite in hexadecimal RGB (ex. 0xFFFFFF).
     */
    public Sprite(int size, int colour) {
        S_SIZE = size;
        pixels = new int[S_SIZE * S_SIZE];
        setColour(colour);
    }

    /**
     * Constructor for loading sprites from a sprite sheet image file.
     * @param size Size of the sprite in pixels (size * size).
     * @param x Starting coordinate on X-Axis of the sprite on the sprite sheet file. (in pixels).
     * @param y Starting coordinate on Y-Axis of the sprite on the sprite sheet file. (in pixels).
     * @param sheet Object of SpriteSheet on which the file with sprites is (includes path and sheet size).
     */
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        S_SIZE = size; // Sets sprite size to selected sprite size
        pixels = new int[S_SIZE * S_SIZE]; // Makes an array for all the pixels in the sprite
        this.x = x * size; // Starting coordinate x of sprite (pixels)
        this.y = y * size; // Starting coordinate y of sprite (pixels)
        this.sheet = sheet; // Sets object sheet of this class equal to chosen sprite sheet
        load(); // Loads the sprite from sprite sheet and stores it's pixels in this.pixels
    }

    /**
     * Method to load and color each pixel of the chosen sprite. (using array of sprite sheet pixels).
     */
    private void load() {
        for (int y = 0; y < S_SIZE; y++) {
            for (int x = 0; x < S_SIZE; x++) {
                // Sets the tile array equal to the correct tile from tile sheet array
                // (y * T_SIZE) - row
                // x - column
                pixels[x + y * S_SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SS_SIZE];
            }
        }
    }

    /**
     * Method to colour all pixels of the sprite (in the pixel array of the sprite).
     * @param colour Chosen color of the sprite in hexadecimal RGB (ex. 0xFFFFFF).
     */
    private void setColour(int colour) {
        for (int i = 0; i < S_SIZE * S_SIZE; i++) {
            pixels[i] = colour;
        }
    }
}
