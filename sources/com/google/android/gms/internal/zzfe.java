package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zzme
public final class zzfe {
    private final Bundle zzAa;
    private final Map<Class<? extends Object>, Object> zzAb;
    private final SearchAdRequest zzAc;
    private final Set<String> zzAd;
    private final Set<String> zzAe;
    private final Date zzcR;
    private final Set<String> zzcT;
    private final Location zzcV;
    private final boolean zzsS;
    private final int zzzk;
    private final int zzzn;
    private final String zzzo;
    private final String zzzq;
    private final Bundle zzzs;
    private final String zzzu;
    private final boolean zzzw;

    public static final class zza {
        /* access modifiers changed from: private */
        public final Bundle zzAa = new Bundle();
        /* access modifiers changed from: private */
        public final HashSet<String> zzAf = new HashSet<>();
        /* access modifiers changed from: private */
        public final HashMap<Class<? extends Object>, Object> zzAg = new HashMap<>();
        /* access modifiers changed from: private */
        public final HashSet<String> zzAh = new HashSet<>();
        /* access modifiers changed from: private */
        public final HashSet<String> zzAi = new HashSet<>();
        /* access modifiers changed from: private */
        public Date zzcR;
        /* access modifiers changed from: private */
        public Location zzcV;
        /* access modifiers changed from: private */
        public boolean zzsS = false;
        /* access modifiers changed from: private */
        public int zzzk = -1;
        /* access modifiers changed from: private */
        public int zzzn = -1;
        /* access modifiers changed from: private */
        public String zzzo;
        /* access modifiers changed from: private */
        public String zzzq;
        /* access modifiers changed from: private */
        public final Bundle zzzs = new Bundle();
        /* access modifiers changed from: private */
        public String zzzu;
        /* access modifiers changed from: private */
        public boolean zzzw;

        public void zzL(String str) {
            this.zzAf.add(str);
        }

        public void zzM(String str) {
            this.zzAh.add(str);
        }

        public void zzN(String str) {
            this.zzAh.remove(str);
        }

        public void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzAa.putBundle(cls.getName(), bundle);
        }

        public void zza(Date date) {
            this.zzcR = date;
        }

        public void zzb(Location location) {
            this.zzcV = location;
        }

        public void zzn(boolean z) {
            this.zzzn = z ? 1 : 0;
        }

        public void zzo(boolean z) {
            this.zzzw = z;
        }

        public void zzy(int i) {
            this.zzzk = i;
        }
    }

    public zzfe(zza zza2) {
        this(zza2, null);
    }

    public zzfe(zza zza2, SearchAdRequest searchAdRequest) {
        this.zzcR = zza2.zzcR;
        this.zzzq = zza2.zzzq;
        this.zzzk = zza2.zzzk;
        this.zzcT = Collections.unmodifiableSet(zza2.zzAf);
        this.zzcV = zza2.zzcV;
        this.zzsS = zza2.zzsS;
        this.zzAa = zza2.zzAa;
        this.zzAb = Collections.unmodifiableMap(zza2.zzAg);
        this.zzzo = zza2.zzzo;
        this.zzzu = zza2.zzzu;
        this.zzAc = searchAdRequest;
        this.zzzn = zza2.zzzn;
        this.zzAd = Collections.unmodifiableSet(zza2.zzAh);
        this.zzzs = zza2.zzzs;
        this.zzAe = Collections.unmodifiableSet(zza2.zzAi);
        this.zzzw = zza2.zzzw;
    }

    public Date getBirthday() {
        return this.zzcR;
    }

    public String getContentUrl() {
        return this.zzzq;
    }

    public Bundle getCustomTargeting() {
        return this.zzzs;
    }

    public int getGender() {
        return this.zzzk;
    }

    public Set<String> getKeywords() {
        return this.zzcT;
    }

    public Location getLocation() {
        return this.zzcV;
    }

    public boolean getManualImpressionsEnabled() {
        return this.zzsS;
    }

    public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzAa.getBundle(cls.getName());
    }

    public String getPublisherProvidedId() {
        return this.zzzo;
    }

    public boolean isDesignedForFamilies() {
        return this.zzzw;
    }

    public boolean isTestDevice(Context context) {
        return this.zzAd.contains(zzel.zzeT().zzad(context));
    }

    public String zzfb() {
        return this.zzzu;
    }

    public SearchAdRequest zzfc() {
        return this.zzAc;
    }

    public Map<Class<? extends Object>, Object> zzfd() {
        return this.zzAb;
    }

    public Bundle zzfe() {
        return this.zzAa;
    }

    public int zzff() {
        return this.zzzn;
    }

    public Set<String> zzfg() {
        return this.zzAe;
    }
}
