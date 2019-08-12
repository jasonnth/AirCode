package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.google.android.gms.dynamic.zzd;
import com.jumio.p311nv.data.NVStrings;

@zzme
public class zzek {
    private final Object zzrJ = new Object();
    private zzew zzzG;
    /* access modifiers changed from: private */
    public final zzeb zzzH;
    /* access modifiers changed from: private */
    public final zzea zzzI;
    private final zzfj zzzJ;
    private final zzht zzzK;
    private final zzny zzzL;
    /* access modifiers changed from: private */
    public final zzlk zzzM;
    /* access modifiers changed from: private */
    public final zzky zzzN;

    abstract class zza<T> {
        zza() {
        }

        /* access modifiers changed from: protected */
        public abstract T zzb(zzew zzew) throws RemoteException;

        /* access modifiers changed from: protected */
        public abstract T zzeJ() throws RemoteException;

        /* access modifiers changed from: protected */
        public final T zzeQ() {
            T t = null;
            zzew zza = zzek.this.zzeH();
            if (zza == null) {
                zzqf.zzbh("ClientApi class cannot be loaded.");
                return t;
            }
            try {
                return zzb(zza);
            } catch (RemoteException e) {
                zzqf.zzc("Cannot invoke local loader using ClientApi class", e);
                return t;
            }
        }

        /* access modifiers changed from: protected */
        public final T zzeR() {
            try {
                return zzeJ();
            } catch (RemoteException e) {
                zzqf.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzek(zzeb zzeb, zzea zzea, zzfj zzfj, zzht zzht, zzny zzny, zzlk zzlk, zzky zzky) {
        this.zzzH = zzeb;
        this.zzzI = zzea;
        this.zzzJ = zzfj;
        this.zzzK = zzht;
        this.zzzL = zzny;
        this.zzzM = zzlk;
        this.zzzN = zzky;
    }

    private static boolean zza(Activity activity, String str) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            return intent.getBooleanExtra(str, false);
        }
        zzqf.m1280e("useClientJar flag not found in activity intent extras.");
        return false;
    }

    /* access modifiers changed from: private */
    public void zzc(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString(ManageListingAnalytics.FLOW, str);
        zzel.zzeT().zza(context, (String) null, "gmob-apps", bundle, true);
    }

    private static zzew zzeG() {
        try {
            Object newInstance = zzek.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return com.google.android.gms.internal.zzew.zza.asInterface((IBinder) newInstance);
            }
            zzqf.zzbh("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e) {
            zzqf.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public zzew zzeH() {
        zzew zzew;
        synchronized (this.zzrJ) {
            if (this.zzzG == null) {
                this.zzzG = zzeG();
            }
            zzew = this.zzzG;
        }
        return zzew;
    }

    public zzet zza(final Context context, final zzeg zzeg, final String str) {
        return (zzet) zza(context, false, (zza<T>) new zza<zzet>() {
            /* renamed from: zza */
            public zzet zzb(zzew zzew) throws RemoteException {
                return zzew.createSearchAdManager(zzd.zzA(context), zzeg, str, 10260000);
            }

            /* renamed from: zzeI */
            public zzet zzeJ() {
                zzet zza = zzek.this.zzzH.zza(context, zzeg, str, null, 3);
                if (zza != null) {
                    return zza;
                }
                zzek.this.zzc(context, NVStrings.SEARCH);
                return new zzfl();
            }
        });
    }

    public zzet zza(Context context, zzeg zzeg, String str, zzka zzka) {
        final Context context2 = context;
        final zzeg zzeg2 = zzeg;
        final String str2 = str;
        final zzka zzka2 = zzka;
        return (zzet) zza(context, false, (zza<T>) new zza<zzet>() {
            /* renamed from: zza */
            public zzet zzb(zzew zzew) throws RemoteException {
                return zzew.createBannerAdManager(zzd.zzA(context2), zzeg2, str2, zzka2, 10260000);
            }

            /* renamed from: zzeI */
            public zzet zzeJ() {
                zzet zza = zzek.this.zzzH.zza(context2, zzeg2, str2, zzka2, 1);
                if (zza != null) {
                    return zza;
                }
                zzek.this.zzc(context2, "banner");
                return new zzfl();
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public <T> T zza(Context context, boolean z, zza<T> zza2) {
        if (!z && !zzel.zzeT().zzaf(context)) {
            zzqf.zzbf("Google Play Services is not available");
            z = true;
        }
        if (z) {
            T zzeQ = zza2.zzeQ();
            return zzeQ == null ? zza2.zzeR() : zzeQ;
        }
        T zzeR = zza2.zzeR();
        return zzeR == null ? zza2.zzeQ() : zzeR;
    }

    public zzer zzb(final Context context, final String str, final zzka zzka) {
        return (zzer) zza(context, false, (zza<T>) new zza<zzer>() {
            /* renamed from: zzc */
            public zzer zzb(zzew zzew) throws RemoteException {
                return zzew.createAdLoaderBuilder(zzd.zzA(context), str, zzka, 10260000);
            }

            /* renamed from: zzeK */
            public zzer zzeJ() {
                zzer zza = zzek.this.zzzI.zza(context, str, zzka);
                if (zza != null) {
                    return zza;
                }
                zzek.this.zzc(context, "native_ad");
                return new zzfk();
            }
        });
    }

    public zzet zzb(Context context, zzeg zzeg, String str, zzka zzka) {
        final Context context2 = context;
        final zzeg zzeg2 = zzeg;
        final String str2 = str;
        final zzka zzka2 = zzka;
        return (zzet) zza(context, false, (zza<T>) new zza<zzet>() {
            /* renamed from: zza */
            public zzet zzb(zzew zzew) throws RemoteException {
                return zzew.createInterstitialAdManager(zzd.zzA(context2), zzeg2, str2, zzka2, 10260000);
            }

            /* renamed from: zzeI */
            public zzet zzeJ() {
                zzet zza = zzek.this.zzzH.zza(context2, zzeg2, str2, zzka2, 2);
                if (zza != null) {
                    return zza;
                }
                zzek.this.zzc(context2, "interstitial");
                return new zzfl();
            }
        });
    }

    public zzlf zzb(final Activity activity) {
        return (zzlf) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), (zza<T>) new zza<zzlf>() {
            /* renamed from: zzeO */
            public zzlf zzeJ() {
                zzlf zzg = zzek.this.zzzM.zzg(activity);
                if (zzg != null) {
                    return zzg;
                }
                zzek.this.zzc(activity, "iap");
                return null;
            }

            /* renamed from: zzg */
            public zzlf zzb(zzew zzew) throws RemoteException {
                return zzew.createInAppPurchaseManager(zzd.zzA(activity));
            }
        });
    }

    public zzkz zzc(final Activity activity) {
        return (zzkz) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), (zza<T>) new zza<zzkz>() {
            /* renamed from: zzeP */
            public zzkz zzeJ() {
                zzkz zzf = zzek.this.zzzN.zzf(activity);
                if (zzf != null) {
                    return zzf;
                }
                zzek.this.zzc(activity, "ad_overlay");
                return null;
            }

            /* renamed from: zzh */
            public zzkz zzb(zzew zzew) throws RemoteException {
                return zzew.createAdOverlay(zzd.zzA(activity));
            }
        });
    }
}
