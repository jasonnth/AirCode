package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.internal.zzhq.zza;

@zzme
public class zzhv extends zza {
    private final OnContentAdLoadedListener zzHz;

    public zzhv(OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzHz = onContentAdLoadedListener;
    }

    public void zza(zzhl zzhl) {
        this.zzHz.onContentAdLoaded(zzb(zzhl));
    }

    /* access modifiers changed from: 0000 */
    public zzhm zzb(zzhl zzhl) {
        return new zzhm(zzhl);
    }
}
