package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.dynamic.zzf.zza;

public final class zzcn extends zzf<zzcp> {
    private static final zzcn zzrS = new zzcn();

    private zzcn() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    public static zzco zzb(String str, Context context, boolean z) {
        if (zze.zzuY().isGooglePlayServicesAvailable(context) == 0) {
            zzco zzc = zzrS.zzc(str, context, z);
            if (zzc != null) {
                return zzc;
            }
        }
        return new zzcm(str, context, z);
    }

    private zzco zzc(String str, Context context, boolean z) {
        IBinder zzb;
        IObjectWrapper zzA = zzd.zzA(context);
        if (z) {
            try {
                zzb = ((zzcp) zzbl(context)).zza(str, zzA);
            } catch (RemoteException | zza e) {
                return null;
            }
        } else {
            zzb = ((zzcp) zzbl(context)).zzb(str, zzA);
        }
        return zzco.zza.zzd(zzb);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzb */
    public zzcp zzc(IBinder iBinder) {
        return zzcp.zza.zze(iBinder);
    }
}
