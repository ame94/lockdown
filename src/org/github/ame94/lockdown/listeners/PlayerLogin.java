package org.github.ame94.lockdown.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.github.ame94.lockdown.Lockdown;
import org.github.ame94.lockdown.Message;
import org.github.ame94.lockdown.MessageTokens;
import org.github.ame94.lockdown.util.ANSI;
import org.github.ame94.lockdown.util.Logger;

public class PlayerLogin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (Lockdown.isLockedDown()) {
            Player player = event.getPlayer();
            if (!player.hasPermission("lockdown.bypass")) {
                Logger.Warning(ANSI.Convert(Message.getMessage(MessageTokens.KickingPlayerDuringLockdown, player.getName(), false)));
                Lockdown.KickConnectingPlayer(player);
            }
        }
    }

}
