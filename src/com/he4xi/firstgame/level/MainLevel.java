package com.he4xi.firstgame.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class for Main Level.
 * Used to create object for main level and load the level from file.
 * Created on 30.10.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class MainLevel extends Level {

    /**
     * Constructor to create Main Level object.
     * Calling this constructor creates Main Level object
     * and loads a level from chosen file.
     *
     * @param path Path of the level file.
     */
    public MainLevel(String path) { // Load/generate level from file (2nd constructor)
        super(path);
    }

    /**
     * Method to load level file in image format.
     * On level file, each pixel represents a tile in the level.
     * Each tile has its representing color.
     * This method will load the image file and create an array of its pixels' colors.
     *
     * @param path Path to the level file.
     */
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(MainLevel.class.getResource(path));
            int w = super.width = image.getWidth();
            int h = super.height = image.getHeight();
            super.tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
    }

    protected void generateLevel() {}
}
