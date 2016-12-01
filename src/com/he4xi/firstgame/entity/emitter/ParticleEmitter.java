package com.he4xi.firstgame.entity.emitter;

import com.he4xi.firstgame.entity.particles.Particle;
import com.he4xi.firstgame.level.Level;

/**
 * Class to handle spawning particles.
 * Created by Rando on 1.12.2016.
 */
public class ParticleEmitter extends Emitter{
    public ParticleEmitter(int x, int y, int amount, int duration, Level level) {
        super(x, y, 50, Type.PARTICLE, level);
        for (int i = 0; i < amount; i++) {
            level.addEntity(new Particle(x, y, duration));
        }
    }
}
