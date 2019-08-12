package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbx extends zzca {
    public zzbx(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbK = Integer.valueOf(2);
        boolean booleanValue = ((Boolean) this.zzre.invoke(null, new Object[]{this.zzpz.getApplicationContext()})).booleanValue();
        synchronized (this.zzqV) {
            if (booleanValue) {
                this.zzqV.zzbK = Integer.valueOf(1);
            } else {
                this.zzqV.zzbK = Integer.valueOf(0);
            }
        }
    }
}
