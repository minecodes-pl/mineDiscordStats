package pl.minecodes.minediscordstats.storage;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import pl.minecodes.minediscordstats.MineDiscordStats;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataManger {

    private static int currentPlayers;
    private static int recordPlayers;
    private static int uniquePlayerJoins;
    private static int playerJoins;

    public static int getCurrentPlayers() {
        return currentPlayers;
    }

    public static void setCurrentPlayers(int currentPlayers) {
        DataManger.currentPlayers = currentPlayers;
    }

    public static int getRecordPlayers() {
        return recordPlayers;
    }

    public static void setRecordPlayers(int recordPlayers) {
        DataManger.recordPlayers = recordPlayers;
    }

    public static int getUniquePlayerJoins() {
        return uniquePlayerJoins;
    }

    public static void setUniquePlayerJoins(int uniquePlayerJoins) {
        DataManger.uniquePlayerJoins = uniquePlayerJoins;
    }

    public static int getPlayerJoins() {
        return playerJoins;
    }

    public static void setPlayerJoins(int playerJoins) {
        DataManger.playerJoins = playerJoins;
    }

    public static void saveData() {
        FileConfiguration dataFile = FileManager.getDataFile();
        if(dataFile == null) {
            throw new IllegalStateException("Data file isn't loaded yet!");
        }
        dataFile.set("data.record_players", recordPlayers);
        dataFile.set("data.player_joins", playerJoins);
        try {
            dataFile.save(new File(MineDiscordStats.getInstance().getDataFolder(), "data.yml"));
        } catch (IOException e) {
            MineDiscordStats
                    .getInstance()
                    .getLogger()
                    .log(Level.WARNING, "Could not save data file! Details:");
            e.printStackTrace();
        }
    }

    public static void loadData() {
        FileConfiguration dataFile = FileManager.getDataFile();
        if(dataFile == null) {
            throw new IllegalStateException("Data file isn't loaded yet!");
        }
        recordPlayers = dataFile.getInt("data.record_players");
        uniquePlayerJoins = Bukkit.getOfflinePlayers().length;
        playerJoins = dataFile.getInt("data.player_joins");
    }
}
