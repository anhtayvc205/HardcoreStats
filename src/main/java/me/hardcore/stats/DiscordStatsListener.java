package me.hardcore.stats;

import github.scarsz.discordsrv.api.Listener;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;

import org.bukkit.Bukkit;

public class DiscordStatsListener implements Listener {

    @Subscribe
    public void onDiscordMessage(DiscordGuildMessageReceivedEvent event) {

        // Chỉ nghe lệnh /stats
        if (!event.getMessage().getContentRaw().equalsIgnoreCase("/stats")) return;

        int online = Bukkit.getOnlinePlayers().size();

        event.getChannel().sendMessage(
                "🔥 **HARDCORE SERVER STATS**\n" +
                "👥 Online: **" + online + "**"
        ).queue();
    }
}
