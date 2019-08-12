package com.google.firebase.appindexing;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzd;
import java.lang.ref.WeakReference;

public abstract class FirebaseAppIndex {
    private static WeakReference<FirebaseAppIndex> zzbXj;

    public static synchronized FirebaseAppIndex getInstance() {
        FirebaseAppIndex zzVh;
        synchronized (FirebaseAppIndex.class) {
            zzVh = zzVh();
            if (zzVh == null) {
                zzVh = zzcp(FirebaseApp.getInstance().getApplicationContext());
            }
        }
        return zzVh;
    }

    private static FirebaseAppIndex zzVh() {
        if (zzbXj == null) {
            return null;
        }
        return (FirebaseAppIndex) zzbXj.get();
    }

    private static FirebaseAppIndex zzcp(Context context) {
        zzd zzd = new zzd(context);
        zzbXj = new WeakReference<>(zzd);
        return zzd;
    }

    public abstract Task<Void> remove(String... strArr);

    public abstract Task<Void> update(Indexable... indexableArr);
}
