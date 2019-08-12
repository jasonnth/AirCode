package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@zzme
public final class zznj {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, zza> zzVb = new WeakHashMap<>();

    private class zza {
        public final long zzVd = zzw.zzcS().currentTimeMillis();
        public final zzni zzVe;

        public zza(zznj zznj, zzni zzni) {
            this.zzVe = zzni;
        }

        public boolean hasExpired() {
            return ((Long) zzgd.zzDw.get()).longValue() + this.zzVd < zzw.zzcS().currentTimeMillis();
        }
    }

    public Future<zzni> zzA(final Context context) {
        return zzpn.zza((Callable<T>) new Callable<zzni>() {
            /* renamed from: zzjD */
            public zzni call() {
                zza zza = (zza) zznj.this.zzVb.get(context);
                zzni zzjC = (zza == null || zza.hasExpired() || !((Boolean) zzgd.zzDv.get()).booleanValue()) ? new com.google.android.gms.internal.zzni.zza(context).zzjC() : new com.google.android.gms.internal.zzni.zza(context, zza.zzVe).zzjC();
                zznj.this.zzVb.put(context, new zza(zznj.this, zzjC));
                return zzjC;
            }
        });
    }
}
