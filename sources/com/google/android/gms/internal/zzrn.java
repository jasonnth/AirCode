package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzf;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzrn extends zzf<zzrn> {
    private Map<Integer, Double> zzacW = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzacW.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 6).append("metric").append(valueOf).toString(), entry.getValue());
        }
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrn zzrn) {
        zzrn.zzacW.putAll(this.zzacW);
    }

    public Map<Integer, Double> zzni() {
        return Collections.unmodifiableMap(this.zzacW);
    }
}
