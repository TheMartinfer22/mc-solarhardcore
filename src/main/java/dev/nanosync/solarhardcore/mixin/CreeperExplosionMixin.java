package dev.nanosync.solarhardcore.mixin;

import dev.nanosync.solarhardcore.util.ExplosionUtil;
import dev.nanosync.solarhardcore.util.TimeUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CreeperEntity.class)
public abstract class CreeperExplosionMixin extends MonsterEntity {
    protected CreeperExplosionMixin(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @Override
    public void setRemainingFireTicks(int p_241209_1_) {
        super.setRemainingFireTicks(p_241209_1_);
        BlockPos blockPos = new BlockPos(this.getX(), this.getY(), this.getZ());
        if (this.level.canSeeSky(blockPos) && !this.level.isClientSide() && this.level.isDay() && TimeUtil.isTimeToSolarApocalypse(this.level)) {
            ExplosionUtil.explodeBlock((ServerWorld) this.level, blockPos, 10);
        }
    }
}
