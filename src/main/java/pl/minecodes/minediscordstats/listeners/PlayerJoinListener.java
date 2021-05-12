package pl.minecodes.minediscordstats.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.minecodes.minediscordstats.storage.DataManger;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e) {
        DataManger.setPlayerJoins(DataManger.getPlayerJoins() + 1);
        if(!e.getPlayer().hasPlayedBefore()) {
            DataManger.setUniquePlayerJoins(DataManger.getUniquePlayerJoins() + 1);
        }
        if(Bukkit.getOnlinePlayers().size() > DataManger.getRecordPlayers()) {
            DataManger.setRecordPlayers(Bukkit.getOnlinePlayers().size());
        }
    }

}
