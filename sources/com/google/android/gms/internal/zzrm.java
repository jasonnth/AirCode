package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzf;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzrm extends zzf<zzrm> {
    private Map<Integer, String> zzacV = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzacV.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 9).append("dimension").append(valueOf).toString(), entry.getValue());
        }
        return zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzrm zzrm) {
        zzrm.zzacV.putAll(this.zzacV);
    }

    public Map<Integer, String> zznh() {
        return Collections.unmodifiableMap(this.zzacV);
    }
}
