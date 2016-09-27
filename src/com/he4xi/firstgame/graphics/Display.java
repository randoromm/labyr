package com.he4xi.firstgame.graphics;

/**
 * Created by rando on 27.09.16.
 * This is our screen / renderer
 * It's basically what will be displayed to end user.
 */
public class Display {

    private int width, height;
    public int[] pixels;

    public Display(int width, int height) { // Constructor
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                /* Since we don't have 2 dimensional array, we need to make our own coordinate system.
                * basically the indexes of pixel[] go from 0 to 48599
                * if one row gets displayed, the next row starts with index
                * that is one higher from previous rows last index.
                * (y * width) indicates the row of grid
                * x indicates the column of grid
                */
                pixels[20 + 45 * width] = 0xff00ff;

            }
        }
    }
}
