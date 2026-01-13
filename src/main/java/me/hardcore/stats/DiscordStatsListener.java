package me.hardcore.stats;

import github.scarsz.discordsrv.api.DiscordUtil;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;
import github.scarsz.discordsrv.api.Listener;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class DiscordStatsListener implements Listener {

    @github.scarsz.discordsrv.api.Subscribe
    public void onMessage(DiscordGuildMessageReceivedEvent event) {

        if (!event.getMessage().getContentRaw().startsWith("/stats")) return;

        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args.length < 2) {
            event.getChannel().sendMessage("❌ Dùng: `/stats <player>`").queue();
            return;
        }

        String name = args[1];
        OfflinePlayer p = Bukkit.getOfflinePlayer(name);

        long ticks = p.getStatistic(org.bukkit.Statistic.PLAY_ONE_MINUTE);
        long seconds = ticks / 20;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;

        event.getChannel().sendMessage(
                "**📊 Stats của " + name + "**\n" +
                "⏱ Playtime: **" + hours + "h " + minutes + "m**\n"
        ).queue();
    }
}
