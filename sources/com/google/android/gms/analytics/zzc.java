package com.google.android.gms.analytics;

import android.support.p000v4.app.NotificationCompat;
import com.google.android.gms.internal.zztc;

public final class zzc {
    public static String zzao(int i) {
        return zzc("cd", i);
    }

    public static String zzaq(int i) {
        return zzc("cm", i);
    }

    public static String zzar(int i) {
        return zzc("&pr", i);
    }

    public static String zzas(int i) {
        return zzc("pr", i);
    }

    public static String zzat(int i) {
        return zzc("&promo", i);
    }

    public static String zzau(int i) {
        return zzc(NotificationCompat.CATEGORY_PROMO, i);
    }

    public static String zzav(int i) {
        return zzc("pi", i);
    }

    public static String zzaw(int i) {
        return zzc("&il", i);
    }

    public static String zzax(int i) {
        return zzc("il", i);
    }

    private static String zzc(String str, int i) {
        if (i >= 1) {
            return new StringBuilder(String.valueOf(str).length() + 11).append(str).append(i).toString();
        }
        zztc.zzf("index out of range for prefix", str);
        return "";
    }
}
