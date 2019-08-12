package com.google.android.gms.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.atomic.AtomicReference;

public class zzbth {
    private static final AtomicReference<zzbth> zzbWP = new AtomicReference<>();

    zzbth(Context context) {
    }

    public static zzbth zzcx(Context context) {
        zzbWP.compareAndSet(null, new zzbth(context));
        return (zzbth) zzbWP.get();
    }

    public void zzg(FirebaseApp firebaseApp) {
    }
}
