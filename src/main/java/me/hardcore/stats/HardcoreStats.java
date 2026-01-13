package me.hardcore.stats;

import github.scarsz.discordsrv.DiscordSRV;
import net.dv8tion.jda.api.JDA;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreStats extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        JDA jda = DiscordSRV.api.getMainGuild().getJda();
        DiscordSlashStats.register(jda);

        getLogger().info("HardcoreStats enabled (Discord Slash)");
    }
}
