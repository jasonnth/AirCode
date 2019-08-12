package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.internal.zzhp.zza;

@zzme
public class zzhu extends zza {
    private final OnAppInstallAdLoadedListener zzHy;

    public zzhu(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzHy = onAppInstallAdLoadedListener;
    }

    public void zza(zzhj zzhj) {
        this.zzHy.onAppInstallAdLoaded(zzb(zzhj));
    }

    /* access modifiers changed from: 0000 */
    public zzhk zzb(zzhj zzhj) {
        return new zzhk(zzhj);
    }
}
