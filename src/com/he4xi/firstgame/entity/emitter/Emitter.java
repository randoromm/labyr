package com.he4xi.firstgame.entity.emitter;

import com.he4xi.firstgame.entity.Entity;
import com.he4xi.firstgame.entity.particles.Particle;
import com.he4xi.firstgame.level.Level;

/**
 * Class to handle spawning mobs and particles.
 * Created by Rando on 1.12.2016.
 */
public class Emitter extends Entity {

    public enum Type {
        MOB, PARTICLE
    }

    private Type type;

    public Emitter(int x, int y, int amount, Type type, Level level) {
        initLevel(level);
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
