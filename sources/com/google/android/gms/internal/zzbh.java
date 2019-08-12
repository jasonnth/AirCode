package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbh extends zzca {
    public zzbh(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzqV) {
            this.zzqV.zzbc = Long.valueOf(-1);
            this.zzqV.zzbc = (Long) this.zzre.invoke(null, new Object[]{this.zzpz.getContext()});
        }
    }
}
