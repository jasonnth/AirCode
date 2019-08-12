package com.google.android.gms.internal;

import android.provider.Settings.SettingNotFoundException;
import com.google.android.gms.internal.zzag.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbi extends zzca {
    public zzbi(zzbd zzbd, String str, String str2, zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbL = Integer.valueOf(2);
        try {
            this.zzqV.zzbL = Integer.valueOf(((Boolean) this.zzre.invoke(null, new Object[]{this.zzpz.getApplicationContext()})).booleanValue() ? 1 : 0);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
