package me.hardcore.stats;

import org.bukkit.plugin.java.JavaPlugin;
import github.scarsz.discordsrv.api.DiscordUtil;

public class HardcoreStats extends JavaPlugin {

    @Override
    public void onEnable() {
        DiscordUtil.api.subscribe(new DiscordStatsListener());
        getLogger().info("HardcoreStats + DiscordSRV enabled");
    }
}
