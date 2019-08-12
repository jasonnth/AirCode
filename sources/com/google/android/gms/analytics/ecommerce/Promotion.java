package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzf;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Promotion {
    Map<String, String> zzadz = new HashMap();

    public String toString() {
        return zzf.zzS(this.zzadz);
    }

    public Map<String, String> zzbM(String str) {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzadz.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf((String) entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) entry.getValue());
        }
        return hashMap;
    }
}
