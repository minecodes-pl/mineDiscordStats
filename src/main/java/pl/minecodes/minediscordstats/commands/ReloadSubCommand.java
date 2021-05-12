package pl.minecodes.minediscordstats.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.storage.FileManager;
import pl.minecodes.minediscordstats.util.MessageUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReloadSubCommand extends SubCommand {

    private MineDiscordStats plugin = MineDiscordStats.getInstance();

    public ReloadSubCommand() {
        super("reload", Collections.singletonList("restart"));
    }

    @Override
    public void handleCommand(CommandSender sender, String[] arguments) {
        MessageUtil.sendMessage(sender, FileManager.getMessages().pendingReload());
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.init(false);
            MessageUtil.sendMessage(sender, FileManager.getMessages().reloadCompleted());
        });
    }
}
