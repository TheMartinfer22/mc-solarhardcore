package dev.nanosync.solarhardcore.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class RainMixin {

    @Shadow public abstract boolean isDay();

    @Inject(method = "isRaining", at = @At("RETURN"), cancellable = true)
    public void rainOff(CallbackInfoReturnable<Boolean> cir) {
        if (this.isDay()) {
            cir.setReturnValue(false);
        }
    }
}
