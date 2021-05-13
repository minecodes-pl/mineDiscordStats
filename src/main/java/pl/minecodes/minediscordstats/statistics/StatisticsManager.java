package pl.minecodes.minediscordstats.statistics;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.storage.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticsManager {

    private static List<Statistic> statisticList = new ArrayList<>();

    public static void refreshStats() {
        if(!Bukkit.getServer().isStopping()) {
            statisticList.forEach(Statistic::update);
        } else {
            statisticList.forEach(statistic -> {
                if(statistic.isUpdateOnServerStop()) {
                    statistic.update();
                }
            });
        }
    }

    public static List<Statistic> getStatisticList() {
        return statisticList;
    }

    public static void loadStatistics() {
        Logger logger = MineDiscordStats.getInstance().getLogger();
        FileConfiguration stats = FileManager.getStatsFile();
        statisticList.clear();
        for (String key : stats.getConfigurationSection("stats").getKeys(false)) {
            boolean enabled = stats.getBoolean("stats." + key + ".enabled");
            if(!enabled) {
                continue;
            }
            try {
                String name = Objects.requireNonNull(stats.getString("stats." + key + ".name"));
                String value = Objects.requireNonNull(stats.getString("stats." + key + ".value"));
                long channel = stats.getLong("stats." + key + ".channel");
                statisticList.add(new Statistic(name, value, channel));
            } catch (NullPointerException ex) {
                logger.log(Level.WARNING, "Encountered exception while trying to parse statistic \"" + key + "\"");
                logger.log(Level.WARNING, "Details:");
                logger.log(Level.WARNING, ex.getMessage());
                logger.log(Level.WARNING, "Make sure that values of \"name\", \"value\" and \"channel\" are defined.");
                logger.log(Level.WARNING, "---------------------");
            }
        }
        refreshStats();
    }



}
