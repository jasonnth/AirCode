package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbw extends zzca {
    private static final Object zzqS = new Object();
    private static volatile Long zzra = null;

    public zzbw(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (zzra == null) {
            synchronized (zzqS) {
                if (zzra == null) {
                    zzra = (Long) this.zzre.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbz = zzra;
        }
    }
}
