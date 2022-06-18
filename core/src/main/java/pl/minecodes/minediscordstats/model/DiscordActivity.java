package pl.minecodes.minediscordstats.model;

public class DiscordActivity {

    private Type type;
    private String value;
    private Status status;
    private String url;

    public DiscordActivity(Status status) {
        this(status, null, null);
    }

    public DiscordActivity(Status status, Type type, String value) {
        this(status, type, value, null);
    }

    public DiscordActivity(Status status, Type type, String value, String url) {
        this.type = type;
        this.value = value;
        this.status = status;
        this.url = url;
    }

    public static DiscordActivity empty() {
        return new DiscordActivity(Status.ONLINE);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public enum Type {

        NONE(-1),
        PLAYING(0),
        STREAMING(1),
        LISTENING(2),
        WATCHING(3),
        CUSTOM(4),
        COMPETING(5);

        private final int key;

        Type(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }
    }

    public enum Status {
        INVISIBLE("invisible"),
        AWAY("away"),
        DO_NOT_DISTURB("dnd"),
        ONLINE("online");

        private final String key;

        Status(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

}
