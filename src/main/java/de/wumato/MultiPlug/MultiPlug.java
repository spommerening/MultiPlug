package de.wumato.MultiPlug;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class MultiPlug extends JavaPlugin {

    private FileConfiguration config;
    private PluginDescriptionFile info = this.getDescription();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);

        saveDefaultConfig();     // copy config.yml from jar to plugin data directory (if file does not exist)
        reloadConfiguration();   // reload configuration from config.yml in plugin data directory

        getLogger().info("Version " + info.getVersion() + " has been successfully enabled.");
        getLogger().info("Plugin by " + info.getAuthors().toString());
    }

    @Override
    public void onDisable() {
        getLogger().info("has been disabled.");
    }

    private void reloadConfiguration() {
        reloadConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("multiplug")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.AQUA + "MultiPlug running!");
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                getLogger().info("Reloading configuration");
                reloadConfiguration();
                player.sendMessage(ChatColor.GREEN + "Configuration reloaded");
                return true;
            }
            if (args[0].equalsIgnoreCase("motd")) {
                player.sendMessage(ChatColor.GREEN + "MultiPlug MOTD:");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("MOTD")));
                return true;
            }
            player.sendMessage(ChatColor.RED + "Action unknown to MultiPlug!");
            return false;
        } else {
            return false;
        }
    }
}
