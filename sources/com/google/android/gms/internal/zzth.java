package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzac;

public final class zzth {
    static Boolean zzabx;
    static Object zztX = new Object();

    public static boolean zzak(Context context) {
        zzac.zzw(context);
        if (zzabx != null) {
            return zzabx.booleanValue();
        }
        boolean zza = zztm.zza(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzabx = Boolean.valueOf(zza);
        return zza;
    }
}
