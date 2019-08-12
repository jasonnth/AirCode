package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import java.text.NumberFormat;

public class PercentageUtils {
    public static double discountIntToDiscountedDouble(int discountPercentage) {
        return ((double) (100 - discountPercentage)) / 100.0d;
    }

    public static int discountedDoubleToDiscountInt(double discountedDecimal) {
        return 100 - ((int) Math.round(100.0d * discountedDecimal));
    }

    public static String formatDiscountPercentage(int discountPercentage) {
        return localizePercentage(discountPercentage);
    }

    public static String formatDiscountedDouble(double discountedDecimal) {
        return formatDiscountPercentage(discountedDoubleToDiscountInt(discountedDecimal));
    }

    public static String localizePercentage(int percentage) {
        return NumberFormat.getPercentInstance().format((double) (((float) percentage) / 100.0f));
    }

    public static boolean isPercentSignPrefixed(Context context) {
        String percentChar = context.getString(C0716R.string.percent_sign);
        String discountString = NumberFormat.getPercentInstance().format(0);
        return discountString.startsWith(percentChar) && discountString.contains(percentChar);
    }
}
