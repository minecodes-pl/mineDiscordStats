package pl.minecodes.minediscordstats.commands;

import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public abstract class SubCommand {

    private final String name;
    private final List<String> aliases;

    public SubCommand(String name, List<String> aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public SubCommand(String name) {
        this(name, Collections.emptyList());
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public abstract void handleCommand(CommandSender sender, String[] arguments);

}
