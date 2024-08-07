package dev.nanosync.solarhardcore.register;

import dev.nanosync.solarhardcore.Application;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Application.MOD_ID);

//    public static final RegistryObject<Item> SOLAR_PROTECTOR = ITEMS.register("solar_protector",
//            () -> new SolarProtector(new Item.Properties()
//                    .tab(ItemGroup.TAB_MISC)));
}
