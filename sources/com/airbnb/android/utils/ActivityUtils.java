package com.airbnb.android.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.p002v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.airbnb.p027n2.utils.BitmapUtils;

public class ActivityUtils {
    private static final String TAG = ActivityUtils.class.getSimpleName();

    public static void startActivityWithScaleUpView(Context context, Intent intent, View v) {
        context.startActivity(intent, ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle());
    }

    public static void startActivityWithScaleUpView(Context context, Intent intent, View v, String imageUrl) {
        ActivityOptions options = getOptionsForScaleUpAnimation(context, v, imageUrl);
        if (options == null) {
            startActivityWithScaleUpView(context, intent, v);
        } else {
            context.startActivity(intent, options.toBundle());
        }
    }

    public static boolean hasActivityStopped(Activity activity) {
        return activity.getWindow() == null || activity.isFinishing();
    }

    private static ActivityOptions getOptionsForScaleUpAnimation(Context context, View v, String imageUrl) {
        Bitmap image = BitmapUtils.getBitmapFromCacheOnly(context, imageUrl);
        if (image != null) {
            return ActivityOptions.makeThumbnailScaleUpAnimation(v, image, 0, 0);
        }
        Log.v(TAG, "Cached image bitmap not found. Falling back to standard scale up animation.");
        return null;
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rectangle = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        if (rectangle.top > 0) {
            return rectangle.top;
        }
        return ViewUtils.getAndroidDimension(activity, "status_bar_height");
    }

    public static int getStatusBarActionBarHeight(AppCompatActivity activity) {
        return getStatusBarHeight(activity) + activity.getSupportActionBar().getHeight();
    }

    public static boolean hasTranslucentStatusBar(Window window) {
        if (!AndroidVersion.isAtLeastKitKat() || (window.getAttributes().flags & 67108864) != 67108864) {
            return false;
        }
        return true;
    }

    public static int getNavBarHeight(Context context) {
        if (isPortraitMode(context)) {
            return ViewUtils.getAndroidDimension(context, "navigation_bar_height");
        }
        return ViewUtils.getAndroidDimension(context, "navigation_bar_height_landscape");
    }

    public static boolean isPortraitMode(Context context) {
        Point point = ViewUtils.getScreenSize(context);
        return point.x < point.y;
    }

    public static boolean isLandscapeMode(Context context) {
        return !isPortraitMode(context);
    }
}
