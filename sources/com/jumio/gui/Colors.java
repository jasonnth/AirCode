package com.jumio.gui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

public class Colors {
    public static final int DIALOG_NEGATIVE_ACTION_COLOR = -10066330;
    public static final int DIALOG_POSITIVE_ACTION_COLOR = -6832627;

    public static int parseColor(Context context, int colorId, int fallbackColor) {
        int i = fallbackColor;
        TypedArray values = null;
        try {
            values = context.getTheme().obtainStyledAttributes(new int[]{colorId});
            return values.getColor(0, fallbackColor);
        } finally {
            if (values != null) {
                values.recycle();
            }
        }
    }

    public static ColorStateList parseColorStateList(Context context, int colorId, int fallbackColor) {
        TypedArray values = context.getTheme().obtainStyledAttributes(new int[]{colorId});
        ColorStateList colorStateList = values.getColorStateList(0);
        if (colorStateList == null) {
            int color = values.getColor(0, 0);
            if (color == 0) {
                color = fallbackColor;
            }
            colorStateList = ColorStateList.valueOf(color);
        }
        values.recycle();
        return colorStateList;
    }

    public static ColorStateList constructList(int enabled, int disabled, int pressed) {
        return new ColorStateList(new int[][]{new int[]{16842910}, new int[]{-16842910}, new int[]{16842919}, new int[0]}, new int[]{enabled, disabled, pressed, enabled});
    }
}
