package com.he4xi.firstgame.graphics;

/**
 * This is class for individual sprites/tiles/textures.
 *
 * Created on 28.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class Sprite {

    /** SpriteSheet object. */
    private SpriteSheet sheet; // Holds the chosen sprite sheet

    /** Starting coordinate x and y of the sprite on texture sheet (pixel precision). */
    private int x, y; // Starting coordinate x and y of sprite (pixels)

    /** Size of the sprite. */
    public final int SPRITE_SIZE; // Size of a sprite
    public int width, height;  // Make final maybe OR private and getters and setters !!!

    /** Array for each pixel of the sprite. */
    public int[] spritePixels; // Array for each pixel of the sprite

    public static Sprite nullSprite = new Sprite(16, 0xfb0000);
//    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles); // creates a tile and stores it in an object
//    public static Sprite grassHigh = new Sprite(16, 1, 0, SpriteSheet.tiles);
//    public static Sprite flowerPurple = new Sprite(16, 2, 0, SpriteSheet.tiles);
//    public static Sprite flowerYellow = new Sprite(16, 3, 0, SpriteSheet.tiles);
//    public static Sprite rock = new Sprite(16, 4, 0, SpriteSheet.tiles);

    // Main Level sprites:
    public static Sprite mainGrass = new Sprite(16, 0, 0, SpriteSheet.mainLevel); // creates a tile and stores it in an object
    public static Sprite mainGrassHigh = new Sprite(16, 1, 0, SpriteSheet.mainLevel);
    public static Sprite mainFlowerPurple = new Sprite(16, 2, 0, SpriteSheet.mainLevel);
    public static Sprite mainFlowerYellow = new Sprite(16, 3, 0, SpriteSheet.mainLevel);
    public static Sprite mainRock = new Sprite(16, 0, 1, SpriteSheet.mainLevel);
    public static Sprite mainBush = new Sprite(16, 1, 1, SpriteSheet.mainLevel);
    public static Sprite mainWall = new Sprite(16, 2, 1, SpriteSheet.mainLevel);
    public static Sprite mainPlate1 = new Sprite(16, 3, 1, SpriteSheet.mainLevel);
    public static Sprite mainPlate2 = new Sprite(16, 0, 2, SpriteSheet.mainLevel);
    public static Sprite mainPlate3 = new Sprite(16, 1, 2, SpriteSheet.mainLevel);

    // Player sprites:
    public static Sprite playerNorth = new Sprite(32, 0, 0, SpriteSheet.player); // Refactor to player up?
    public static Sprite playerNorth1 = new Sprite(32, 0, 1, SpriteSheet.player);
    public static Sprite playerNorth2 = new Sprite(32, 0, 2, SpriteSheet.player);
    public static Sprite playerEast = new Sprite(32, 1, 0, SpriteSheet.player);
    public static Sprite playerEast1 = new Sprite(32, 1, 1, SpriteSheet.player);
    public static Sprite playerEast2 = new Sprite(32, 1, 2, SpriteSheet.player);
    public static Sprite playerSouth = new Sprite(32, 2, 0, SpriteSheet.player);
    public static Sprite playerSouth1 = new Sprite(32, 2, 1, SpriteSheet.player);
    public static Sprite playerSouth2 = new Sprite(32, 2, 2, SpriteSheet.player);
    public static Sprite playerWest = new Sprite(32, 3, 0, SpriteSheet.player);
    public static Sprite playerWest1 = new Sprite(32, 3, 1, SpriteSheet.player);
    public static Sprite playerWest2 = new Sprite(32, 3, 2, SpriteSheet.player);

    // Particle sprites:
    public static Sprite particleMain = new Sprite(2, 0x303030);

    // Projectile sprites:
    public static Sprite projectileMain = new Sprite(16, 0, 0, SpriteSheet.projectiles);

    /**
     * Constructor to create a sprite filled with single colour.
     * @param size Size of the sprite in pixels (Size * Size).
     * @param colour Chosen color of the sprite in hexadecimal RGB (ex. 0xFFFFFF).
     */
    public Sprite(int size, int colour) {
        SPRITE_SIZE = size;
        this.width = size;
        this.height = size;
        spritePixels = new int[SPRITE_SIZE * SPRITE_SIZE];
        setColour(colour);
    }

    /**
     * Constructor to create a sprite filled with single colour.
     * @param width Width of sprite in pixels.
     * @param height Height of sprite in pixels.
     * @param colour Chosen color of the sprite in hexadecimal RGB (ex. 0xFFFFFF).
     */
    public Sprite(int width, int height, int colour) {
        SPRITE_SIZE = -1;
        this.width = width;
        this.height = height;
        spritePixels = new int[this.width * this.height];
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
        SPRITE_SIZE = size; // Sets sprite size to selected sprite size
        this.width = size;
        this.height = size;
        spritePixels = new int[SPRITE_SIZE * SPRITE_SIZE]; // Makes an array for all the pixels in the sprite
        this.x = x * size; // Starting coordinate x of sprite (pixels)
        this.y = y * size; // Starting coordinate y of sprite (pixels)
        this.sheet = sheet; // Sets object sheet of this class equal to chosen sprite sheet
        load(); // Loads the sprite from sprite sheet and stores it's pixels in this.pixels
    }

    /**
     * Method to load and color each pixel of the chosen sprite. (using array of sprite sheet pixels).
     */
    private void load() {
        for (int y = 0; y < SPRITE_SIZE; y++) {
            for (int x = 0; x < SPRITE_SIZE; x++) {
                // Sets the tile array equal to the correct tile from tile sheet array
                // (y * SPRITE_SIZE) - row
                // x - column
                spritePixels[x + y * SPRITE_SIZE] = sheet.sheetPixels[(x + this.x) + (y + this.y) * sheet.SHEET_SIZE];
            }
        }
    }

    /**
     * Method to colour all pixels of the sprite (in the pixel array of the sprite).
     * @param colour Chosen color of the sprite in hexadecimal RGB (ex. 0xFFFFFF).
     */
    private void setColour(int colour) {
        for (int i = 0; i < width * height; i++) {
            spritePixels[i] = colour;
        }
    }
}
