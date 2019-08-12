package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;

public class zzrz {
    private final zzsc zzadO;

    protected zzrz(zzsc zzsc) {
        zzac.zzw(zzsc);
        this.zzadO = zzsc;
    }

    private void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zztd zztd = null;
        if (this.zzadO != null) {
            zztd = this.zzadO.zzoe();
        }
        if (zztd != null) {
            zztd.zza(i, str, obj, obj2, obj3);
            return;
        }
        String str2 = (String) zzsw.zzafl.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zzk = zzk(obj);
        String zzk2 = zzk(obj2);
        String zzk3 = zzk(obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzk)) {
            sb.append(str2);
            sb.append(zzk);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzk2)) {
            sb.append(str2);
            sb.append(zzk2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzk3)) {
            sb.append(str2);
            sb.append(zzk3);
        }
        return sb.toString();
    }

    private static String zzk(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
        }
        return obj == Boolean.TRUE ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.zzadO.getContext();
    }

    public void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public void zzbP(String str) {
        zza(2, str, null, null, null);
    }

    public void zzbQ(String str) {
        zza(3, str, null, null, null);
    }

    public void zzbR(String str) {
        zza(4, str, null, null, null);
    }

    public void zzbS(String str) {
        zza(5, str, null, null, null);
    }

    public void zzbT(String str) {
        zza(6, str, null, null, null);
    }

    public void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public boolean zzkI() {
        return Log.isLoggable((String) zzsw.zzafl.get(), 2);
    }

    /* access modifiers changed from: protected */
    public zzry zzmA() {
        return this.zzadO.zzmA();
    }

    /* access modifiers changed from: protected */
    public zztn zzmB() {
        return this.zzadO.zzmB();
    }

    /* access modifiers changed from: protected */
    public void zzmR() {
        this.zzadO.zzmR();
    }

    public GoogleAnalytics zzmu() {
        return this.zzadO.zzof();
    }

    public zzsc zznQ() {
        return this.zzadO;
    }

    /* access modifiers changed from: protected */
    public zze zznR() {
        return this.zzadO.zznR();
    }

    /* access modifiers changed from: protected */
    public zztd zznS() {
        return this.zzadO.zznS();
    }

    /* access modifiers changed from: protected */
    public zzsp zznT() {
        return this.zzadO.zznT();
    }

    /* access modifiers changed from: protected */
    public zzh zznU() {
        return this.zzadO.zznU();
    }

    /* access modifiers changed from: protected */
    public zzst zznV() {
        return this.zzadO.zznV();
    }

    /* access modifiers changed from: protected */
    public zztg zznW() {
        return this.zzadO.zznW();
    }

    /* access modifiers changed from: protected */
    public zzsk zznX() {
        return this.zzadO.zzoi();
    }

    /* access modifiers changed from: protected */
    public zzrx zznY() {
        return this.zzadO.zzoh();
    }

    /* access modifiers changed from: protected */
    public zzsh zznZ() {
        return this.zzadO.zznZ();
    }

    /* access modifiers changed from: protected */
    public zzss zzoa() {
        return this.zzadO.zzoa();
    }
}
