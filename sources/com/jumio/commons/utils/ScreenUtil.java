package com.jumio.commons.utils;

import android.content.Context;
import android.util.TypedValue;

public class ScreenUtil {
    public static float dipToPx(Context c, float dip) {
        return TypedValue.applyDimension(1, dip, c.getResources().getDisplayMetrics());
    }

    public static float spToPx(Context c, float sp) {
        return TypedValue.applyDimension(2, sp, c.getResources().getDisplayMetrics());
    }

    public static int pxToDp(Context c, int px) {
        return (int) TypedValue.applyDimension(0, (float) px, c.getResources().getDisplayMetrics());
    }

    public static int dpToPx(Context c, int dp) {
        return (int) TypedValue.applyDimension(1, (float) dp, c.getResources().getDisplayMetrics());
    }
}
