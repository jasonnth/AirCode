package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.dynamic.zzf.zza;

public final class zzaj extends zzf<zzy> {
    private static final zzaj zzaGI = new zzaj();

    private zzaj() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzd(Context context, int i, int i2) throws zza {
        return zzaGI.zze(context, i, i2);
    }

    private View zze(Context context, int i, int i2) throws zza {
        try {
            zzah zzah = new zzah(i, i2, null);
            return (View) zzd.zzF(((zzy) zzbl(context)).zza(zzd.zzA(context), zzah));
        } catch (Exception e) {
            throw new zza("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    /* renamed from: zzby */
    public zzy zzc(IBinder iBinder) {
        return zzy.zza.zzbx(iBinder);
    }
}
