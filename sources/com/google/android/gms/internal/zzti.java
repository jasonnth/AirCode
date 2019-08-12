package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzac;

public final class zzti {
    private static Boolean zzaby;

    public static boolean zzal(Context context) {
        zzac.zzw(context);
        if (zzaby != null) {
            return zzaby.booleanValue();
        }
        boolean zzy = zztm.zzy(context, "com.google.android.gms.analytics.AnalyticsService");
        zzaby = Boolean.valueOf(zzy);
        return zzy;
    }
}
