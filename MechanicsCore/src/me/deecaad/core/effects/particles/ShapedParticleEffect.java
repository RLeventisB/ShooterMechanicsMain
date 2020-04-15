package me.deecaad.core.effects.particles;

import me.deecaad.core.effects.data.EffectData;
import me.deecaad.core.effects.shapes.Shape;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShapedParticleEffect extends ParticleEffect {

    private Shape shape;
    private int interval;

    /**
     * Empty constructor for serializers
     */
    public ShapedParticleEffect() {
    }

    public ShapedParticleEffect(Particle particle, int amount, double horizontal, double vertical, double speed,
                                @Nullable Object particleData, @Nonnull Shape shape, int interval) {
        super(particle, amount, horizontal, vertical, speed, particleData);

        this.shape = shape;
        this.interval = interval;
    }

    @Override
    public void spawnOnce(@Nonnull Plugin source, @Nonnull World world, double x, double y, double z, @Nullable EffectData data) {
        int counter = 0;
        for (Vector vector: shape) {
            Bukkit.getScheduler().runTaskLater(source, () -> {
                super.spawnOnce(source, world, x + vector.getX(), y + vector.getZ(), z + vector.getY(), data);
            }, counter++ * interval);
        }
    }
}
