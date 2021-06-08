package pl.minecodes.minediscordstats.statistics;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;
import pl.minecodes.minediscordstats.MineDiscordStats;
import pl.minecodes.minediscordstats.discord.BotManager;
import pl.minecodes.minediscordstats.placeholders.PlaceholderHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Statistic {

    public Statistic(String name, String value, long channelId) {
        this.name = name;
        this.value = value;
        this.channelId = channelId;
    }

    public Statistic(String name, String value, long channelId, boolean updateOnServerStop) {
        this.name = name;
        this.value = value;
        this.channelId = channelId;
        this.updateOnServerStop = updateOnServerStop;
    }

    public void update() {
        JDA jda = BotManager.getBotInstance().getJda();
        VoiceChannel channel = jda.getVoiceChannelById(channelId);
        if(channel == null) {
            Logger logger = MineDiscordStats.getInstance().getLogger();
            logger.log(Level.WARNING, "Could not update statistic \"" + name + "\" on channel with ID \"" +
                    channelId + "\". Please check your configuration.");
            logger.log(Level.WARNING, "---------------------");
            return;
        }
        try {
            channel.getManager().setName(name + ": " + PlaceholderHandler.parse(value)).queue(
                    success -> {
                        // no need to do anything here
                    },
                    failure -> {
                        Logger logger = MineDiscordStats.getInstance().getLogger();
                        logger.log(Level.WARNING, "Could not update statistic \"" + name + "\" on channel with ID \"" +
                                channelId + "\".");
                        logger.log(Level.WARNING, "Details:");
                        logger.log(Level.WARNING, failure.getMessage());;
                        logger.log(Level.WARNING, "---------------------");
                    }
            );
        } catch (Exception ex) {
            Logger logger = MineDiscordStats.getInstance().getLogger();
            logger.log(Level.WARNING, "Could not update statistic \"" + name + "\" on channel with ID \"" +
                    channelId + "\".");
            logger.log(Level.WARNING, "Details:");
            logger.log(Level.WARNING, ex.getMessage());
            logger.log(Level.WARNING, "---------------------");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public boolean isUpdateOnServerStop() {
        return updateOnServerStop;
    }

    public void setUpdateOnServerStop(boolean updateOnServerStop) {
        this.updateOnServerStop = updateOnServerStop;
    }

    private String name;
    private String value;
    private long channelId;
    private boolean updateOnServerStop;




}
