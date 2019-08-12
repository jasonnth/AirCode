package com.appboy.p028ui.support;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: com.appboy.ui.support.ViewUtils */
public class ViewUtils {
    private static int sDisplayHeight;

    public static void removeViewFromParent(View view) {
        if (view != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    public static int getTopVisibleCoordinate(View view) {
        Rect rectangle = new Rect();
        view.getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    public static int getDisplayHeight(Activity activity) {
        if (sDisplayHeight > 0) {
            return sDisplayHeight;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT <= 13) {
            sDisplayHeight = display.getHeight();
            return sDisplayHeight;
        }
        Point point = new Point();
        display.getSize(point);
        sDisplayHeight = point.y;
        return sDisplayHeight;
    }

    public static double convertDpToPixels(Activity activity, double valueInDp) {
        return valueInDp * ((double) activity.getResources().getDisplayMetrics().density);
    }

    public static boolean isRunningOnTablet(Activity activity) {
        if (VERSION.SDK_INT >= 13) {
            if (activity.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
                return true;
            }
            return false;
        } else if ((activity.getResources().getConfiguration().screenLayout & 15) < 4) {
            return false;
        } else {
            return true;
        }
    }

    @TargetApi(16)
    public static void removeOnGlobalLayoutListenerSafe(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }
}
