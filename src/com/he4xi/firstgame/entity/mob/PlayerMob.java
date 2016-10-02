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

    public void render(Display display) {
        if (direction == 0) {
            if (running) {
                if (animation % 40 > 30) {
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
