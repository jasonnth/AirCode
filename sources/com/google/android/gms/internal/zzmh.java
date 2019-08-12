package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzj;

@zzme
public final class zzmh {

    public interface zza {
        void zza(zzmn zzmn);
    }

    interface zzb {
        boolean zza(zzqh zzqh);
    }

    public static zzpq zza(final Context context, zzqh zzqh, zzqp<zzmk> zzqp, zza zza2) {
        return zza(context, zzqh, zzqp, zza2, new zzb() {
            public boolean zza(zzqh zzqh) {
                return zzqh.zzYY || (zzj.zzbb(context) && !((Boolean) zzgd.zzBU.get()).booleanValue());
            }
        });
    }

    static zzpq zza(Context context, zzqh zzqh, zzqp<zzmk> zzqp, zza zza2, zzb zzb2) {
        return zzb2.zza(zzqh) ? zza(context, zzqp, zza2) : zzb(context, zzqh, zzqp, zza2);
    }

    private static zzpq zza(Context context, zzqp<zzmk> zzqp, zza zza2) {
        zzpk.zzbf("Fetching ad response from local ad request service.");
        com.google.android.gms.internal.zzmi.zza zza3 = new com.google.android.gms.internal.zzmi.zza(context, zzqp, zza2);
        zza3.zziP();
        return zza3;
    }

    private static zzpq zzb(Context context, zzqh zzqh, zzqp<zzmk> zzqp, zza zza2) {
        zzpk.zzbf("Fetching ad response from remote ad request service.");
        if (zzel.zzeT().zzaf(context)) {
            return new com.google.android.gms.internal.zzmi.zzb(context, zzqh, zzqp, zza2);
        }
        zzpk.zzbh("Failed to connect to remote ad request service.");
        return null;
    }
}
