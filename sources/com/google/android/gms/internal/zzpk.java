package com.google.android.gms.internal;

import android.util.Log;

@zzme
public final class zzpk extends zzqf {
    /* renamed from: v */
    public static void m1279v(String str) {
        if (zzkI()) {
            Log.v("Ads", str);
        }
    }

    public static boolean zzkH() {
        return ((Boolean) zzgd.zzDr.get()).booleanValue();
    }

    public static boolean zzkI() {
        return zzak(2) && zzkH();
    }
}
