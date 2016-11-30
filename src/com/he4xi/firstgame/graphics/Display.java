package com.he4xi.firstgame.graphics;

import com.he4xi.firstgame.entity.projectiles.Projectile;
import com.he4xi.firstgame.level.tile.Tile;

import java.util.Random;

/**
 * Screen/Display class.
 * Handles with rendering mostly.
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

    /**
     * Constructor for Display class.
     * It sets the width and height for this class and also creates array to store each pixel
     * to be displayed on screen (width * height)
     * @param width the width of resolution (w/o scale)
     * @param height the height of resolution (w/o scale)
     */
    public Display(int width, int height) { // Constructor
        this.width = width;
        this.height = height;

        pixels = new int[width * height]; // about 50,400

        /*for (int i = 0; i < TILE_MAP_SIZE * TILE_MAP_SIZE; i++) {
            tiles[i] = rndm.nextInt(0xffffff);
        }
        tiles[0] = 0x000000;*/
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

    public void renderSprite(int xPos, int yPos, boolean stationary, Sprite sprite) {
        if (stationary) { // If sprite is stationary (glued on map).
            xPos -= xOffset;
            yPos -= yOffset;
        }
        for (int y = 0; y < sprite.height; y++) {
            int yAbs = y + yPos;
            for (int x = 0; x < sprite.width; x++) {
                int xAbs = x + xPos;
                if(xAbs < -sprite.width || xAbs > width || yAbs < 0 || yAbs >= height) continue;
                pixels[xAbs + yAbs * width] = sprite.spritePixels[x + y * sprite.width];
            }
        }
    }

    /**
     * Method to render a single tile.
     * It replaces the pixels on the (x, y) position(on screen) with pixels of the specific sprite.
     *
     * @param xPos position on the screen in pixels on X axis (left to right, 0...16(or rather tile size)).
     * @param yPos position on the screen in pixels on Y axis (top to bottom, 0...16).
     * @param tile the tile to be rendered.
     */
    public void renderTile(int xPos, int yPos, Tile tile) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < tile.sprite.SPRITE_SIZE; y++) {
            int yAbs = y + yPos; // Absolute - relative to whole level/world, Relative - Relative to another tile ors
            for (int x = 0; x < tile.sprite.SPRITE_SIZE; x++) {
                int xAbs = x + xPos; // x + offset
                // NB! Only render the tiles we see
                if(xAbs < -tile.sprite.SPRITE_SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break;
                if (xAbs < 0) xAbs = 0; // to avoid left black border, WHY DOES IT WORK?!
                // Which pixels on the screen get rendered = pixels in the sprite
                pixels[xAbs + yAbs * width] = tile.sprite.spritePixels[x + y * tile.sprite.SPRITE_SIZE];
            }
        }
    }

    /**
     * Method to render a single sprite.
     * It replaces the pixels on the (x, y) position(on screen) with pixels of the specific sprite.
     *
     * @param xPos position on the screen in pixels on X axis (left to right, 0...16(or rather tile size)).
     * @param yPos position on the screen in pixels on Y axis (top to bottom, 0...16).
     * @param p the projectile to be rendered.
     */
    public void renderProjectile(int xPos, int yPos, Projectile p) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < p.getSprite().SPRITE_SIZE; y++) {
            int yAbs = y + yPos; // Absolute - relative to whole level/world, Relative - Relative to another tile ors
            for (int x = 0; x < p.getSprite().SPRITE_SIZE; x++) {
                int xAbs = x + xPos; // x + offset
                // NB! Only render the sprites we see
                if(xAbs < -p.getSprite().SPRITE_SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break;
                if (xAbs < 0) xAbs = 0; // to avoid left black border, WHY DOES IT WORK?!
                // Which pixels on the screen get rendered = pixels in the sprite
                int col = p.getSprite().spritePixels[x + y * p.getSprite().SPRITE_SIZE];
                // Render the pixel if it's is not transparent black (loose the black background).
                if (col != 0x00000000) pixels[xAbs + yAbs * width] = col;
            }
        }
    }

    /**
     * Method to render player. (32 X 32 sprite)
     *
     * @param xPos starting position on the screen in pixels on X axis (left to right, 0...32(or rather tile size)).
     * @param yPos starting position on the screen in pixels on Y axis (top to bottom, 0...32).
     * @param sprite the sprite to be rendered (player sprite).
     */
    public void renderPlayer(int xPos, int yPos, Sprite sprite) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < sprite.SPRITE_SIZE; y++) {
            int yAbs = y + yPos; // Absolute - relative to whole level/world, Relative - Relative to another tile ors
            for (int x = 0; x < sprite.SPRITE_SIZE; x++) {
                int xAbs = x + xPos; // x + offset
//                int xMoonWalk = 31 - x;
                // NB! Only render the tiles we see
                if(xAbs < -sprite.SPRITE_SIZE || xAbs >= width || yAbs < 0 || yAbs >= height) break;
//                if (xAbs < 0) xAbs = 0; // to avoid left black border
                // Which pixels on the screen get rendered = which pixels in the sprite get rendered.
                int col = sprite.spritePixels[x + y * sprite.SPRITE_SIZE];
                // Render the pixel if it's is not transparent black (loose the black background).
                if (col != 0x00000000) pixels[xAbs + yAbs * width] = col;
            }
        }
    }

    /**
     * Method to take in map offsets (relative to player) and create variables for Display class offsets.
     * @param xOffset Offset on X-Axis in pixels, relative to (0, 0) (player position by default).
     * @param yOffset Offset on Y-Axis in pixels, relative to (0, 0) (player position by default).
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Clears the screen/display by coloring all the pixels black.
     * (This should be applied before rendering).
     */
    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }
}
