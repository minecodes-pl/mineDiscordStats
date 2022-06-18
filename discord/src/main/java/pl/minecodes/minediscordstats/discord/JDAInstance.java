package pl.minecodes.minediscordstats.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.managers.Presence;
import pl.minecodes.minediscordstats.model.DiscordActivity;

import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.logging.Logger;

public class JDAInstance implements DiscordBot {

    private final Logger logger;
    private final String token;
    private DiscordActivity activity;

    private JDA jda;

    public JDAInstance(Logger logger, String token, DiscordActivity activity) {
        Objects.requireNonNull(logger, "Provided invalid logger.");
        Objects.requireNonNull(token, "Bot token was not provided.");

        this.logger = logger;
        this.token = token;
        this.activity = activity == null ? DiscordActivity.empty() : activity;
    }

    @Override
    public void start() throws LoginException {
        jda = JDABuilder.createDefault(token)
                .setAutoReconnect(true)
                .build();

        try {
            jda.awaitReady();
        } catch (InterruptedException exception) {
            logger.severe("JDA starting thread was interrupted.");
            logger.severe(exception.getMessage());
            return;
        }

        changeActivity(this.activity);
    }

    @Override
    public void shutdown(boolean interrupt) {

    }

    @Override
    public void changeActivity(DiscordActivity activity) {
        if (jda == null) {
            throw new IllegalStateException("JDA not initialized.");
        }

        this.activity = activity;

        Presence presence = jda.getPresence();

        presence.setStatus(OnlineStatus.fromKey(activity.getStatus().getKey()));
        presence.setActivity(Activity.of(
                Activity.ActivityType.fromKey(activity.getType().getKey()),
                activity.getValue(),
                activity.getUrl()
        ));

    }
}
