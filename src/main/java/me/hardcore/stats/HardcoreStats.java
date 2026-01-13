package me.hardcore.stats;

import github.scarsz.discordsrv.api.DiscordUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreStats extends JavaPlugin {

    @Override
    public void onEnable() {
        DiscordUtil.getApi().subscribe(new DiscordStatsListener());
        getLogger().info("HardcoreStats enabled");
    }
}
