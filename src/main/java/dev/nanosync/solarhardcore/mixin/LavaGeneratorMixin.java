package dev.nanosync.solarhardcore.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

@Mixin(AbstractBlock.class)
public abstract class LavaGeneratorMixin {

    @Mutable @Shadow @Final protected boolean isRandomlyTicking;

    @Shadow protected abstract Block asBlock();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(AbstractBlock.Properties properties, CallbackInfo ci) {
        if (asBlock().is(Blocks.COBBLESTONE)) {
            isRandomlyTicking = true;
        }
    }


    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random p_225542_4_, CallbackInfo ci) {
        if (asBlock().is(Blocks.COBBLESTONE) && isTimeToSolarApocalypse(world) && world.canSeeSky(pos)) {
            world.setBlock(pos, Blocks.LAVA.defaultBlockState(), 2);
        }
    }
}
