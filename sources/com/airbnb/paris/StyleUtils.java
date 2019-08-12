package com.airbnb.paris;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.p002v7.content.res.AppCompatResources;

class StyleUtils {
    public static Drawable getDrawable(Context context, TypedArrayWrapper a, int index) {
        if (a.isNull(index)) {
            return null;
        }
        return getDrawableCompat(context, a, index);
    }

    private static Drawable getDrawableCompat(Context context, TypedArrayWrapper array, int index) {
        if (VERSION.SDK_INT >= 21) {
            return array.getDrawable(index);
        }
        int resourceId = array.getResourceId(index, -1);
        if (resourceId != -1) {
            return AppCompatResources.getDrawable(context, resourceId);
        }
        return null;
    }
}
