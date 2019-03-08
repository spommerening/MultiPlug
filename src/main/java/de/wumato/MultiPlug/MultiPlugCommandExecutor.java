package de.wumato.MultiPlug;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class MultiPlugCommandExecutor implements CommandExecutor {
    private final MultiPlug plugin;

    // Get reference to plugin it belongs to
    MultiPlugCommandExecutor(MultiPlug plugin) {
        this.plugin = plugin;
    }

    // onCommand for all plugin commands (actions)
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("mp")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.AQUA + "MultiPlug running!");
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (player.hasPermission("mp.reload")) {
                    plugin.getLogger().info("Reloading configuration");
                    plugin.reloadConfiguration();
                    player.sendMessage(ChatColor.GREEN + "Configuration reloaded");
                } else {
                    player.sendMessage(ChatColor.RED + "No, you don't have permission.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("version")) {
                player.sendMessage(ChatColor.GREEN + "MultiPlug version " + plugin.getDescription().getVersion());
                return true;
            }
            if (args[0].equalsIgnoreCase("motd")) {
                player.sendMessage(ChatColor.GREEN + "MultiPlug MOTD:");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MOTD")));
                return true;
            }
            if (args[0].equalsIgnoreCase("announcement")) {
                player.sendMessage(ChatColor.GREEN + "MultiPlug Announcement message:");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("announcement.message")));
                return true;
            }
            if (args[0].equalsIgnoreCase("ores")) {
                return blockRadar(player);
            }
            player.sendMessage(ChatColor.RED + "Action unknown to MultiPlug!");
            return false;
        } else {
            return false;
        }
    }

    private boolean blockRadar(Player player) {
        int radius = plugin.getConfig().getInt("ores.radius");
        Map<String, Integer> oreMap = new HashMap<String, Integer>();
        int oreCount;

        player.sendMessage(ChatColor.GREEN + "MultiPlug Ores: (radius: " + radius + ")");

        // Find nearby blocks with a radius
        List<Block> foundBlocks = getNearbyBlocks(player.getLocation(), radius);

        // loop over found blocks and count ores
        for (Block b : foundBlocks) {
            switch (b.getType()) {
                case COAL_ORE:
                case IRON_ORE:
                case REDSTONE_ORE:
                case LAPIS_ORE:
                case NETHER_QUARTZ_ORE:
                case GOLD_ORE:
                case DIAMOND_ORE:
                case EMERALD_ORE:
                    if (oreMap.containsKey(b.getType().toString())) {
                        oreCount = oreMap.get(b.getType().toString()) + 1;
                        oreMap.put(b.getType().toString(), oreCount);
                    } else {
                        oreMap.put(b.getType().toString(), 1);
                    }
                    break;
            }
        }

        // Show ore counting results to player
        for (Map.Entry<String, Integer> entry : oreMap.entrySet()) {
            player.sendMessage(ChatColor.YELLOW + entry.getKey() + ": " + entry.getValue());
        }
        return true;
    }

    private static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();

        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
