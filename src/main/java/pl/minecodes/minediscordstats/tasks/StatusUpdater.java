package pl.minecodes.minediscordstats.tasks;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import pl.minecodes.minediscordstats.placeholders.PlaceholderHandler;
import pl.minecodes.minediscordstats.storage.FileManager;

public class StatusUpdater implements Runnable {

    private JDA jda;

    public StatusUpdater(JDA jda) {
        this.jda = jda;
    }


    @Override
    public void run() {
        jda.getPresence().setActivity(Activity.playing(PlaceholderHandler.parse(FileManager.getConfig().bot().status())));
    }
}
