package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@zzme
public class zzod {
    public static void zza(Context context, Runnable runnable) {
        zzac.zzdj("Adapters must be initialized on the main thread.");
        Map zzkf = zzw.zzcQ().zzkw().zzkf();
        if (zzkf != null && !zzkf.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzpk.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzns zzjE = zzns.zzjE();
            if (zzjE != null) {
                zza(context, zzkf.values(), zzjE);
            }
        }
    }

    static void zza(Context context, Collection<zzjr> collection, zzns zzns) {
        HashMap hashMap = new HashMap();
        IObjectWrapper zzA = zzd.zzA(context);
        for (zzjr zzjr : collection) {
            for (zzjq zzjq : zzjr.zzKD) {
                String str = zzjq.zzKv;
                for (String str2 : zzjq.zzKp) {
                    if (!hashMap.containsKey(str2)) {
                        hashMap.put(str2, new ArrayList());
                    }
                    if (str != null) {
                        ((Collection) hashMap.get(str2)).add(str);
                    }
                }
            }
        }
        for (Entry entry : hashMap.entrySet()) {
            String str3 = (String) entry.getKey();
            try {
                zzol zzaN = zzns.zzaN(str3);
                if (zzaN != null) {
                    zzkb zzjN = zzaN.zzjN();
                    if (!zzjN.isInitialized() && zzjN.zzhg()) {
                        zzjN.zza(zzA, (zzom) zzaN.zzjO(), (List) entry.getValue());
                        String str4 = "Initialized rewarded video mediation adapter ";
                        String valueOf = String.valueOf(str3);
                        zzpk.zzbf(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                    }
                }
            } catch (Throwable th) {
                zzpk.zzc(new StringBuilder(String.valueOf(str3).length() + 56).append("Failed to initialize rewarded video mediation adapter \"").append(str3).append("\"").toString(), th);
            }
        }
    }
}
