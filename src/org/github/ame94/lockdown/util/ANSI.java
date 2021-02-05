package org.github.ame94.lockdown.util;

import java.util.HashMap;

public class ANSI {
    public static final String RESET = "\u001b[0m";
    public static final String BLACK = "\u001b[30m";
    public static final String DARK_RED = "\u001b[31m";
    public static final String DARK_GREEN = "\u001b[32m";
    public static final String GOLD = "\u001b[33m";
    public static final String DARK_BLUE = "\u001b[34m";
    public static final String DARK_PURPLE = "\u001b[35m";
    public static final String DARK_AQUA = "\u001b[36m";
    public static final String GRAY = "\u001b[37m";
    public static final String DARK_GRAY = "\u001b[30;1m";
    public static final String RED = "\u001b[31;1m";
    public static final String GREEN = "\u001b[32;1m";
    public static final String YELLOW = "\u001b[33;1m";
    public static final String BLUE = "\u001b[34;1m";
    public static final String PURPLE = "\u001b[35;1m";
    public static final String AQUA = "\u001b[36;1m";
    public static final String WHITE = "\u001b[37;1m";

    private static final HashMap<String, String> Mapping;
    static {
        Mapping = new HashMap<>();
        Mapping.put("&r", RESET);
        Mapping.put("&0", BLACK);
        Mapping.put("&1", DARK_BLUE);
        Mapping.put("&2", DARK_GREEN);
        Mapping.put("&3", DARK_AQUA);
        Mapping.put("&4", DARK_RED);
        Mapping.put("&5", DARK_PURPLE);
        Mapping.put("&6", GOLD);
        Mapping.put("&7", GRAY);
        Mapping.put("&8", DARK_GRAY);
        Mapping.put("&9", BLUE);
        Mapping.put("&a", GREEN);
        Mapping.put("&b", AQUA);
        Mapping.put("&c", RED);
        Mapping.put("&d", PURPLE);
        Mapping.put("&e", YELLOW);
        Mapping.put("&f", WHITE);
    }

    public static String Convert(String message) {
        if (message == null) return null;
        String result = message;
        for (String ColorCode : Mapping.keySet()) {
            result = result.replace(ColorCode, Mapping.get(ColorCode));
        }
        return result;
    }

}
