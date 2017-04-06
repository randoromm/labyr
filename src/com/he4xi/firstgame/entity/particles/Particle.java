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
    private int counter = 0;
    private Sprite sprite;
    protected double xNew, yNew, zNew;
    protected double xDouble, yDouble, zDouble;

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
        this.duration = duration + (random.nextInt(20) - 10);
        sprite = Sprite.particleMain;

        xNew = random.nextGaussian() + 1.8; // Normal distribution. Gives value about -1 to 1, but more likely to be around 0.
        if (xNew < 0) xNew = 0.1;
        yNew = random.nextGaussian();
        zDouble = random.nextFloat() + 2.0;
    }

    @Override
    public void update() {
        counter++;
        if (counter > 9999) counter = 0;
        if (counter >= duration) remove();
        zNew -= 0.1;

        if (zDouble < 0) {
            zDouble = 0;
            zNew *= -0.6;
            xNew *= 0.4;
            yNew *= 0.4;
        }
        xDouble += xNew;
        yDouble += yNew;
        zDouble += zNew;
    }

    @Override
    public void render(Display display) {
        display.renderSprite((int)xDouble -8, (int)yDouble - (int)zDouble, true, sprite);
    }
}
