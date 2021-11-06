package pl.minecodes.minediscordstats.storage;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.minecodes.minediscordstats.MineDiscordStats;

public class FileManager {

    private static final MineDiscordStats plugin = MineDiscordStats.getInstance();
    private static Config config;
    private static Messages messages;
    private static FileConfiguration data;
    private static FileConfiguration stats;

    public static Config getConfig() {
        return config;
    }
    public static Messages getMessages() {
        return messages;
    }

    public static FileConfiguration getDataFile() {
        return data;
    }
    public static FileConfiguration getStatsFile() {
        return stats;
    }

    public static void loadConfiguration() {
        config = loadConfig();
        messages = loadMessages();
        data = loadDataFile();
        stats = loadStatisticsFile();
    }

    public static Config loadConfig() {
        ConfigurationManager<Config> configManager = ConfigurationManager.create(plugin.getDataFolder().toPath(), "config.yml", Config.class);
        configManager.reloadConfig();
        return configManager.getConfigData();
    }

    public static Messages loadMessages() {
        ConfigurationManager<Messages> configManager = ConfigurationManager.create(plugin.getDataFolder().toPath(), "messages.yml", Messages.class);
        configManager.reloadConfig();
        return configManager.getConfigData();
    }

    public static FileConfiguration loadDataFile() {
        File dataFile = new File(plugin.getDataFolder(), "data.yml");
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                plugin.getPluginLoader().disablePlugin(plugin);
            }
        }
        return YamlConfiguration.loadConfiguration(dataFile);
    }

    public static FileConfiguration loadStatisticsFile() {
        File statsFile = new File(plugin.getDataFolder(), "statistics.yml");
        if(!statsFile.exists()) {
            plugin.saveResource("statistics.yml", false);
        }
        return YamlConfiguration.loadConfiguration(statsFile);
    }
}
