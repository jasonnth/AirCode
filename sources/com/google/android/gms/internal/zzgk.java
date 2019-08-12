package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@zzme
public class zzgk {
    private final Map<String, zzgj> zzFN = new HashMap();
    private final zzgl zzsn;

    public zzgk(zzgl zzgl) {
        this.zzsn = zzgl;
    }

    public void zza(String str, zzgj zzgj) {
        this.zzFN.put(str, zzgj);
    }

    public void zza(String str, String str2, long j) {
        zzgh.zza(this.zzsn, (zzgj) this.zzFN.get(str2), j, str);
        this.zzFN.put(str, zzgh.zza(this.zzsn, j));
    }

    public zzgl zzfA() {
        return this.zzsn;
    }
}
