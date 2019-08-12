package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzme
public final class zzkn<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final zzkc zzLE;

    public zzkn(zzkc zzkc) {
        this.zzLE = zzkc;
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, final ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzqf.zzbf(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onFailedToReceiveAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzqf.zzbf(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onFailedToReceiveAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }
}
