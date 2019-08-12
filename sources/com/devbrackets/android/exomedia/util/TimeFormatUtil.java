package com.devbrackets.android.exomedia.util;

import java.util.Formatter;
import java.util.Locale;

public class TimeFormatUtil {
    private static StringBuilder formatBuilder = new StringBuilder();
    private static Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());

    public static String formatMs(long milliseconds) {
        if (milliseconds < 0) {
            return "--:--";
        }
        long seconds = (milliseconds % 60000) / 1000;
        long minutes = (milliseconds % 3600000) / 60000;
        long hours = (milliseconds % 86400000) / 3600000;
        formatBuilder.setLength(0);
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}).toString();
    }
}
