package pl.minecodes.minediscordstats;

import net.dv8tion.jda.api.JDA;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.minecodes.minediscordstats.commands.MainCommand;
import pl.minecodes.minediscordstats.discord.BotInstance;
import pl.minecodes.minediscordstats.discord.BotManager;
import pl.minecodes.minediscordstats.listeners.PlayerJoinListener;
import pl.minecodes.minediscordstats.statistics.StatisticsManager;
import pl.minecodes.minediscordstats.storage.DataManger;
import pl.minecodes.minediscordstats.storage.FileManager;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public final class MineDiscordStats extends JavaPlugin {

    private static MineDiscordStats instance;

    public boolean isPapiHookEnabled() {
        return papiHookEnabled;
    }

    private boolean papiHookEnabled = false;

    public static MineDiscordStats getInstance() {
        return instance;
    }

    private void scheduleTasks(int period) {
        Bukkit.getScheduler().cancelTasks(this);
        if(period < 60) {
            getLogger().log(Level.WARNING, "Tried to use refresh time of " + period + " seconds.");
            getLogger().log(Level.WARNING, "The minimum value that you can safely use is 60, defaulting to it.");
            period = 60;
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            StatisticsManager.refreshStats();
            DataManger.saveData();
        }, period*20L, period*20L);
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    private void registerCommands() {
        getCommand("minediscordstats").setExecutor(new MainCommand());
    }

    public long init(boolean firstInit) {
        long time = System.currentTimeMillis();
        getLogger().log(Level.INFO, "Loading configuration files...");
        FileManager.loadConfiguration();
        DataManger.loadData();
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "PlaceholderAPI detected! Hooking into it.");
            papiHookEnabled = true;
        }
        if(firstInit) {
            registerListeners();
            registerCommands();
        }
        getLogger().log(Level.INFO, "Starting JDA...");
        BotInstance botInstance = BotManager.init();
        if(botInstance == null) {
            return -1;
        }
        getLogger().log(Level.INFO, "Loading statistics...");
        StatisticsManager.loadStatistics();
        scheduleTasks(FileManager.getConfig().stats().refreshTime());
        long startupTime = System.currentTimeMillis() - time;
        getLogger().log(Level.INFO, "Done! Loaded in " + startupTime + "ms.");
        return startupTime;
    }

    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this, 11315);
        init(true);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Shutting down bot instance...");
        BotManager.getBotInstance().shutdownNow();
        Bukkit.getScheduler().cancelTasks(this);
    }
}