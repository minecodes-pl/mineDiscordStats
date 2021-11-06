package pl.minecodes.minediscordstats.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.minecodes.minediscordstats.storage.DataManager;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e) {
        DataManager.setPlayerJoins(DataManager.getPlayerJoins() + 1);
        if(!e.getPlayer().hasPlayedBefore()) {
            DataManager.setUniquePlayerJoins(DataManager.getUniquePlayerJoins() + 1);
        }
        if(Bukkit.getOnlinePlayers().size() > DataManager.getRecordPlayers()) {
            DataManager.setRecordPlayers(Bukkit.getOnlinePlayers().size());
        }
    }

}
