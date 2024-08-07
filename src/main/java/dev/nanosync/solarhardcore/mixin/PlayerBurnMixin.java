package dev.nanosync.solarhardcore.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.nanosync.solarhardcore.util.TimeUtil.isTimeToSolarApocalypse;

@Mixin(PlayerEntity.class)
public abstract class PlayerBurnMixin extends LivingEntity {

    @Shadow public abstract boolean isCreative();

    protected PlayerBurnMixin(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    public void burnPlayerOnSe(CallbackInfo ci) {
        if (isTimeToSolarApocalypse(this.level) && isAlive() && !isOnFire() && !isCreative() && !this.level.isRaining() && !this.level.isNight() && !this.level.isClientSide() && this.level.canSeeSky(new BlockPos(this.position()))) {
            if (((PlayerEntity) getEntity()).hasEffect(Effects.FIRE_RESISTANCE)) {
                return;
            }
            setSecondsOnFire(3);
            addEffect(new EffectInstance(Effects.WEAKNESS, 300));
        }
    }
}
