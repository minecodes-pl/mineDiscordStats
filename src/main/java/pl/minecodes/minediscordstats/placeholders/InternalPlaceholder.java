package pl.minecodes.minediscordstats.placeholders;

import org.bukkit.Bukkit;
import pl.minecodes.minediscordstats.storage.DataManger;
import pl.minecodes.minediscordstats.storage.FileManager;

public enum InternalPlaceholder {

    ONLINE_PLAYERS("online_players"),
    RECORD_PLAYERS("record_players"),
    UNIQUE_PLAYER_JOINS("unique_player_joins"),
    PLAYER_JOINS("player_joins"),
    MAX_PLAYERS("max_players"),
    SERVER_STATUS("server_status");

    public String getName() {
        return name;
    }

    public String getReplacement() {
        switch (this) {
            case ONLINE_PLAYERS:
                return String.valueOf(Bukkit.getOnlinePlayers().size());
            case RECORD_PLAYERS:
                return String.valueOf(DataManger.getRecordPlayers());
            case UNIQUE_PLAYER_JOINS:
                return String.valueOf(Bukkit.getOfflinePlayers().length);
            case PLAYER_JOINS:
                return String.valueOf(DataManger.getPlayerJoins());
            case MAX_PLAYERS:
                return String.valueOf(Bukkit.getMaxPlayers());
            case SERVER_STATUS:
                if(Bukkit.getServer().isStopping()) {
                    return FileManager.getMessages().placeholders().statusOffline();
                } else {
                    return FileManager.getMessages().placeholders().statusOnline();
                }
            default:
                return "undefined";
        }
    }

    private String name;

    InternalPlaceholder(String name) {
        this.name = name;
    }
}
