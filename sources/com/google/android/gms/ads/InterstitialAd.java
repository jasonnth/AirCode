package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzfg;

public final class InterstitialAd {
    private final zzfg zzrH;

    public InterstitialAd(Context context) {
        this.zzrH = new zzfg(context);
    }

    public void loadAd(AdRequest adRequest) {
        this.zzrH.zza(adRequest.zzbp());
    }

    public void setAdListener(AdListener adListener) {
        this.zzrH.setAdListener(adListener);
        if (adListener != null && (adListener instanceof zzdx)) {
            this.zzrH.zza((zzdx) adListener);
        } else if (adListener == null) {
            this.zzrH.zza((zzdx) null);
        }
    }

    public void setAdUnitId(String str) {
        this.zzrH.setAdUnitId(str);
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzrH.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public void show() {
        this.zzrH.show();
    }

    public void zzd(boolean z) {
        this.zzrH.zzd(z);
    }
}
