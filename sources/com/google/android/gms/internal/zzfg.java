package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

@zzme
public class zzfg {
    private final Context mContext;
    private final zzjz zzAj;
    private Correlator zzAn;
    private zzet zzAo;
    private InAppPurchaseListener zzAp;
    private OnCustomRenderedAdLoadedListener zzAq;
    private PlayStorePurchaseListener zzAr;
    private String zzAs;
    private PublisherInterstitialAd zzAw;
    private boolean zzAx;
    private RewardedVideoAdListener zzcJ;
    private final zzef zzrw;
    private String zzts;
    private zzdx zzyR;
    private AdListener zzyS;
    private AppEventListener zzzE;

    public zzfg(Context context) {
        this(context, zzef.zzeD(), null);
    }

    public zzfg(Context context, zzef zzef, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzAj = new zzjz();
        this.mContext = context;
        this.zzrw = zzef;
        this.zzAw = publisherInterstitialAd;
    }

    private void zzS(String str) throws RemoteException {
        if (this.zzts == null) {
            zzT(str);
        }
        this.zzAo = zzel.zzeU().zzb(this.mContext, this.zzAx ? zzeg.zzeE() : new zzeg(), this.zzts, this.zzAj);
        if (this.zzyS != null) {
            this.zzAo.zza((zzep) new zzdz(this.zzyS));
        }
        if (this.zzyR != null) {
            this.zzAo.zza((zzeo) new zzdy(this.zzyR));
        }
        if (this.zzzE != null) {
            this.zzAo.zza((zzev) new zzei(this.zzzE));
        }
        if (this.zzAp != null) {
            this.zzAo.zza((zzle) new zzlj(this.zzAp));
        }
        if (this.zzAr != null) {
            this.zzAo.zza(new zzln(this.zzAr), this.zzAs);
        }
        if (this.zzAq != null) {
            this.zzAo.zza((zzgp) new zzgq(this.zzAq));
        }
        if (this.zzAn != null) {
            this.zzAo.zza((zzex) this.zzAn.zzbq());
        }
        if (this.zzcJ != null) {
            this.zzAo.zza((zznw) new zznz(this.zzcJ));
        }
    }

    private void zzT(String str) {
        if (this.zzAo == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(str).append(" is called.").toString());
        }
    }

    public void setAdListener(AdListener adListener) {
        try {
            this.zzyS = adListener;
            if (this.zzAo != null) {
                this.zzAo.zza((zzep) adListener != null ? new zzdz(adListener) : null);
            }
        } catch (RemoteException e) {
            zzqf.zzc("Failed to set the AdListener.", e);
        }
    }

    public void setAdUnitId(String str) {
        if (this.zzts != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzts = str;
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzcJ = rewardedVideoAdListener;
            if (this.zzAo != null) {
                this.zzAo.zza((zznw) rewardedVideoAdListener != null ? new zznz(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzqf.zzc("Failed to set the AdListener.", e);
        }
    }

    public void show() {
        try {
            zzT("show");
            this.zzAo.showInterstitial();
        } catch (RemoteException e) {
            zzqf.zzc("Failed to show interstitial.", e);
        }
    }

    public void zza(zzdx zzdx) {
        try {
            this.zzyR = zzdx;
            if (this.zzAo != null) {
                this.zzAo.zza((zzeo) zzdx != null ? new zzdy(zzdx) : null);
            }
        } catch (RemoteException e) {
            zzqf.zzc("Failed to set the AdClickListener.", e);
        }
    }

    public void zza(zzfe zzfe) {
        try {
            if (this.zzAo == null) {
                zzS("loadAd");
            }
            if (this.zzAo.zzb(this.zzrw.zza(this.mContext, zzfe))) {
                this.zzAj.zzi(zzfe.zzfd());
            }
        } catch (RemoteException e) {
            zzqf.zzc("Failed to load ad.", e);
        }
    }

    public void zzd(boolean z) {
        this.zzAx = z;
    }
}
