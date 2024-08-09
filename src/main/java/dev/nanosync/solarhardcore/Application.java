package dev.nanosync.solarhardcore;

import dev.nanosync.solarhardcore.register.ItemsRegister;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Application.MOD_ID)
public class Application {

    public static final String MOD_ID = "solarhardcore";

    public Application() {
        MinecraftForge.EVENT_BUS.register(this);
        ItemsRegister.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
