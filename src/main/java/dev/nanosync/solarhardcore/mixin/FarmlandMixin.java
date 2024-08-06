package dev.nanosync.solarhardcore.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FarmlandBlock.class)
public abstract class FarmlandMixin {
    @Inject(method = "isNearWater", at = @At("RETURN"), cancellable = true)
    private static void isNearWater(IWorldReader p_176530_0_, BlockPos p_176530_1_, CallbackInfoReturnable<Boolean> cir) {
        if (p_176530_0_.canSeeSky(p_176530_1_)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_, CallbackInfoReturnable<Boolean> cir) {
        if (p_196260_2_.canSeeSky(p_196260_3_)) {
            cir.setReturnValue(false);
        }
    }
}
