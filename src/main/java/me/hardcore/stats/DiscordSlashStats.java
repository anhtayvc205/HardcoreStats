package me.hardcore.stats;

import github.scarsz.discordsrv.util.DiscordUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.awt.*;

public class DiscordSlashStats extends ListenerAdapter {

    public static void register(JDA jda) {
        jda.upsertCommand(
                Commands.slash("stats", "Xem Hardcore Stats")
                        .addOption(
                                OptionType.STRING,
                                "player",
                                "Tên người chơi",
                                true
                        )
        ).queue();

        jda.addEventListener(new DiscordSlashStats());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (!event.getName().equals("stats")) return;

        String playerName = event.getOption("player").getAsString();
        Player p = Bukkit.getPlayer(playerName);

        if (p == null) {
            event.reply("❌ Người chơi không online!")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        long ticks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long seconds = ticks / 20;
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;

        int broken = p.getStatistic(Statistic.MINE_BLOCK);
        int placed = p.getStatistic(Statistic.USE_ITEM);
        int mobs = p.getStatistic(Statistic.MOB_KILLS);

        int lives = 5; // hook plugin hardcore của bạn

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("☠ HARDCORE STATISTICS ☠");
        eb.setColor(Color.RED);
        eb.setThumbnail("https://mc-heads.net/avatar/" + p.getName() + "/128");

        eb.addField("❤️ Mạng sống", String.valueOf(lives), true);
        eb.addField("⏱ Thời gian", days + "d " + hours + "h", true);
        eb.addField("⛏ Block đào", String.valueOf(broken), true);
        eb.addField("🧱 Block đặt", String.valueOf(placed), true);
        eb.addField("☠ Quái giết", String.valueOf(mobs), true);

        eb.setFooter("Hardcore Survival | Paper 1.21.11");

        event.replyEmbeds(eb.build()).queue();
    }
}
