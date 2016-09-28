package com.he4xi.firstgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class controls Keyboard inputs
 *
 * Created on 28.09.16.
 * @author Rando Rommot
 * @version 0.1
 */
public class KeyInput implements KeyListener{

    private boolean[] keys = new boolean[120]; // create a boolean for each key
    public boolean up, down, left, right;

    public void update() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];

        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                System.out.println("KEY: " + i);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
