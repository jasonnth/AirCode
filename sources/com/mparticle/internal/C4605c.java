package com.mparticle.internal;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.StatFs;

@TargetApi(18)
/* renamed from: com.mparticle.internal.c */
public class C4605c {
    /* renamed from: a */
    public static long m2231a(StatFs statFs) {
        try {
            if (VERSION.SDK_INT > 17) {
                return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
