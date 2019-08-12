package com.google.android.gms.internal;

@zzme
public class zzgh {
    public static zzgj zza(zzgl zzgl, long j) {
        if (zzgl == null) {
            return null;
        }
        return zzgl.zzc(j);
    }

    public static boolean zza(zzgl zzgl, zzgj zzgj, long j, String... strArr) {
        if (zzgl == null || zzgj == null) {
            return false;
        }
        return zzgl.zza(zzgj, j, strArr);
    }

    public static boolean zza(zzgl zzgl, zzgj zzgj, String... strArr) {
        if (zzgl == null || zzgj == null) {
            return false;
        }
        return zzgl.zza(zzgj, strArr);
    }

    public static zzgj zzb(zzgl zzgl) {
        if (zzgl == null) {
            return null;
        }
        return zzgl.zzfB();
    }
}
