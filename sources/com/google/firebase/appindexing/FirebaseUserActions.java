package com.google.firebase.appindexing;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzf;
import java.lang.ref.WeakReference;

public abstract class FirebaseUserActions {
    private static WeakReference<FirebaseUserActions> zzbXj;

    public static synchronized FirebaseUserActions getInstance() {
        FirebaseUserActions zzVi;
        synchronized (FirebaseUserActions.class) {
            zzVi = zzVi();
            if (zzVi == null) {
                zzVi = zzcq(FirebaseApp.getInstance().getApplicationContext());
            }
        }
        return zzVi;
    }

    private static FirebaseUserActions zzVi() {
        if (zzbXj == null) {
            return null;
        }
        return (FirebaseUserActions) zzbXj.get();
    }

    private static FirebaseUserActions zzcq(Context context) {
        zzf zzf = new zzf(context);
        zzbXj = new WeakReference<>(zzf);
        return zzf;
    }

    public abstract Task<Void> end(Action action);
}
