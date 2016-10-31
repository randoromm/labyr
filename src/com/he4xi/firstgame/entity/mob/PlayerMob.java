package com.he4xi.firstgame.entity.mob;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.input.KeyInput;

/**
 * Class for the player mob.
 *
 * Created on 30.09.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class PlayerMob extends Mob {

    private KeyInput input;
    private int animation = 0;
    private boolean running = false;

    /**
     * Constructor for PlayerMob.
     * This is used if player location is default.
     * (The last pixel of first sprite is exactly at (0, 0))
     * @param input Object of Key input/keyboard class, to use its boolean variables.
     */
    public PlayerMob(KeyInput input) {  // Default constructor
        this.input = input;
    }

    /**
     * Constructor for PlayerMob (if location is not default).
     * @param x Player spawn position on X-Axis (relative to centre (0, 0))(pixel precision).
     * @param y Player spawn position on Y-Axis (relative to centre (0, 0))(pixel precision).
     * @param input The keyInput listener object.
     */
    public PlayerMob(int x, int y, KeyInput input) {  // Constructor with player location
        this.input = input;
        this.x = x;  // x pos in entity class
        this.y = y;  // y pos in entity class
    }

    /**
     * Update method for Player mob.
     * Deals with updating player position and controlling animation speed.
     */
    public void update() {
        int xAxis = 0, yAxis = 0;
        if (animation < 666) animation++;  // What if someone runs the game overnight? :P
        else animation = 0;
        if (input.up) yAxis--;
        if (input.down) yAxis++;
        if (input.right) xAxis++;
        if (input.left) xAxis--;

        if (xAxis != 0 || yAxis != 0) {
            move(xAxis, yAxis);
            running = true;
        } else {
            running = false;
        }
    }

    /**
     * Rendering method for Player mob.
     * Deals with rendering and animating the player sprites according to direction and animation speed.
     * @param display object of main Display/Screen, to use the methods in there.
     */
    public void render(Display display) {
        if (direction == 0) {
            if (running) {
                if (animation % 40 > 30) {
                    // Subtracting 16 because the sprite is size 32.
                    // Subtracting 16 makes the first pixel of the last sprite (of total 4) exactly at (0, 0)
                    // Therefore in the middle
                    display.renderPlayer(x - 16, y - 16, Sprite.playerNorth1);
                }
                else if (animation % 40 > 20) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerNorth);
                }
                else if (animation % 40 > 10) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerNorth2);
                } else {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerNorth);
                }
            } else {
                display.renderPlayer(x - 16, y - 16, Sprite.playerNorth);
            }
        }
        if (direction == 1) {
            if (running) {
                if (animation % 40 > 30) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerEast1);
                }
                else if (animation % 40 > 20) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerEast);
                }
                else if (animation % 40 > 10) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerEast2);
                } else {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerEast);
                }
            } else {
                display.renderPlayer(x - 16, y - 16, Sprite.playerEast);
            }
        }
        if (direction == 2) {
            if (running) {
                if (animation % 40 > 30) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerSouth1);
                }
                else if (animation % 40 > 20) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerSouth);
                }
                else if (animation % 40 > 10) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerSouth2);
                } else {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerSouth);
                }
            } else {
                display.renderPlayer(x - 16, y - 16, Sprite.playerSouth);
            }
        }
        if (direction == 3) {
            if (running) {
                if (animation % 40 > 30) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerWest1);
                }
                else if (animation % 40 > 20) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerWest);
                }
                else if (animation % 40 > 10) {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerWest2);
                } else {
                    display.renderPlayer(x - 16, y - 16, Sprite.playerWest);
                }
            } else {
                display.renderPlayer(x - 16, y - 16, Sprite.playerWest);
            }
        }
    }
}
