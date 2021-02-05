package org.github.ame94.lockdown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.ame94.lockdown.listeners.PlayerLogin;
import org.github.ame94.lockdown.util.PluginMgr;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginMgr.Init(this);
        Config.Load();
        Message.Init();

        PluginMgr.RegisterEvent(new PlayerLogin());

        if (Config.isOnLockdown()) {
            Lockdown.enableLockdown(null);
        }

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return Lockdown.Command(sender, command, label, args);
    }

}
