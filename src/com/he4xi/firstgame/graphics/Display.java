package com.he4xi.firstgame.graphics;

/**
 * Screen/Display class
 * Handles with rendering mostly
 *
 * Created on 27.09.16.
 * @author Rando Rommot
 * @version 0.1
 */
public class Display {

    private int width, height;
    public int[] pixels;

    public Display(int width, int height) { // Constructor
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }
    int xtime = 160, ytime = 0;
    int xtime2 = 10, ytime2 = 10;
    int counter = 0;
    public void render() {
        counter++;
        if (counter % 5 == 0) {
            ytime++;
            ytime2++;
        }

        if (counter % 10 == 0) {
            xtime--;
            xtime2--;
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                /*
                * Since we don't have 2 dimensional array, we need to make our own coordinate system
                * basically the indexes of pixel[] go from 0 to 48599
                * if one row gets displayed, the next row starts with index
                * that is one higher from previous rows last index.
                * (y * width) indicates the row of grid
                * x indicates the column of grid
                */
                pixels[x + y * width] = 0x2b0040;

            }
        }
        if ((ytime >= 0 & ytime < height) & (xtime >= 0 & xtime < width)) {
            pixels[xtime + ytime * width] = 0xff00ff;
        }
        if ((ytime2 >= 0 & ytime2 < height) & (xtime2 >= 0 & xtime2 < width)) {
            for (int j = 0; j < width; j += 5) {
                if (j < height) {
                    pixels[(xtime2 + ytime2 * width) + j] = 0xffffff;
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }
}
