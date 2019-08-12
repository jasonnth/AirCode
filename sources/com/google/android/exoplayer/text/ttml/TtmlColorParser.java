package com.google.android.exoplayer.text.ttml;

import android.text.TextUtils;
import com.airbnb.android.airmapview.AirMapInterface;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class TtmlColorParser {
    private static final Map<String, Integer> COLOR_NAME_MAP = new HashMap();
    private static final Pattern RGBA_PATTERN = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");

    static {
        COLOR_NAME_MAP.put("transparent", Integer.valueOf(0));
        COLOR_NAME_MAP.put("black", Integer.valueOf(AirMapInterface.CIRCLE_BORDER_COLOR));
        COLOR_NAME_MAP.put("silver", Integer.valueOf(-4144960));
        COLOR_NAME_MAP.put("gray", Integer.valueOf(-8355712));
        COLOR_NAME_MAP.put("white", Integer.valueOf(-1));
        COLOR_NAME_MAP.put("maroon", Integer.valueOf(-8388608));
        COLOR_NAME_MAP.put("red", Integer.valueOf(-65536));
        COLOR_NAME_MAP.put("purple", Integer.valueOf(-8388480));
        COLOR_NAME_MAP.put("fuchsia", Integer.valueOf(-65281));
        COLOR_NAME_MAP.put("magenta", Integer.valueOf(-65281));
        COLOR_NAME_MAP.put("green", Integer.valueOf(-16744448));
        COLOR_NAME_MAP.put("lime", Integer.valueOf(-16711936));
        COLOR_NAME_MAP.put("olive", Integer.valueOf(-8355840));
        COLOR_NAME_MAP.put("yellow", Integer.valueOf(-256));
        COLOR_NAME_MAP.put("navy", Integer.valueOf(-16777088));
        COLOR_NAME_MAP.put("blue", Integer.valueOf(-16776961));
        COLOR_NAME_MAP.put("teal", Integer.valueOf(-16744320));
        COLOR_NAME_MAP.put("aqua", Integer.valueOf(16777215));
        COLOR_NAME_MAP.put("cyan", Integer.valueOf(-16711681));
    }

    public static int parseColor(String colorExpression) {
        Assertions.checkArgument(!TextUtils.isEmpty(colorExpression));
        String colorExpression2 = colorExpression.replace(" ", "");
        if (colorExpression2.charAt(0) == '#') {
            int color = (int) Long.parseLong(colorExpression2.substring(1), 16);
            if (colorExpression2.length() == 7) {
                return color | AirMapInterface.CIRCLE_BORDER_COLOR;
            }
            if (colorExpression2.length() == 9) {
                return ((color & 255) << 24) | (color >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (colorExpression2.startsWith("rgba")) {
            Matcher matcher = RGBA_PATTERN.matcher(colorExpression2);
            if (matcher.matches()) {
                return argb(255 - Integer.parseInt(matcher.group(4), 10), Integer.parseInt(matcher.group(1), 10), Integer.parseInt(matcher.group(2), 10), Integer.parseInt(matcher.group(3), 10));
            }
        } else if (colorExpression2.startsWith("rgb")) {
            Matcher matcher2 = RGB_PATTERN.matcher(colorExpression2);
            if (matcher2.matches()) {
                return rgb(Integer.parseInt(matcher2.group(1), 10), Integer.parseInt(matcher2.group(2), 10), Integer.parseInt(matcher2.group(3), 10));
            }
        } else {
            Integer color2 = (Integer) COLOR_NAME_MAP.get(Util.toLowerInvariant(colorExpression2));
            if (color2 != null) {
                return color2.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private static int argb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    private static int rgb(int red, int green, int blue) {
        return argb(255, red, green, blue);
    }
}
