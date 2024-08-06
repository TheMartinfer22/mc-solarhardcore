package dev.nanosync.solarhardcore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("solarhardcore")
public class Application {
    public Application() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
