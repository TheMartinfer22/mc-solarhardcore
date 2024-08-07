package dev.nanosync.solarhardcore.itens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SolarProtector extends Item {

    public SolarProtector(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }

        player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 10));
        return ActionResult.sidedSuccess(itemStack, world.isClientSide());
    }
}
