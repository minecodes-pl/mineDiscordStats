package pl.minecodes.minediscordstats.commands;

import org.bukkit.command.CommandSender;
import pl.minecodes.minediscordstats.placeholders.PlaceholderHandler;
import pl.minecodes.minediscordstats.storage.FileManager;
import pl.minecodes.minediscordstats.util.MessageUtil;

public class ParseSubCommand extends SubCommand {

    public ParseSubCommand() {
        super("parse");
    }

    @Override
    public void handleCommand(CommandSender sender, String[] arguments) {
        if(arguments.length == 1) {
            MessageUtil.sendMessage(sender, FileManager.getMessages().parseMessageRequired());
            return;
        }
        String text = "";
        for (int i=1;i<arguments.length;i++) {
            text = text + arguments[i] + " ";
        }
        MessageUtil.sendMessage(sender, FileManager.getMessages().messageParsed());
        MessageUtil.sendMessage(sender, PlaceholderHandler.parse(text));
    }
}
