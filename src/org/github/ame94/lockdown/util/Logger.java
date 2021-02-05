package org.github.ame94.lockdown.util;

import org.bukkit.Bukkit;
import java.util.logging.Level;

public class Logger {
    public static void Info(String msg) {
        String logEntry = "[" + PluginMgr.GetName() + "] &f" + msg + "&f";
        Bukkit.getLogger().log(Level.INFO, ANSI.Convert(logEntry));
    }

    public static void Warning(String msg) {
        String logEntry = "[&e" + PluginMgr.GetName() + "&f] &e" + msg + "&f";
        Bukkit.getLogger().log(Level.WARNING, ANSI.Convert(logEntry));
    }

    public static void Error(String msg) {
        String logEntry = "[&c" + PluginMgr.GetName() + "&f] &c" + msg + "&f";
        Bukkit.getLogger().log(Level.SEVERE, ANSI.Convert(logEntry));
    }
}
