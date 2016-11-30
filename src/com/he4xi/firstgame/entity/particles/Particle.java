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

    private ArrayList<Particle> particles = new ArrayList<>();
    private int duration;
    private Sprite sprite;

    /**
     * Constructor to create X amount of particles and add them to a particles list.
     * @param x Initial position of particles on x Axis.
     * @param y Initial position of particles on x Axis.
     * @param duration Lifetime/duration of particles.
     * @param amount Amount of particles.
     */
    public Particle(int x, int y, int duration, int amount) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        sprite = Sprite.particleMain;
        for (int i = 0; i < amount; i++) {
            particles.add(new Particle(x, y, duration)); // Create the rest of the particles with same characteristics.
        }
    }

    /**
     * Constructor to create single particle and add it to the list.
     * @param x Initial position of particle on x Axis.
     * @param y Initial position of particle on y Axis.
     * @param duration Lifetime/duration of particle.
     */
    public Particle(int x, int y, int duration) {
        this(x, y, duration, 1);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Display display) {
        super.render(display);
    }
}
