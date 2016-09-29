package com.he4xi.firstgame.level;

import java.util.Random;

/**
 * Class for random level
 *
 * Created on 29.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class RandomLevel extends Level {
    private static Random rndm = new Random();
    /*
    * Since we extend Level class but level class doesn't have default constructor,
    * we have to create a super constructor here, to choose the constructor we want
     */

    public RandomLevel(int width, int height) {
        super(width, height); // Super refers to the extending constructor in Level class, executes all code there
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = rndm.nextInt(4);
            }
        }
    }
}
