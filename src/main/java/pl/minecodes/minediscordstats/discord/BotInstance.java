package pl.minecodes.minediscordstats.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import pl.minecodes.minediscordstats.placeholders.PlaceholderHandler;
import pl.minecodes.minediscordstats.storage.FileManager;

import javax.security.auth.login.LoginException;

public class BotInstance {

    private String token;
    private JDA jda;
    private boolean ready;

    public BotInstance(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JDA getJda() {
        return jda;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public JDA init() throws LoginException {
        JDA jda = JDABuilder
                .createDefault(token)
                .setAutoReconnect(true)
                .build();
        setJda(jda);
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setReady(true);
        jda.getPresence().setActivity(Activity.playing(PlaceholderHandler.parse(FileManager.getConfig().bot().status())));
        return this.jda;
    }

    public void shutdown() {
        if(jda == null || !ready) {
            throw new IllegalStateException("Bot session isn't fully started!");
        }
        jda.shutdown();
        setJda(null);
        setReady(false);
    }

    public void shutdownNow() {
        if(jda == null || !ready) {
            throw new IllegalStateException("Bot session isn't fully started!");
        }
        jda.shutdownNow();
        setJda(null);
        setReady(false);
    }


    private void registerListeners() {
        if(jda == null || !ready) {
            throw new IllegalStateException("Bot session isn't fully started!");
        }

    }

}
