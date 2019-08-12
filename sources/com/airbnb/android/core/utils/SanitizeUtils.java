package com.airbnb.android.core.utils;

public final class SanitizeUtils {
    private SanitizeUtils() {
    }

    public static <T> T defaultIfNull(T value, T fallback) {
        return value == null ? fallback : value;
    }

    public static String emptyIfNull(String string) {
        return (String) defaultIfNull(string, "");
    }

    public static int zeroIfNull(Integer value) {
        if (value == null) {
            return 0;
        }
        return value.intValue();
    }

    public static long zeroIfNull(Long value) {
        if (value == null) {
            return 0;
        }
        return value.longValue();
    }

    public static Integer nullIfZero(int value) {
        if (value == 0) {
            return null;
        }
        return Integer.valueOf(value);
    }
}
