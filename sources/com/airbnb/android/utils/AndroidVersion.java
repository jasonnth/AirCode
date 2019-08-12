package com.airbnb.android.utils;

import android.os.Build;
import android.os.Build.VERSION;

public final class AndroidVersion {
    public static boolean isAtLeastJellyBeanMR1() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isAtLeastJellyBeanMR2() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isAtLeastKitKat() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isAtLeastLollipop() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isAtLeastMarshmallow() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isAtLeastLollipopMR1() {
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isJellyBean() {
        return VERSION.SDK_INT == 16;
    }

    public static boolean isAtLeastNougat() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean canDeviceRunRenderscript() {
        return isAtLeastJellyBeanMR2();
    }

    public static boolean isSamsung() {
        return Build.MANUFACTURER.equals("samsung");
    }

    private AndroidVersion() {
    }
}
