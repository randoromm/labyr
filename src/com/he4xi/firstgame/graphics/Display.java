package com.he4xi.firstgame.graphics;

import java.util.Random;

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
    public final int TILE_MAP_SIZE = 64; // Tile
    public final int TILE_MASK = TILE_MAP_SIZE - 1;
    public int[] tiles = new int[TILE_MAP_SIZE * TILE_MAP_SIZE]; // size * size Tiles

    private Random rndm = new Random();

    public Display(int width, int height) { // Constructor
        this.width = width;
        this.height = height;

        pixels = new int[width * height]; // about 50,400

        for (int i = 0; i < TILE_MAP_SIZE * TILE_MAP_SIZE; i++) {
            tiles[i] = rndm.nextInt(0xffffff);
        }
        tiles[0] = 0x000000;
    }

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
//            if (yy < 0 || yy >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
//                if (xx < 0 || xx >= width) break;
                /*
                * Since we don't have 2 dimensional array, we need to make our own coordinate system
                * basically the indexes of pixel[] go from 0 to 48599
                * if one row gets displayed, the next row starts with index
                * that is one higher from previous rows last index.
                * (y * width) indicates the row of grid
                * x indicates the column of grid
                *
                * tileIndex creates indexes for our pixel array for every tile
                * (x >> 4) - x shifted right by 4
                * (x << 4) - x shifted left by 4
                * (x >> 4) = (x / (2*4)), bitwise operations are just faster
                * (x << 4) = (x * (2*4))
                */
                int tileIndex = ((xx >> 4) & TILE_MASK) + ((yy >> 4) & TILE_MASK) * TILE_MAP_SIZE;
                pixels[x + y * width] = tiles[tileIndex];

            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }
}
