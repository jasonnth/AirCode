package com.airbnb.android.lib.utils;

public final class MathUtils {
    private MathUtils() {
    }

    static double random(double randomValue, double min, double max) {
        return ((max - min) * randomValue) + min;
    }
}
