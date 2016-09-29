package com.he4xi.firstgame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This controls the spritesheet
 *
 * Created 28.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class TileSheet {

    private String path; // Path of the tile sheet file
    public final int TS_SIZE; // Size of  the tile sheet in pixels
    public int[] pixels;  // Array for pixels of tiles

    // Object for tile sheet that also sets the path and size
    public static TileSheet sprites = new TileSheet("/texturesheet.png", 256);

    // Constructor
    public TileSheet(String path, int size) {
        this.path = path;
        TS_SIZE = size;
        pixels = new int[TS_SIZE * TS_SIZE];
        loadSheet(); // Loads the tile sheet and creates an array of its pixels
    }

    private void loadSheet() {
        try {
            // Buffers image from image
            BufferedImage image = ImageIO.read(TileSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w); // Creates an array of the pixels in tile sheet
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
