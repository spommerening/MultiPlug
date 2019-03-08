package de.wumato.MultiPlug;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class MultiPlug extends JavaPlugin {

    private PluginDescriptionFile info = this.getDescription();
    private int announcementTaskId;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MultiPlugPlayerLoginListener(this), this);
        announcementTimer();

        saveDefaultConfig();     // copy config.yml from jar to plugin data directory (if file does not exist)
        reloadConfiguration();   // reload configuration from config.yml in plugin data directory

        // set CommandExecutor
        this.getCommand("mp").setExecutor(new MultiPlugCommandExecutor(this));
        this.getCommand("mp").setTabCompleter(new MultiPlugCommandAutoComplete());

        getLogger().info("Version " + info.getVersion() + " has been successfully enabled.");
        getLogger().info("Plugin by " + info.getAuthors().toString());
    }

    @Override
    public void onDisable() {
    }

    void reloadConfiguration() {
        reloadConfig();

        // Restart announcementTask using possible new timer value
        this.getServer().getScheduler().cancelTask(announcementTaskId);
        announcementTimer();
    }

    private void announcementTimer() {
        int ticks = getConfig().getInt("announcement.minutes") * 60 * 20;   // 20 ticks per second

        announcementTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {

                getLogger().info("AnnouncementTimer triggered");
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("announcement.message")));
                }
            }
        }, ticks, ticks);
    }
}
