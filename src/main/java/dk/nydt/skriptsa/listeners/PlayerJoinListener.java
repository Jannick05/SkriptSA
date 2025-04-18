package dk.nydt.skriptsa.listeners;

import dk.nydt.skriptsa.SkriptSA;
import dk.nydt.skriptsa.events.SABoosterJoin;
import dk.nydt.skriptsa.events.SAStaffJoin;
import dk.nydt.skriptsa.events.SAVipJoin;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(SkriptSA.getInstance(), () -> {
            if(MutalUtils.isPlayerStaff(event.getPlayer().getName())) {
                Bukkit.getPluginManager().callEvent(new SAStaffJoin(event.getPlayer()));
            }
            if(MutalUtils.isPlayerVIP(event.getPlayer().getName())) {
                Bukkit.getPluginManager().callEvent(new SAVipJoin(event.getPlayer()));
            }
            if(MutalUtils.isPlayerServerbooster(event.getPlayer().getName())) {
                Bukkit.getPluginManager().callEvent(new SABoosterJoin(event.getPlayer()));
            }
        });
    }
}
