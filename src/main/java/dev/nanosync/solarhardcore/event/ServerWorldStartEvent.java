package dev.nanosync.solarhardcore.event;


import dev.nanosync.solarhardcore.Application;
import dev.nanosync.solarhardcore.util.ServerUtil;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Application.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerWorldStartEvent {
    @SubscribeEvent
    public static void serverStartEvent(FMLServerStartingEvent event) {
        ServerUtil.MINECRAFT_SERVER = event.getServer();
    }
}
