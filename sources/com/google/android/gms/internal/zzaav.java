package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaav implements zzaah, zzabc {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Lock zzaAG;
    final zzg zzaAL;
    final Map<Api<?>, Boolean> zzaAO;
    private final zze zzaAQ;
    final zzaat zzaAw;
    final Map<zzc<?>, Api.zze> zzaBQ;
    private final Condition zzaCd;
    private final zzb zzaCe;
    final Map<zzc<?>, ConnectionResult> zzaCf = new HashMap();
    /* access modifiers changed from: private */
    public volatile zzaau zzaCg;
    private ConnectionResult zzaCh = null;
    int zzaCi;
    final com.google.android.gms.internal.zzabc.zza zzaCj;
    final com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zzazo;

    static abstract class zza {
        private final zzaau zzaCk;

        protected zza(zzaau zzaau) {
            this.zzaCk = zzaau;
        }

        public final void zzc(zzaav zzaav) {
            zzaav.zzaAG.lock();
            try {
                if (zzaav.zzaCg == this.zzaCk) {
                    zzwe();
                    zzaav.zzaAG.unlock();
                }
            } finally {
                zzaav.zzaAG.unlock();
            }
        }

        /* access modifiers changed from: protected */
        public abstract void zzwe();
    }

    final class zzb extends Handler {
        zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzc(zzaav.this);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    public zzaav(Context context, zzaat zzaat, Lock lock, Looper looper, zze zze, Map<zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Boolean> map2, com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zza2, ArrayList<zzaag> arrayList, com.google.android.gms.internal.zzabc.zza zza3) {
        this.mContext = context;
        this.zzaAG = lock;
        this.zzaAQ = zze;
        this.zzaBQ = map;
        this.zzaAL = zzg;
        this.zzaAO = map2;
        this.zzazo = zza2;
        this.zzaAw = zzaat;
        this.zzaCj = zza3;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((zzaag) it.next()).zza(this);
        }
        this.zzaCe = new zzb(looper);
        this.zzaCd = lock.newCondition();
        this.zzaCg = new zzaas(this);
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzaCd.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaCh != null ? this.zzaCh : new ConnectionResult(13, null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zzaCd.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaCh != null ? this.zzaCh : new ConnectionResult(13, null);
    }

    public void connect() {
        this.zzaCg.connect();
    }

    public void disconnect() {
        if (this.zzaCg.disconnect()) {
            this.zzaCf.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zzaCg);
        for (Api api : this.zzaAO.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((Api.zze) this.zzaBQ.get(api.zzvg())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        zzc zzvg = api.zzvg();
        if (this.zzaBQ.containsKey(zzvg)) {
            if (((Api.zze) this.zzaBQ.get(zzvg)).isConnected()) {
                return ConnectionResult.zzayj;
            }
            if (this.zzaCf.containsKey(zzvg)) {
                return (ConnectionResult) this.zzaCf.get(zzvg);
            }
        }
        return null;
    }

    public boolean isConnected() {
        return this.zzaCg instanceof zzaaq;
    }

    public boolean isConnecting() {
        return this.zzaCg instanceof zzaar;
    }

    public void onConnected(Bundle bundle) {
        this.zzaAG.lock();
        try {
            this.zzaCg.onConnected(bundle);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.zzaAG.lock();
        try {
            this.zzaCg.onConnectionSuspended(i);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzaad.zza<R, A>> T zza(T t) {
        t.zzvI();
        return this.zzaCg.zza(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        this.zzaAG.lock();
        try {
            this.zzaCg.zza(connectionResult, api, z);
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zza zza2) {
        this.zzaCe.sendMessage(this.zzaCe.obtainMessage(1, zza2));
    }

    /* access modifiers changed from: 0000 */
    public void zza(RuntimeException runtimeException) {
        this.zzaCe.sendMessage(this.zzaCe.obtainMessage(2, runtimeException));
    }

    public boolean zza(zzabq zzabq) {
        return false;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzaad.zza<? extends Result, A>> T zzb(T t) {
        t.zzvI();
        return this.zzaCg.zzb(t);
    }

    /* access modifiers changed from: 0000 */
    public void zzh(ConnectionResult connectionResult) {
        this.zzaAG.lock();
        try {
            this.zzaCh = connectionResult;
            this.zzaCg = new zzaas(this);
            this.zzaCg.begin();
            this.zzaCd.signalAll();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzvM() {
        if (isConnected()) {
            ((zzaaq) this.zzaCg).zzwd();
        }
    }

    public void zzvn() {
    }

    /* access modifiers changed from: 0000 */
    public void zzws() {
        this.zzaAG.lock();
        try {
            this.zzaCg = new zzaar(this, this.zzaAL, this.zzaAO, this.zzaAQ, this.zzazo, this.zzaAG, this.mContext);
            this.zzaCg.begin();
            this.zzaCd.signalAll();
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzwt() {
        this.zzaAG.lock();
        try {
            this.zzaAw.zzwp();
            this.zzaCg = new zzaaq(this);
            this.zzaCg.begin();
            this.zzaCd.signalAll();
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzwu() {
        for (Api.zze disconnect : this.zzaBQ.values()) {
            disconnect.disconnect();
        }
    }
}
