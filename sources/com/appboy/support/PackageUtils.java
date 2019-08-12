package com.appboy.support;

import android.content.Context;

public class PackageUtils {

    /* renamed from: a */
    private static final String f2913a = AppboyLogger.getAppboyLogTag(PackageUtils.class);

    /* renamed from: b */
    private static String f2914b;

    public static String getResourcePackageName(Context context) {
        if (f2914b != null) {
            return f2914b;
        }
        f2914b = context.getPackageName();
        return f2914b;
    }
}
