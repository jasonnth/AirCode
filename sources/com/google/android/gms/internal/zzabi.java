package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzabh.zzb;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class zzabi {
    private final Set<zzabh<?>> zzasL = Collections.newSetFromMap(new WeakHashMap());

    public static <L> zzb<L> zza(L l, String str) {
        zzac.zzb(l, (Object) "Listener must not be null");
        zzac.zzb(str, (Object) "Listener type must not be null");
        zzac.zzh(str, "Listener type must not be empty");
        return new zzb<>(l, str);
    }

    public static <L> zzabh<L> zzb(L l, Looper looper, String str) {
        zzac.zzb(l, (Object) "Listener must not be null");
        zzac.zzb(looper, (Object) "Looper must not be null");
        zzac.zzb(str, (Object) "Listener type must not be null");
        return new zzabh<>(looper, l, str);
    }

    public void release() {
        for (zzabh clear : this.zzasL) {
            clear.clear();
        }
        this.zzasL.clear();
    }

    public <L> zzabh<L> zza(L l, Looper looper, String str) {
        zzabh<L> zzb = zzb(l, looper, str);
        this.zzasL.add(zzb);
        return zzb;
    }

    public <L> zzabh<L> zzb(L l, Looper looper) {
        return zza(l, looper, "NO_TYPE");
    }
}
