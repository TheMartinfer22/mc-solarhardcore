package dev.nanosync.solarhardcore.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;

public class ExplosionUtil {
    public static void explodeBlock(ServerWorld world, BlockPos pos, Integer size) {
        world.explode(
                null,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                size,
                Explosion.Mode.BREAK);
    }
}
