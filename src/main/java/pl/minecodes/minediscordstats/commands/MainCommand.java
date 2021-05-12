package pl.minecodes.minediscordstats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.storage.FileManager;
import pl.minecodes.minediscordstats.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {

    private final List<SubCommand> subCommandList = new ArrayList<>();
    private final MineDiscordStats plugin = MineDiscordStats.getInstance();

    public MainCommand() {
        subCommandList.add(new ReloadSubCommand());
        subCommandList.add(new ParseSubCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!sender.hasPermission("minediscordstats.admin")) {
            MessageUtil.sendMessage(sender, "&amineDiscordStats v" + plugin.getDescription().getVersion()
                    + " by Nikox3003");
            MessageUtil.sendMessage(sender, "Check more of our plugins at https://minecodes.pl!");
            return true;
        }

        if(args.length == 0) {
            MessageUtil.sendMessage(sender, FileManager.getMessages().helpCommand());
            return true;
        }
        SubCommand cmd = getSubCommand(args[0]);
        if(cmd == null) {
            MessageUtil.sendMessage(sender, FileManager.getMessages().helpCommand());
            return true;
        }
        cmd.handleCommand(sender, args);
        return false;
    }

    private SubCommand getSubCommand(String name) {
        for (SubCommand subCommand : subCommandList) {
            if (subCommand.getName().equalsIgnoreCase(name) || subCommand.getAliases().contains(name)) {
                return subCommand;
            }
        }
        return null;
    }
}
