package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.p000v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzabq;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class zzb extends AsyncTaskLoader<Void> implements zzabq {
    private Semaphore zzaku = new Semaphore(0);
    private Set<GoogleApiClient> zzakv;

    public zzb(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzakv = set;
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        this.zzaku.drainPermits();
        forceLoad();
    }

    /* renamed from: zzrp */
    public Void loadInBackground() {
        int i;
        int i2 = 0;
        Iterator it = this.zzakv.iterator();
        while (true) {
            i = i2;
            if (it.hasNext()) {
                i2 = ((GoogleApiClient) it.next()).zza((zzabq) this) ? i + 1 : i;
            } else {
                try {
                    break;
                } catch (InterruptedException e) {
                    Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.zzaku.tryAcquire(i, 5, TimeUnit.SECONDS);
        return null;
    }

    public void zzrq() {
        this.zzaku.release();
    }
}
