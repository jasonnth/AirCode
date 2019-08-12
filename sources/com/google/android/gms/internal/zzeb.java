package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzet.zza;

@zzme
public class zzeb extends zzf<zzeu> {
    public zzeb() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public zzet zza(Context context, zzeg zzeg, String str, zzka zzka, int i) {
        try {
            return zza.zzq(((zzeu) zzbl(context)).zza(zzd.zzA(context), zzeg, str, zzka, 10260000, i));
        } catch (RemoteException | zzf.zza e) {
            zzqf.zza("Could not create remote AdManager.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzk */
    public zzeu zzc(IBinder iBinder) {
        return zzeu.zza.zzr(iBinder);
    }
}
