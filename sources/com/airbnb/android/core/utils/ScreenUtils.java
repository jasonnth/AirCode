package com.airbnb.android.core.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class ScreenUtils {
    public static Point getScreenSize(Context context) {
        Point screenSize = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }
}
