package org.github.ame94.lockdown;

import org.bukkit.configuration.file.YamlConfiguration;
import org.github.ame94.lockdown.util.Logger;
import org.github.ame94.lockdown.util.PluginMgr;
import java.io.*;
import java.util.*;

public class Config {

    private static String languageCode = "en";
    private static HashSet<String> supportedLanguages = new HashSet<>();
    private static boolean onLockdown = false;
    private static YamlConfiguration messages;

    public static HashSet<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public static YamlConfiguration getMessages() {
        return messages;
    }

    public static String getLanguageCode() {
        return languageCode;
    }

    public static boolean isOnLockdown() {
        return onLockdown;
    }

    public static void setLockdownConfigStatus(boolean enable) {
        File dataFolder = new File(PluginMgr.getPlugin().getDataFolder().getAbsolutePath());
        File configFile = new File(dataFolder, "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("onLockdown", enable);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load() {
        File dataFolder = new File(PluginMgr.getPlugin().getDataFolder().getAbsolutePath());
        File configFile = new File(dataFolder, "config.yml");
        File messagesFile = new File(dataFolder, "messages.yml");

        if (!dataFolder.exists()) {
            Logger.Info("Initializing plugin...");
            dataFolder.mkdir();

            PluginMgr.getPlugin().saveResource("config.yml", true);
            PluginMgr.getPlugin().saveResource("messages.yml", true);
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);

        // Load in supported languages
        supportedLanguages.addAll(messages.getKeys(false));

        // forceLang
        String configLangSetting = config.getString("forceLang");

        if (configLangSetting == null) {
            Logger.Warning("Could not determine language setting; defaulting to English.");
        } else {
            if (configLangSetting.equalsIgnoreCase("false")) {
                String detectedLocaleLang = Locale.getDefault().getLanguage();
                if (supportedLanguages.contains(detectedLocaleLang)) {
                    languageCode = detectedLocaleLang;
                } else {
                    Logger.Info("No current translation exists yet for system language. Please consider submitting one!");
                }
            } else {
                if (configLangSetting.length() == 2) {
                    // should be two-letter language code
                    if (supportedLanguages.contains(configLangSetting)) {
                        languageCode = configLangSetting;
                    } else {
                        Logger.Warning("Sorry, no translation exists yet. Please consider submitting one!");
                    }
                } else {
                    Logger.Warning("Could not determine language setting; defaulting to English.");
                }
            }
        }

        // Currently on lockdown?
        if (config.getBoolean("onLockdown")) {
            onLockdown = true;
        }

    }

}
