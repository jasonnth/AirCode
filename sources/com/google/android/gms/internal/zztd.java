package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import java.util.Map;
import java.util.Map.Entry;

public class zztd extends zzsa {
    private static String zzags = "3";
    private static String zzagt = "01VDIWEA?";
    private static zztd zzagu;

    public zztd(zzsc zzsc) {
        super(zzsc);
    }

    public static zztd zzpW() {
        return zzagu;
    }

    public void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        String str2 = (String) zzsw.zzafl.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
        if (i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    public void zza(zzsz zzsz, String str) {
        if (str == null) {
            str = "no reason provided";
        }
        String str2 = zzsz != null ? zzsz.toString() : "no hit data";
        String str3 = "Discarding hit. ";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), str2);
    }

    public synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        synchronized (this) {
            zzac.zzw(str);
            if (i >= 0) {
                i2 = i;
            }
            int i3 = i2 >= zzagt.length() ? zzagt.length() - 1 : i2;
            char c = zznT().zzoW() ? 'C' : 'c';
            String str2 = zzags;
            char charAt = zzagt.charAt(i3);
            String str3 = zzsb.VERSION;
            String valueOf = String.valueOf(zzc(str, zzm(obj), zzm(obj2), zzm(obj3)));
            String sb = new StringBuilder(String.valueOf(str2).length() + 3 + String.valueOf(str3).length() + String.valueOf(valueOf).length()).append(str2).append(charAt).append(c).append(str3).append(":").append(valueOf).toString();
            if (sb.length() > 1024) {
                sb = sb.substring(0, 1024);
            }
            zztg zzog = zznQ().zzog();
            if (zzog != null) {
                zzog.zzqj().zzcc(sb);
            }
        }
    }

    public void zzg(Map<String, String> map, String str) {
        String str2;
        if (str == null) {
            str = "no reason provided";
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Entry entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append((String) entry.getKey());
                sb.append('=');
                sb.append((String) entry.getValue());
            }
            str2 = sb.toString();
        } else {
            str2 = "no hit data";
        }
        String str3 = "Discarding hit. ";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), str2);
    }

    /* access modifiers changed from: protected */
    public String zzm(Object obj) {
        if (obj == null) {
            return null;
        }
        Object obj2 = obj instanceof Integer ? new Long((long) ((Integer) obj).intValue()) : obj;
        if (!(obj2 instanceof Long)) {
            return obj2 instanceof Boolean ? String.valueOf(obj2) : obj2 instanceof Throwable ? obj2.getClass().getCanonicalName() : "-";
        }
        if (Math.abs(((Long) obj2).longValue()) < 100) {
            return String.valueOf(obj2);
        }
        String str = String.valueOf(obj2).charAt(0) == '-' ? "-" : "";
        String valueOf = String.valueOf(Math.abs(((Long) obj2).longValue()));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))));
        sb.append("...");
        sb.append(str);
        sb.append(Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        synchronized (zztd.class) {
            zzagu = this;
        }
    }
}
