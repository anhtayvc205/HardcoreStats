package me.hardcore.stats;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.awt.*;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        var cfg = HardcoreStats.getInstance().getConfig();

        if (args.length != 1) {
            sender.sendMessage("§c/stats <player>");
            return true;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(cfg.getString("messages.offline").replace("&", "§"));
            return true;
        }

        // ===== PLAYTIME =====
        long ticks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long seconds = ticks / 20;
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;

        // ===== STATISTICS =====
        int broken = p.getStatistic(Statistic.MINE_BLOCK);
        int placed = p.getStatistic(Statistic.USE_ITEM);
        int mobs = p.getStatistic(Statistic.MOB_KILLS);
        int deaths = p.getStatistic(Statistic.DEATHS);

        // ⚠️ THAY BẰNG API HARDCORE CỦA BẠN
        int lives = 5;

        // ===== EMBED =====
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(cfg.getString("embed.title"));
        eb.setThumbnail("https://mc-heads.net/avatar/" + p.getName() + "/128");
        eb.setFooter(cfg.getString("embed.footer"));
        eb.setColor(Color.RED);

        if (cfg.getBoolean("display.lives"))
            eb.addField("❤️ Mạng sống", String.valueOf(lives), true);

        if (cfg.getBoolean("display.playtime"))
            eb.addField("⏱ Thời gian", days + "d " + hours + "h", true);

        if (cfg.getBoolean("display.blocks-broken"))
            eb.addField("⛏ Block đào", String.valueOf(broken), true);

        if (cfg.getBoolean("display.blocks-placed"))
            eb.addField("🧱 Block đặt", String.valueOf(placed), true);

        if (cfg.getBoolean("display.mobs-killed"))
            eb.addField("☠ Quái giết", String.valueOf(mobs), true);

        if (cfg.getBoolean("display.deaths"))
            eb.addField("💀 Chết", String.valueOf(deaths), true);

        DiscordUtil.sendMessage(
                DiscordSRV.api.getMainGuild()
                        .getTextChannelsByName(cfg.getString("discord.channel"), true)
                        .get(0),
                eb.build()
        );

        sender.sendMessage(cfg.getString("messages.sent").replace("&", "§"));
        return true;
    }
}
