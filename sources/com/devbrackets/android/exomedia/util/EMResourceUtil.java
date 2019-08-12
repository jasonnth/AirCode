package com.devbrackets.android.exomedia.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.p000v4.graphics.drawable.DrawableCompat;

public class EMResourceUtil {
    public static Drawable tintList(Context context, int drawableRes, int tintListRes) {
        return tintList(context, getDrawable(context, drawableRes).mutate(), tintListRes);
    }

    public static Drawable tintList(Context context, Drawable drawable, int tintListRes) {
        Drawable drawable2 = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawable2, getColorStateList(context, tintListRes));
        return drawable2;
    }

    public static Drawable getDrawable(Context context, int drawableResourceId) {
        if (VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(drawableResourceId, context.getTheme());
        }
        return context.getResources().getDrawable(drawableResourceId);
    }

    public static ColorStateList getColorStateList(Context context, int colorRes) {
        if (VERSION.SDK_INT >= 23) {
            return context.getResources().getColorStateList(colorRes, context.getTheme());
        }
        return context.getResources().getColorStateList(colorRes);
    }
}
