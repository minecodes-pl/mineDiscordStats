package pl.minecodes.minediscordstats.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static String format(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        message = message.replace(">>", "»").replace("<<", "«");
        return message;
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(format(message));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(format(message));
    }
}
