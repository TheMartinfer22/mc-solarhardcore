package dev.nanosync.solarhardcore.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.nanosync.solarhardcore.util.TimeUtil.isTimeToSolarApocalypse;

@Mixin(value = MobEntity.class, priority = 1001)
public abstract class EntityBurnMixin extends LivingEntity {
    protected EntityBurnMixin(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void hookAiStep(CallbackInfo ci) {
        if (isTimeToSolarApocalypse(this.level) && isAlive() && !isOnFire() && !this.level.isRaining() && !this.level.isNight() && !this.level.isClientSide() && this.level.canSeeSky(new BlockPos(this.position()))) {
            setSecondsOnFire(8);
        }
    }
}
