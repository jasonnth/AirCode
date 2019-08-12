package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.zzka.zza;
import java.util.Map;

@zzme
public final class zzjz extends zza {
    private Map<Class<? extends Object>, Object> zzLA;

    private <NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzkb zzau(String str) throws RemoteException {
        try {
            Class cls = Class.forName(str, false, zzjz.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new zzkm(mediationAdapter, (NetworkExtras) this.zzLA.get(mediationAdapter.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new zzkh((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            } else {
                zzqf.zzbh(new StringBuilder(String.valueOf(str).length() + 64).append("Could not instantiate mediation adapter: ").append(str).append(" (not a valid adapter).").toString());
                throw new RemoteException();
            }
        } catch (Throwable th) {
            return zzav(str);
        }
    }

    private zzkb zzav(String str) throws RemoteException {
        try {
            zzqf.zzbf("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzkh(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzkh(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzkh(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzkm(customEventAdapter, (CustomEventExtras) this.zzLA.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            zzqf.zzc(new StringBuilder(String.valueOf(str).length() + 43).append("Could not instantiate mediation adapter: ").append(str).append(". ").toString(), th);
        }
    }

    public zzkb zzas(String str) throws RemoteException {
        return zzau(str);
    }

    public boolean zzat(String str) throws RemoteException {
        boolean z = false;
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(str, false, zzjz.class.getClassLoader()));
        } catch (Throwable th) {
            zzqf.zzbh(new StringBuilder(String.valueOf(str).length() + 80).append("Could not load custom event implementation class: ").append(str).append(", assuming old implementation.").toString());
            return z;
        }
    }

    public void zzi(Map<Class<? extends Object>, Object> map) {
        this.zzLA = map;
    }
}
