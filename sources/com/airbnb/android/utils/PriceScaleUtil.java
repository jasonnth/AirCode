package com.airbnb.android.utils;

import java.util.ArrayList;
import java.util.List;

public class PriceScaleUtil {
    private static final double CURVINESS = 2.5d;
    private static final int SIG_FIGS = 2;

    public enum ScaleType {
        Exponential,
        Geometric
    }

    public static List<Integer> getPriceScale(int minPrice, int maxPrice, ScaleType scaleType) {
        ArrayList<Integer> prices = new ArrayList<>(102);
        int toDomain = maxPrice - minPrice;
        long domain = transferFunction(100, scaleType);
        prices.add(Integer.valueOf(minPrice));
        for (int i = 0; i < 100; i++) {
            prices.add(Integer.valueOf(roundSmart((int) (((transferFunction(i, scaleType) * ((long) toDomain)) / domain) + ((long) minPrice)))));
        }
        prices.add(Integer.valueOf(maxPrice));
        return prices;
    }

    private static long transferFunction(int value, ScaleType scaleType) {
        switch (scaleType) {
            case Geometric:
                return transferFunctionGeometric(value);
            default:
                return transferFunctionExponential(value);
        }
    }

    private static long transferFunctionGeometric(int value) {
        return (long) Math.pow((double) value, 2.0d);
    }

    private static long transferFunctionExponential(int i) {
        return (long) Math.pow((double) i, CURVINESS);
    }

    private static int roundSmart(int i) {
        double multiplier = Math.pow(10.0d, Math.max((Math.floor(Math.log10((double) i)) + 1.0d) - 2.0d, 1.0d));
        return ((int) Math.round(((double) i) / multiplier)) * ((int) multiplier);
    }
}
