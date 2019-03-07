package de.wumato.MultiPlug;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

public class MultiPlug extends JavaPlugin {

    //private FileConfiguration Config;
    //private File pluginPath = new File("plugins" + File.pathSeparator + info.getName());

    //public  String motd;

    @Override
    public void onEnable() {
        //if (!new File(pluginPath, "config.yml").isFile()) {
        //    this.saveDefaultConfig();
        //}
        //loadConfiguration();

        getLogger().info("Version " + this.getDescription().getVersion() + " has been successfully enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }

    //private void loadConfiguration() {
    //    Config = this.getConfig();
    //    motd = Config.getString("MOTD", "This is the Default config");
    //}
}
