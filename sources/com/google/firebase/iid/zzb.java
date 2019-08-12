package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    final ExecutorService zzbtK = Executors.newSingleThreadExecutor();
    private Binder zzckT;
    private int zzckU;
    private int zzckV = 0;
    private final Object zzrJ = new Object();

    /* renamed from: com.google.firebase.iid.zzb$zzb reason: collision with other inner class name */
    public static class C7831zzb extends Binder {
        private final zzb zzclc;

        C7831zzb(zzb zzb) {
            this.zzclc = zzb;
        }
    }

    /* access modifiers changed from: private */
    public void zzC(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.zzrJ) {
            this.zzckV--;
            if (this.zzckV == 0) {
                zzqE(this.zzckU);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzckT == null) {
            this.zzckT = new C7831zzb(this);
        }
        return this.zzckT;
    }

    public final int onStartCommand(final Intent intent, int i, int i2) {
        synchronized (this.zzrJ) {
            this.zzckU = i2;
            this.zzckV++;
        }
        final Intent zzD = zzD(intent);
        if (zzD == null) {
            zzC(intent);
            return 2;
        } else if (zzE(zzD)) {
            zzC(intent);
            return 2;
        } else {
            this.zzbtK.execute(new Runnable() {
                public void run() {
                    zzb.this.handleIntent(zzD);
                    zzb.this.zzC(intent);
                }
            });
            return 3;
        }
    }

    /* access modifiers changed from: protected */
    public Intent zzD(Intent intent) {
        return intent;
    }

    public boolean zzE(Intent intent) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzqE(int i) {
        return stopSelfResult(i);
    }
}
