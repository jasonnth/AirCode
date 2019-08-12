package com.jumio.commons.utils;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

public class DrawableUtil {
    public static void tint(Drawable drawable, int color) {
        if ((drawable instanceof BitmapDrawable) || (drawable instanceof NinePatchDrawable)) {
            drawable.setTint(color);
        } else {
            drawable.setColorFilter(color, Mode.SRC_IN);
        }
    }
}
