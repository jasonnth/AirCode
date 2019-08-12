package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

public class zzbk extends zzca {
    private static final Object zzqS = new Object();
    private static volatile zzan zzqT = null;
    private zza zzqU = null;

    public zzbk(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2, zza zza2) {
        super(zzbd, str, str2, zza, i, i2);
        this.zzqU = zza2;
    }

    public static Boolean zza(zza zza) {
        boolean z = false;
        if (!zzbf.zzs(zzb(zza))) {
            return Boolean.valueOf(false);
        }
        if (!(zza == null || zza.zzaK == null || zza.zzaK.zzaM.intValue() != 3)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static String zzb(zza zza) {
        if (zza == null || zza.zzaL == null || zzbf.zzs(zza.zzaL.zzaN)) {
            return null;
        }
        return zza.zzaL.zzaN;
    }

    private boolean zzbe() {
        return zzqT == null || zzbf.zzs(zzqT.zzaN) || zzqT.zzaN.equals("E");
    }

    private int zzbf() {
        if (!zzbf.zzs(zzb(this.zzqU))) {
            return 4;
        }
        return (!zza(this.zzqU).booleanValue() || !zzbg()) ? 2 : 3;
    }

    private boolean zzbg() {
        return this.zzpz.zzaQ() && ((Boolean) zzgd.zzDW.get()).booleanValue() && ((Boolean) zzgd.zzDX.get()).booleanValue() && ((Boolean) zzgd.zzDV.get()).booleanValue();
    }

    private String zzbh() {
        try {
            if (this.zzpz.zzaS() != null) {
                this.zzpz.zzaS().get();
            }
            zzag.zza zzaR = this.zzpz.zzaR();
            if (!(zzaR == null || zzaR.zzaN == null)) {
                return zzaR.zzaN;
            }
        } catch (InterruptedException | ExecutionException e) {
        }
        return null;
    }

    private void zze(int i) throws IllegalAccessException, InvocationTargetException {
        boolean z = false;
        Method method = this.zzre;
        Object[] objArr = new Object[2];
        objArr[0] = this.zzpz.getContext();
        if (i == 2) {
            z = true;
        }
        objArr[1] = Boolean.valueOf(z);
        zzqT = new zzan((String) method.invoke(null, objArr));
        if (zzbf.zzs(zzqT.zzaN) || zzqT.zzaN.equals("E")) {
            switch (i) {
                case 3:
                    String zzbh = zzbh();
                    if (!zzbf.zzs(zzbh)) {
                        zzqT.zzaN = zzbh;
                        return;
                    }
                    return;
                case 4:
                    zzqT.zzaN = this.zzqU.zzaL.zzaN;
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (zzbe()) {
            synchronized (zzqS) {
                int zzbf = zzbf();
                if (zzbf == 3) {
                    this.zzpz.zzaV();
                }
                zze(zzbf);
            }
        }
        synchronized (this.zzqV) {
            if (zzqT != null) {
                this.zzqV.zzaN = zzqT.zzaN;
                this.zzqV.zzbA = Long.valueOf(zzqT.zzlE);
                this.zzqV.zzaP = zzqT.zzaP;
                this.zzqV.zzaQ = zzqT.zzaQ;
                this.zzqV.zzaR = zzqT.zzaR;
            }
        }
    }
}
