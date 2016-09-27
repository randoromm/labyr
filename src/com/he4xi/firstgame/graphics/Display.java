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
    int xtime = 100, ytime = 100;
    int counter = 0;
    public void render() {
        counter++;
        if (counter % 30 == 0) xtime--;
        if (counter % 20 == 0) ytime--;
        for (int y = 0; y < height; y++) {
            if (ytime < 0 || ytime >= height) break;
            for (int x = 0; x < width; x++) {
                if (xtime < 0 || xtime >= width) break;

                /* Since we don't have 2 dimensional array, we need to make our own coordinate system.
                * basically the indexes of pixel[] go from 0 to 48599
                * if one row gets displayed, the next row starts with index
                * that is one higher from previous rows last index.
                * (y * width) indicates the row of grid
                * x indicates the column of grid
                */
                pixels[xtime + ytime * width] = 0xff00ff;

            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }
}
