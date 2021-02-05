package org.github.ame94.lockdown;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.HashMap;

public class LanguageMapping {
    private HashMap<MessageTokens, String> map;

    public LanguageMapping(YamlConfiguration yml, String langCode) {
        map = new HashMap<>();

        ConfigurationSection langSection = yml.getConfigurationSection(langCode);
        for (String key : langSection.getKeys(false)) {
            map.put(MessageTokens.valueOf(key), langSection.getString(key));
        }
    }
    public String get(MessageTokens token) {
        return map.get(token);
    }
}
