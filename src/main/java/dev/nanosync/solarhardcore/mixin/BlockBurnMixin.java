package dev.nanosync.solarhardcore.mixin;

import dev.nanosync.solarhardcore.util.ExplosionUtil;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static dev.nanosync.solarhardcore.util.TimeUtil.isTimeToSolarApocalypse;

@Mixin(value = AbstractBlock.class, priority = 1001)
public abstract class BlockBurnMixin {

    @Mutable @Final @Shadow protected boolean isRandomlyTicking;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(AbstractBlock.Properties properties, CallbackInfo ci) {
        isRandomlyTicking = true;
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos blockPos = pos.above();
        if (world.isNight() || world.isRaining() || !world.canSeeSky(blockPos)) return;

        if (isTimeToSolarApocalypse(world)) {
            Block block = state.getBlock();

            if (block.is(Blocks.TALL_GRASS) ||
                    block.is(Blocks.GRASS) ||
                    block instanceof FlowerBlock) {
                world.removeBlock(pos, false);
                return;
            }

            if (state.getBlock().is(Blocks.TNT)) {
                world.removeBlock(pos, false);
                ExplosionUtil.explodeBlock(world, pos, 10);
                return;
            }

            if (state.getMaterial().isFlammable()) {
                BlockState blockState = AbstractFireBlock.getState(world, blockPos);
                world.setBlock(blockPos, blockState, 2 | 8);
            }
        }
    }
}
