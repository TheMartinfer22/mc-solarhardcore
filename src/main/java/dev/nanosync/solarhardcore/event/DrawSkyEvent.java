package dev.nanosync.solarhardcore.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.nanosync.solarhardcore.Application;
import dev.nanosync.solarhardcore.util.ServerUtil;
import dev.nanosync.solarhardcore.util.TimeUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Application.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DrawSkyEvent {
    @SubscribeEvent
    public static void onSkyColorChange(EntityViewRenderEvent.FogColors event) {
        MinecraftServer minecraftServer = ServerUtil.MINECRAFT_SERVER;
        if (minecraftServer != null) {
            if (TimeUtil.isTimeToSolarApocalypse(minecraftServer.overworld())) {
                event.setRed(0.9f);
                event.setGreen(0.5f);
                event.setBlue(0.2f);
            }
        }
    }

    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.FogDensity event) {
        MinecraftServer minecraftServer = ServerUtil.MINECRAFT_SERVER;
        if (minecraftServer != null) {
            if (TimeUtil.isTimeToSolarApocalypse(minecraftServer.overworld())) {
                RenderSystem.enableFog();
                float fogDensity = 0.05f;
                RenderSystem.fogDensity(fogDensity);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.RenderFogEvent event) {
        MinecraftServer minecraftServer = ServerUtil.MINECRAFT_SERVER;
        if (minecraftServer != null) {
            if (TimeUtil.isTimeToSolarApocalypse(minecraftServer.overworld())) {
                RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
                float fogStart = 10.0f;
                float fogEnd = 50.0f;
                RenderSystem.fogStart(fogStart);
                RenderSystem.fogEnd(fogEnd);
                event.setCanceled(true);
            }
        }
    }
}
