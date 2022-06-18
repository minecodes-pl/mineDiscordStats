package pl.minecodes.minediscordstats.discord;

import pl.minecodes.minediscordstats.model.DiscordActivity;

import javax.security.auth.login.LoginException;

public interface DiscordBot {

    void start() throws LoginException;

    void shutdown(boolean interrupt);

    void changeActivity(DiscordActivity activity);


}
