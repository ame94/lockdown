package org.github.ame94.lockdown.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMgr {

    private static PluginManager pluginMgr;
    private static JavaPlugin pluginRef;

    public static JavaPlugin getPlugin() {
        return pluginRef;
    }

    public static void Init(JavaPlugin p) {
        pluginMgr = Bukkit.getPluginManager();
        pluginRef = p;
    }

    public static String GetName() {
        return PluginMgr.getPlugin().getDescription().getName();
    }

    public static void RegisterEvent(Listener l) {
        pluginMgr.registerEvents(l, pluginRef);
    }
}
