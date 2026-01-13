package me.hardcore.stats;

import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreStats extends JavaPlugin {

    private static HardcoreStats instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("stats").setExecutor(new StatsCommand());

        getLogger().info("HardcoreStats enabled");
    }

    public static HardcoreStats getInstance() {
        return instance;
    }
}
