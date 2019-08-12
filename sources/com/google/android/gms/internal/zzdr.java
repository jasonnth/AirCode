package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;
import com.google.android.gms.internal.zzdv.zza;
import org.spongycastle.asn1.eac.EACTags;

@zzme
public class zzdr extends zzf<zzdv> {
    zzdr(Context context, Looper looper, zzb zzb, zzc zzc) {
        super(context, looper, EACTags.SECURITY_ENVIRONMENT_TEMPLATE, zzb, zzc, null);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    public zzdv zzeB() throws DeadObjectException {
        return (zzdv) super.zzxD();
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzg */
    public zzdv zzh(IBinder iBinder) {
        return zza.zzi(iBinder);
    }
}
