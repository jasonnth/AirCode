package com.airbnb.p027n2.utils;

import android.content.Context;
import android.content.res.TypedArray;

/* renamed from: com.airbnb.n2.utils.ThemeUtils */
public final class ThemeUtils {
    public static int resolveDimension(Context context, int attr, int defValue) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getDimensionPixelSize(0, defValue);
        } finally {
            a.recycle();
        }
    }
}
