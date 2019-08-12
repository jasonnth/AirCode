package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;
import com.google.android.gms.internal.zzaqm.zza;

public class zzaqh extends zzf<zzaqm> {
    public zzaqh(Context context, Looper looper, zzb zzb, zzc zzc) {
        super(context, looper, 116, zzb, zzc, null);
    }

    public zzaqm zzGL() throws DeadObjectException {
        return (zzaqm) super.zzxD();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzcT */
    public zzaqm zzh(IBinder iBinder) {
        return zza.zzcU(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.gass.internal.IGassService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.gass.START";
    }
}
