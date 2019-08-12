package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbl extends zzca {
    public zzbl(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbe = Long.valueOf(-1);
        this.zzqV.zzbf = Long.valueOf(-1);
        int[] iArr = (int[]) this.zzre.invoke(null, new Object[]{this.zzpz.getContext()});
        synchronized (this.zzqV) {
            this.zzqV.zzbe = Long.valueOf((long) iArr[0]);
            this.zzqV.zzbf = Long.valueOf((long) iArr[1]);
        }
    }
}
