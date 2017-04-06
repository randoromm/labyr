package com.he4xi.firstgame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This controls the sprite/tile/texture sheet.
 *
 * Created 28.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class SpriteSheet {

    /** Path of the sprite sheet file as a String. */
    private String path; // Path of the sprite sheet file
    public final int SHEET_SIZE; // Size of  the sprite sheet in pixels
    public int[] sheetPixels;  // Array that stores pixel colors of sprite sheet image.

    // Object for sprite sheet that also sets the path and size
//    public static SpriteSheet tiles = new SpriteSheet("/texturesheet.png", 256);

    // Main level textures:
    public static SpriteSheet mainLevel = new SpriteSheet("/sprites/MainLevel.png", 64);

    // Player textures:
    public static SpriteSheet player = new SpriteSheet("/sprites/mobs/playertextures.png", 256);

    // Projectile textures:
    public static SpriteSheet projectiles = new SpriteSheet("/sprites/projectiles/mainprojectiles.png", 64);

    /**
     * Constructor to load a sprite sheet and create an array of its pixels.
     * @param path Path to the sprite sheet image file.
     * @param size Size of one side of the sprite sheet image (width in pixels).
     */
    public SpriteSheet(String path, int size) {
        this.path = path;
        SHEET_SIZE = size;
        sheetPixels = new int[SHEET_SIZE * SHEET_SIZE];
        loadSheet(); // Loads the tile sheet and creates an array of its pixels
    }

    /**
     * Method to load the sprite sheet from image file and create a pixels array where color of every pixel
     * on sprite sheet is stored. (width of sprite sheet image * height of sprite sheet image)
     */
    private void loadSheet() {
        try {
            // Buffers image from image
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, sheetPixels, 0, w); // Creates an array of the pixels in tile sheet
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
