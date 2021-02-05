package org.github.ame94.lockdown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.github.ame94.lockdown.util.ANSI;
import org.github.ame94.lockdown.util.Logger;
import org.github.ame94.lockdown.util.PluginMgr;

import java.util.Collection;

public class Lockdown {

    private static boolean onLockdown = false;

    public static void enableLockdown(String commandingPlayer) {
        onLockdown = true;

        Config.setLockdownConfigStatus(true);
        if (commandingPlayer == null) {
            // Server started in lockdown mode; console only message. No players will be on
            Logger.Warning(ANSI.Convert(Message.getMessage(MessageTokens.EnterLockdown, false)));
        } else {
            // Server entered lockdown at request of player or from console
            Logger.Warning(ANSI.Convert(Message.getMessage(MessageTokens.AdminIssuedLockdown, commandingPlayer, false)));
            Collection<? extends Player> onlinePlayers = PluginMgr.getPlugin().getServer().getOnlinePlayers();
            for (Player player : onlinePlayers) {
                // Notify players
                if (player.hasPermission("lockdown.admin")) {
                    player.sendMessage(Message.getMessage(MessageTokens.AdminIssuedLockdown, commandingPlayer, true));
                } else {
                    player.sendMessage(Message.getMessage(MessageTokens.EnterLockdown, true));
                }
            }
            KickUnprotectedPlayers();
        }
    }

    public static void disableLockdown(String commandingPlayer) {
        onLockdown = false;
        Config.setLockdownConfigStatus(false);
        Logger.Warning(ANSI.Convert(Message.getMessage(MessageTokens.AdminRemovedLockdown, commandingPlayer, false)));
        Collection<? extends Player> onlinePlayers = PluginMgr.getPlugin().getServer().getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (player.hasPermission("lockdown.admin")) {
                player.sendMessage(Message.getMessage(MessageTokens.AdminRemovedLockdown, commandingPlayer, true));
            } else {
                player.sendMessage(Message.getMessage(MessageTokens.LeaveLockdown, true));
            }
        }
    }

    public static boolean isLockedDown() {
        return onLockdown;
    }

    public static void KickConnectingPlayer(Player player) {
        PluginMgr.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(PluginMgr.getPlugin(), new Runnable() {
            @Override
            public void run() {
                player.kickPlayer(Message.getMessage(MessageTokens.PlayerJoinDenyReason, true));
            }
        }, 1L);
    }

    public static void KickUnprotectedPlayers() {
        Collection<? extends Player> onlinePlayers = PluginMgr.getPlugin().getServer().getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (!player.hasPermission("lockdown.bypass")) {
                player.kickPlayer(Message.getMessage(MessageTokens.KickReason, true));
            }
        }
    }

    public static boolean Command(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage("Usage: /lockdown <enable | disable>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (player.hasPermission("lockdown.admin")) {
                    if (onLockdown) {
                        player.sendMessage(Message.getMessage(MessageTokens.LockdownAlreadyEnabled, true));
                    } else {
                        enableLockdown(player.getName());
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                enableLockdown("CONSOLE");
            }
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (player.hasPermission("lockdown.admin")) {
                    if (onLockdown) {
                        disableLockdown(player.getName());
                    } else {
                        player.sendMessage(Message.getMessage(MessageTokens.ServerNotOnLockdown, true));
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                disableLockdown("CONSOLE");
            }
        }

        return true;
    }

}
