package com.he4xi.firstgame.graphics;

import com.he4xi.firstgame.level.tile.Tile;

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

    public int width, height;
    public int xOffset, yOffset;
    public final int TILE_MAP_SIZE = 64; // Tile
    public final int TILE_MASK = TILE_MAP_SIZE - 1;
    public int[] pixels;
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

    /*public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yao = y + yOffset;
            if (yao < 0 || yao >= height) continue;
            for (int x = 0; x < width; x++) {
                int xao = x + xOffset;
                if (xao < 0 || xao >= width) continue;
                *//*
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
                *//*
                pixels[xao + yao * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.T_SIZE];
            }
        }
    }*/

    public void renderTile(int xPos, int yPos, Tile tile) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < tile.sprite.T_SIZE; y++) {
            int yAbs = y + yPos; // Absolute - relative to whole level/world, Relative - Relative to another tile ors
            for (int x = 0; x < tile.sprite.T_SIZE; x++) {
                int xAbs = x + xPos; // x + offset
                if(xAbs < -tile.sprite.T_SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break; // NB! Only render the tiles we see
                if (xAbs < 0) xAbs = 0; // to avoid left black border
                // Which pixels on the screen get rendered = which pixels in the sprite get rendered
                pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.T_SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }
}
