package tv.trashless.trashutils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        joinEvent.setJoinMessage("§a>> §r" + joinEvent.getPlayer().getDisplayName());
    }
}
