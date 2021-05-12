package pl.minecodes.minediscordstats.discord;

import net.dv8tion.jda.api.JDA;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.storage.FileManager;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public class BotManager {

    private static final MineDiscordStats plugin = MineDiscordStats.getInstance();
    private static BotInstance botInstance;

    public static BotInstance getBotInstance() {
        return botInstance;
    }

    public static void setBotInstance(BotInstance botInstance) {
        BotManager.botInstance = botInstance;
    }

    public static BotInstance init() {
        if (botInstance != null) {
            botInstance.shutdown();
        }
        String token = FileManager.getConfig().bot().token();
        botInstance = new BotInstance(token);
        JDA jda;
        try {
            jda = botInstance.init();
        } catch (LoginException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not login to bot account! Details:");
            plugin.getLogger().log(Level.SEVERE, "\"" + e.getMessage() + "\"");
            plugin.getLogger().log(Level.SEVERE, "If you need help with setup, please check our wiki.");
            plugin.getLogger().log(Level.SEVERE, "For now plugin will be disabled.");
            plugin.getPluginLoader().disablePlugin(plugin);
            return null;
        }
        return botInstance;
    }

    public static BotInstance restart() {
        if (botInstance == null) {
            throw new IllegalStateException("Bot session isn't fully started!");
        }
        plugin.getLogger().log(Level.INFO, "Restarting JDA...");
        botInstance.shutdown();
        botInstance = null;
        return init();
    }

}
