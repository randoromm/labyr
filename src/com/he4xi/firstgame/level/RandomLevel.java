package com.he4xi.firstgame.level;

import java.util.Random;

/**
 * Class for random level.
 *
 * Created on 29.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class RandomLevel extends Level {
    private static Random rndm = new Random();

    /**
     * Constructor for RandomLevel class to refer to super constructor.
     * (Because we don't have a default constructor in Level class (that RandomLevel extends)).
     * It creates an array for available tiles (indexes) and to generate a random level.
     * @param width Chosen width for the level to  be generated (in tiles).
     * @param height Chosen height for the level to  be generated (in tiles).
     */
    public RandomLevel(int width, int height) {
        super(width, height); // Super refers to the extending constructor in Level class, executes all code there
    }

    /**
     * Method to generate a random level of chosen size using random tiles.
     */
    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = rndm.nextInt(5);
            }
        }
    }
}
