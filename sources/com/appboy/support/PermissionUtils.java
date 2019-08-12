package com.appboy.support;

import android.content.Context;

public class PermissionUtils {

    /* renamed from: a */
    private static final String f2915a = AppboyLogger.getAppboyLogTag(PermissionUtils.class);

    public static boolean hasPermission(Context context, String permission) {
        try {
            return context.checkCallingOrSelfPermission(permission) == 0;
        } catch (Throwable th) {
            AppboyLogger.m1736e(f2915a, String.format("Failure checking permission %s", new Object[]{permission}), th);
            return false;
        }
    }
}
