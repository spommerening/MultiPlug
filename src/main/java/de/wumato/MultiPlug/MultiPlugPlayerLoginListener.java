package de.wumato.MultiPlug;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MultiPlugPlayerLoginListener implements Listener {

    private MultiPlug plugin;

    MultiPlugPlayerLoginListener(MultiPlug instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MOTD")));
    }
}
