package dev.nanosync.solarhardcore.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static dev.nanosync.solarhardcore.util.TimeUtil.isTimeToSolarApocalypse;

@Mixin(LeavesBlock.class)
public class LeavesMixin {
    @Inject(method = "isRandomlyTicking", at = @At("RETURN"), cancellable = true)
    private void onisRandomlyTicking(BlockState state, CallbackInfoReturnable<Boolean> cir) { cir.setReturnValue(true); }

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void hookRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos blockPos = pos.above();
        if (world.isNight() || world.isRaining() || !world.canSeeSky(blockPos)) return;
        if (isTimeToSolarApocalypse(world)) {
            world.removeBlock(pos, false);
        }
    }
}
