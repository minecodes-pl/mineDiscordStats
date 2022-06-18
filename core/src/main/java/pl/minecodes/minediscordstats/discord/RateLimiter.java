package pl.minecodes.minediscordstats.discord;

public interface RateLimiter {

    void change(long channelId, String name);

    boolean canChange(long channelId);

}
