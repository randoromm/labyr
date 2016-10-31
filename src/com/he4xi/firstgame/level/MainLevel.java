package com.he4xi.firstgame.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * TODO
 * Created by Rando on 31.10.2016.
 */
public class MainLevel extends Level {

    public MainLevel(String path) { // Load/generate level from file (2nd constructor)
        super(path);
    }

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
