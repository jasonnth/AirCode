package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzfe;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzqf;

public class AdLoader {
    private final Context mContext;
    private final zzef zzrw;
    private final zzeq zzrx;

    public static class Builder {
        private final Context mContext;
        private final zzer zzry;

        Builder(Context context, zzer zzer) {
            this.mContext = context;
            this.zzry = zzer;
        }

        public Builder(Context context, String str) {
            this((Context) zzac.zzb(context, (Object) "context cannot be null"), zzel.zzeU().zzb(context, str, new zzjz()));
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzry.zzck());
            } catch (RemoteException e) {
                zzqf.zzb("Failed to build AdLoader.", e);
                return null;
            }
        }

        public Builder forAppInstallAd(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzry.zza((zzhp) new zzhu(onAppInstallAdLoadedListener));
            } catch (RemoteException e) {
                zzqf.zzc("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forContentAd(OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzry.zza((zzhq) new zzhv(onContentAdLoadedListener));
            } catch (RemoteException e) {
                zzqf.zzc("Failed to add content ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzry.zzb((zzep) new zzdz(adListener));
            } catch (RemoteException e) {
                zzqf.zzc("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzry.zza(new zzhc(nativeAdOptions));
            } catch (RemoteException e) {
                zzqf.zzc("Failed to specify native ad options", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzeq zzeq) {
        this(context, zzeq, zzef.zzeD());
    }

    AdLoader(Context context, zzeq zzeq, zzef zzef) {
        this.mContext = context;
        this.zzrx = zzeq;
        this.zzrw = zzef;
    }

    private void zza(zzfe zzfe) {
        try {
            this.zzrx.zzf(this.zzrw.zza(this.mContext, zzfe));
        } catch (RemoteException e) {
            zzqf.zzb("Failed to load ad.", e);
        }
    }

    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzbp());
    }
}
