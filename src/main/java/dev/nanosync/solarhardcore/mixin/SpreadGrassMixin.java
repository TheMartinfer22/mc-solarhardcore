package dev.nanosync.solarhardcore.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SpreadableSnowyDirtBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.nanosync.solarhardcore.util.TimeUtil.isTimeToSolarApocalypse;

@Mixin(value = SpreadableSnowyDirtBlock.class, priority = 1001)
public abstract class SpreadGrassMixin {
    @Inject(method = "canBeGrass", at = @At("RETURN"), cancellable = true)
    private static void canBeGrass(BlockState state, IWorldReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockPos = pos.above();
        World world = (World) worldReader;
        if (world.isRaining() || !world.canSeeSky(blockPos)) return;
        if (isTimeToSolarApocalypse(world)) {
            cir.setReturnValue(false);
        }
    }
}
