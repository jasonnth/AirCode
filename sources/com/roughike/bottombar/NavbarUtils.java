package com.roughike.bottombar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.view.WindowManager;

final class NavbarUtils {
    static int getNavbarHeight(Context context) {
        Resources res = context.getResources();
        int navBarIdentifier = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (navBarIdentifier > 0) {
            return res.getDimensionPixelSize(navBarIdentifier);
        }
        return 0;
    }

    static boolean shouldDrawBehindNavbar(Context context) {
        return isPortrait(context) && hasSoftKeys(context);
    }

    private static boolean isPortrait(Context context) {
        return context.getResources().getBoolean(R.bool.bb_bottom_bar_is_portrait_mode);
    }

    private static boolean hasSoftKeys(Context context) {
        if (VERSION.SDK_INT >= 17) {
            Display d = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);
            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);
            int displayHeight = displayMetrics.heightPixels;
            if (realWidth - displayMetrics.widthPixels > 0 || realHeight - displayHeight > 0) {
                return true;
            }
            return false;
        } else if (VERSION.SDK_INT < 14) {
            return true;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey() && !KeyCharacterMap.deviceHasKey(4);
        }
    }
}
