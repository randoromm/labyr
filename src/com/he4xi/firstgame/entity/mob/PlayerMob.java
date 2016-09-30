package com.he4xi.firstgame.entity.mob;

import com.he4xi.firstgame.input.KeyInput;

/**
 * Class for the player mob
 *
 * Created on 30.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class PlayerMob extends Mob {

    private KeyInput input;

    public PlayerMob(KeyInput input) {  // Default constructor
        this.input = input;
    }

    public PlayerMob(int x, int y, KeyInput input) {  // Constructor with player location
        this.input = input;
        this.x = x;  // x pos in entity class
        this.y = y;  // y pos in entity class
    }

    public void update() {
        int xAxis = 0, yAxis = 0;
        if (input.up) yAxis--;
        if (input.down) yAxis++;
        if (input.right) xAxis++;
        if (input.left) xAxis--;

        if (xAxis != 0 || yAxis != 0) move(xAxis, yAxis);
    }

    public void render() {

    }
}
