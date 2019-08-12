package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzf;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProductAction {
    Map<String, String> zzadz;

    public Map<String, String> build() {
        return new HashMap(this.zzadz);
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzadz.entrySet()) {
            if (((String) entry.getKey()).startsWith("&")) {
                hashMap.put(((String) entry.getKey()).substring(1), (String) entry.getValue());
            } else {
                hashMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return zzf.zzS(hashMap);
    }
}
