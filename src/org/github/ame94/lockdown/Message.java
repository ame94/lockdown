package org.github.ame94.lockdown;

import java.util.HashMap;

public class Message {

    private static HashMap<String, LanguageMapping> MessageMap = new HashMap<>();

    public static void Init() {
        for (String lang : Config.getSupportedLanguages()) {
            MessageMap.put(lang, new LanguageMapping(Config.getMessages(), lang));
        }
    }

    public static String getMessage(MessageTokens token, boolean processColors) {
        return getMessage(token, null, processColors);
    }

    public static String getMessage(MessageTokens token, String playerName, boolean processColors) {
        String langCode = Config.getLanguageCode();

        String message;
        if (processColors) {
            message = MessageMap.get(langCode).get(token);

            message = message.replace("&", "§");
        } else {
            // if the colors arent to be processed this means the message is also going to the console
            // no support for UTF-8, so messages must be displayed in English :(
            message = MessageMap.get("en").get(token);
        }
        if (playerName != null) {
            message = message.replace("%player%", playerName);
        }

        return message;
    }
}
