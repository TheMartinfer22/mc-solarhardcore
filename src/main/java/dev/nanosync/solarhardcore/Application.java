package dev.nanosync.solarhardcore;

import dev.nanosync.solarhardcore.itens.SolarProtector;
import dev.nanosync.solarhardcore.register.ItemsRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Application.MOD_ID)
public class Application {

    public static final String MOD_ID = "solarhardcore";

    public Application() {
        MinecraftForge.EVENT_BUS.register(this);
        ItemsRegister.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
