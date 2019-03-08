package de.wumato.MultiPlug;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.*;

public class MultiPlugCommandAutoComplete implements TabCompleter {

    private static final List<String> COMMANDS = Arrays.asList("ores", "motd", "announcement", "version", "reload");
    private static final List<String> BLANK = Arrays.asList("", "");

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length <= 1) {
            return StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<String>());
        }
        return BLANK;
    }
}
