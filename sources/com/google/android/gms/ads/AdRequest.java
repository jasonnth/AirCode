package com.google.android.gms.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.internal.zzfe;
import com.google.android.gms.internal.zzfe.zza;
import java.util.Date;

public final class AdRequest {
    private final zzfe zzrz;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final zza zzrA = new zza();

        public Builder() {
            this.zzrA.zzM("B3EEABB8EE11C2BE770B684D95219ECB");
        }

        public Builder addKeyword(String str) {
            this.zzrA.zzL(str);
            return this;
        }

        public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzrA.zza(cls, bundle);
            if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zzrA.zzN("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }

        public Builder addTestDevice(String str) {
            this.zzrA.zzM(str);
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this);
        }

        public Builder setBirthday(Date date) {
            this.zzrA.zza(date);
            return this;
        }

        public Builder setGender(int i) {
            this.zzrA.zzy(i);
            return this;
        }

        public Builder setIsDesignedForFamilies(boolean z) {
            this.zzrA.zzo(z);
            return this;
        }

        public Builder setLocation(Location location) {
            this.zzrA.zzb(location);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean z) {
            this.zzrA.zzn(z);
            return this;
        }
    }

    private AdRequest(Builder builder) {
        this.zzrz = new zzfe(builder.zzrA);
    }

    public zzfe zzbp() {
        return this.zzrz;
    }
}
