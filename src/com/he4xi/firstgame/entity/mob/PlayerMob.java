package com.he4xi.firstgame.entity.mob;

import com.he4xi.firstgame.Game;
import com.he4xi.firstgame.entity.projectiles.Projectile;
import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;
import com.he4xi.firstgame.input.KeyInput;
import com.he4xi.firstgame.input.Mouse;

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

    private int updatesBetweenShots; // TODO better solution

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
        updatesBetweenShots = Projectile.UBS; // TODO better solution
    }

    /**
     * Update method for Player mob.
     * Deals with updating player position and controlling animation speed.
     */
    public void update() {
        if(updatesBetweenShots > 0) updatesBetweenShots--;
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

        updateFireing();
        removeProjectile();
    }

    /**
     * Update method for projectiles.
     * Updates projectiles if they are fired.
     */
    public void updateFireing() {
        if (Mouse.getMouseButton() == 1 && updatesBetweenShots <= 0) {
            // DeltaX = Change of distance on X axis. Window Width / 2 is centre of our window.
            double deltaX = Mouse.getMouseX() - Game.getFrameWidth() / 2;
            double deltaY = Mouse.getMouseY() - Game.getFrameHeight() / 2;

            // Math.atan2(dy, dx) == Math.atan(dy/dx), except that it handles division by 0. (If dx is 0)
            double direction = Math.atan2(deltaY, deltaX);

            fireProjectile(x, y, direction);
            updatesBetweenShots = Projectile.UBS; // TODO better solution
        }

    }

    /**
     * Rendering method for Player mob.
     * Deals with rendering and animating the player sprites according to direction and animation speed.
     * @param display object of main Display/Screen, to use the methods in there.
     */
    public void render(Display display) {
        if (direction == 0) {
            playerAnimation(Sprite.playerNorth, Sprite.playerNorth1, Sprite.playerNorth2, display);
        }
        if (direction == 1) {
            playerAnimation(Sprite.playerEast, Sprite.playerEast1, Sprite.playerEast2, display);
        }
        if (direction == 2) {
            playerAnimation(Sprite.playerSouth, Sprite.playerSouth1, Sprite.playerSouth2, display);
        }
        if (direction == 3) {
            playerAnimation(Sprite.playerWest, Sprite.playerWest1, Sprite.playerWest2, display);
        }
    }

    /**
     * Method that deals with running and walking animations of player.
     * @param s1 Standing player sprite.
     * @param s2 1st player moving sprite.
     * @param s3 2nd player moving sprite.
     * @param display object of main Display/Screen.
     */
    private void playerAnimation(Sprite s1, Sprite s2, Sprite s3, Display display) {
        if (running) {
            if (animation % 40 > 30) {
                // Subtracting 16 because the sprite is size 32.
                // Subtracting 16 makes the first pixel of the last sprite (of total 4) exactly at (0, 0)
                // Therefore in the middle
                display.renderPlayer(x - 16, y - 16, s2);
            }
            else if (animation % 40 > 20) {
                display.renderPlayer(x - 16, y - 16, s1);
            }
            else if (animation % 40 > 10) {
                display.renderPlayer(x - 16, y - 16, s3);
            } else {
                display.renderPlayer(x - 16, y - 16, s1);
            }
        } else {
            display.renderPlayer(x - 16, y - 16, s1);
        }
    }
}
