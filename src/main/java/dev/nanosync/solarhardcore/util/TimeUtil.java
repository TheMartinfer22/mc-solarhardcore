package dev.nanosync.solarhardcore.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Objects;

public class TimeUtil {

    private static boolean hasAnnouncementApocalypse = false;

    public static boolean isTimeToSolarApocalypse(World world) {
        boolean isApocalypse = world.isDay() && world.getDayTime() > 24000;

        if (world.getServer() == null) {
            return false;
        }

        if (isApocalypse && !hasAnnouncementApocalypse &&
                !world.getServer().getPlayerList().getPlayers().isEmpty()) {
            broadcastAnnouncement(Objects.requireNonNull(world.getServer()));
            hasAnnouncementApocalypse = true;
        }

        return isApocalypse;
    }

    private static void broadcastAnnouncement(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(player ->
                player.sendMessage(new StringTextComponent("§4[SolarHardcore] §7O apocalipse solar acaba de começar, vá para um local seguro."), player.getUUID()));
    }
}
