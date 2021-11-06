package pl.minecodes.minediscordstats.discord;

import java.util.logging.Level;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.storage.FileManager;

public class BotManager {

    private static final MineDiscordStats plugin = MineDiscordStats.getInstance();
    private static BotInstance botInstance;

    public static BotInstance getBotInstance() {
        return botInstance;
    }

    public static BotInstance init() {
        if (botInstance != null) {
            botInstance.shutdown();
        }
        String token = FileManager.getConfig().bot().token();
        botInstance = new BotInstance(token);
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
