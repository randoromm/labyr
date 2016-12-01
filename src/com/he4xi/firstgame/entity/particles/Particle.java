package com.he4xi.firstgame.entity.particles;

import com.he4xi.firstgame.entity.Entity;
import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.graphics.Sprite;

import java.util.ArrayList;

/**
 * Blueprint for particles.
 *
 * Created on 30.11.2016.
 * @author Rando Rommot
 * @version 0.1
 */
public class Particle extends Entity{

    private int duration; // Duration of particles in ticks (How many updates).
    private Sprite sprite;
    protected double xNew, yNew, xDouble, yDouble;

    /**
     * Constructor to create X amount of particles and add them to a particles list.
     * @param x Initial position of particles on x Axis.
     * @param y Initial position of particles on x Axis.
     */
    public Particle(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.xDouble = x; // We need doubles for accurate physics and coordinates.
        this.yDouble = y;
        this.duration = duration;
        sprite = Sprite.particleMain;

        xNew = random.nextGaussian(); // Normal distribution. Gives value about -1 to 1, but more likely to be around 0.
        yNew = random.nextGaussian();
    }

    @Override
    public void update() {
        xDouble += xNew;
        yDouble += yNew;
    }

    @Override
    public void render(Display display) {
        display.renderSprite((int)xDouble, (int)yDouble, true, sprite);
    }
}
