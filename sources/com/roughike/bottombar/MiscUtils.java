package com.roughike.bottombar;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

class MiscUtils {
    protected static int getColor(Context context, int color) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(color, tv, true);
        return tv.data;
    }

    protected static int dpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        try {
            return (int) ((((float) metrics.densityDpi) / 160.0f) * dp);
        } catch (NoSuchFieldError e) {
            return (int) TypedValue.applyDimension(1, dp, metrics);
        }
    }

    protected static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }
}
