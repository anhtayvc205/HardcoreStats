package me.hardcore.stats;

import github.scarsz.discordsrv.api.Listener;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

public class DiscordStatsListener implements Listener {

    @Subscribe
    public void onDiscordMessage(DiscordGuildMessageReceivedEvent event) {

        String msg = event.getMessage().getContentRaw();
        if (!msg.startsWith("/stats")) return;

        String[] args = msg.split(" ");
        if (args.length < 2) {
            event.getChannel()
                .sendMessage("❌ Dùng: /stats <tên_player>")
                .queue();
            return;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);

        int blocksBroken = p.getStatistic(Statistic.MINE_BLOCK);
        int blocksPlaced = p.getStatistic(Statistic.USE_ITEM);
        int mobsKilled = p.getStatistic(Statistic.MOB_KILLS);
        long playTicks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long hours = (playTicks / 20) / 3600;

        String avatar = "https://mc-heads.net/avatar/" + p.getName() + "/128";

        event.getChannel().sendMessage(
            "**📊 " + p.getName() + " Statistics**\n" +
            "🟩 Blocks đặt: **" + blocksPlaced + "**\n" +
            "⛏ Blocks đào: **" + blocksBroken + "**\n" +
            "☠ Quái giết: **" + mobsKilled + "**\n" +
            "⏱ Thời gian chơi: **" + hours + "h**\n" +
            avatar
        ).queue();
    }
}
