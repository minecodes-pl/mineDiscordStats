package pl.minecodes.minediscordstats.model;

public class Statistic {

    private String id;
    private Objective objective;
    private long channelId;

    public enum Objective {

        ONLINE_PLAYERS,
        RECORD_PLAYERS,
        ALL_TIME_UNIQUE_PLAYERS,
        PLACEHOLDER,
        STATIC_VALUE

    }

}
