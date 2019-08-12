package com.airbnb.p027n2.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p002v7.content.res.AppCompatResources;

/* renamed from: com.airbnb.n2.utils.ColorizedDrawable */
public final class ColorizedDrawable {
    public static Drawable forIdStateList(Context context, int drawableId, int colorStateListId) {
        Drawable drawable = DrawableCompat.wrap(AppCompatResources.getDrawable(context, drawableId));
        DrawableCompat.setTintList(drawable, ContextCompat.getColorStateList(context, colorStateListId));
        DrawableCompat.setTintMode(drawable, Mode.SRC_IN);
        return drawable;
    }

    public static Drawable forIdWithColors(Context context, int blackDrawableId, int baseId, int disabledId, int pressedId, int selectedId) {
        Drawable drawable = DrawableCompat.wrap(AppCompatResources.getDrawable(context, blackDrawableId));
        int baseColor = ContextCompat.getColor(context, baseId);
        DrawableCompat.setTintList(drawable, new ColorStateList(new int[][]{new int[]{-16842910}, new int[]{16842919}, new int[]{16842913}, new int[]{16842910}}, new int[]{ContextCompat.getColor(context, disabledId), ContextCompat.getColor(context, pressedId), ContextCompat.getColor(context, selectedId), baseColor}));
        DrawableCompat.setTintMode(drawable, Mode.SRC_IN);
        return drawable;
    }

    public static Drawable forIdWithColor(Context context, int drawableId, int colorId) {
        return forDrawableWithColor(context, AppCompatResources.getDrawable(context, drawableId), colorId);
    }

    public static Drawable forDrawableWithColor(Context context, Drawable drawable, int colorId) {
        return mutateDrawableWithColor(drawable, ContextCompat.getColor(context, colorId));
    }

    public static Drawable mutateDrawableWithColor(Drawable drawable, int color) {
        addPaddingToDrawableIfNecessary(drawable);
        Drawable dst = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(dst, color);
        DrawableCompat.setTintMode(dst, Mode.SRC_IN);
        return dst;
    }

    private static void addPaddingToDrawableIfNecessary(Drawable drawable) {
        if (!ViewLibUtils.isAtLeastKitKat()) {
            if (drawable instanceof ShapeDrawable) {
                Rect rect = new Rect();
                ShapeDrawable shapeDrawable = (ShapeDrawable) drawable;
                shapeDrawable.getPadding(rect);
                shapeDrawable.setPadding(rect);
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                    addPaddingToDrawableIfNecessary(layerDrawable.getDrawable(i));
                }
            }
        }
    }
}
