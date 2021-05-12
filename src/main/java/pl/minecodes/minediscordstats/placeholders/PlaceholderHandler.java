package pl.minecodes.minediscordstats.placeholders;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import pl.minecodes.minediscordstats.MineDiscordStats;

import java.util.UUID;

public class PlaceholderHandler {

    public static String parse(String text) {
        for (InternalPlaceholder value : InternalPlaceholder.values()) {
            text = text.replace(
                    "%" + value.getName() + "%",
                    value.getReplacement()
            );
        }
        if(MineDiscordStats.getInstance().isPapiHookEnabled()) {
            text = PlaceholderAPI.setPlaceholders(
                    Bukkit.getOfflinePlayer(UUID.randomUUID()),
                    text
            );
        }
        return text;
    }

}
