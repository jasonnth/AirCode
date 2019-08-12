package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.zze;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzaak implements zzabc {
    /* access modifiers changed from: private */
    public final Lock zzaAG;
    private final zzg zzaAL;
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzaaj<?>> zzaAM = new HashMap();
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzaaj<?>> zzaAN = new HashMap();
    private final Map<Api<?>, Boolean> zzaAO;
    /* access modifiers changed from: private */
    public final zzaat zzaAP;
    private final zze zzaAQ;
    /* access modifiers changed from: private */
    public final Condition zzaAR;
    private final boolean zzaAS;
    /* access modifiers changed from: private */
    public final boolean zzaAT;
    private final Queue<com.google.android.gms.internal.zzaad.zza<?, ?>> zzaAU = new LinkedList();
    /* access modifiers changed from: private */
    public boolean zzaAV;
    /* access modifiers changed from: private */
    public Map<zzzz<?>, ConnectionResult> zzaAW;
    /* access modifiers changed from: private */
    public Map<zzzz<?>, ConnectionResult> zzaAX;
    private zzb zzaAY;
    /* access modifiers changed from: private */
    public ConnectionResult zzaAZ;
    private final zzaax zzayX;
    private final Looper zzrs;

    private class zza implements OnCompleteListener<Void> {
        private zza() {
        }

        public void onComplete(Task<Void> task) {
            zzaak.this.zzaAG.lock();
            try {
                if (zzaak.this.zzaAV) {
                    if (task.isSuccessful()) {
                        zzaak.this.zzaAW = new ArrayMap(zzaak.this.zzaAM.size());
                        for (zzaaj apiKey : zzaak.this.zzaAM.values()) {
                            zzaak.this.zzaAW.put(apiKey.getApiKey(), ConnectionResult.zzayj);
                        }
                    } else if (task.getException() instanceof com.google.android.gms.common.api.zzb) {
                        com.google.android.gms.common.api.zzb zzb = (com.google.android.gms.common.api.zzb) task.getException();
                        if (zzaak.this.zzaAT) {
                            zzaak.this.zzaAW = new ArrayMap(zzaak.this.zzaAM.size());
                            for (zzaaj zzaaj : zzaak.this.zzaAM.values()) {
                                zzzz apiKey2 = zzaaj.getApiKey();
                                ConnectionResult zza = zzb.zza(zzaaj);
                                if (zzaak.this.zza(zzaaj, zza)) {
                                    zzaak.this.zzaAW.put(apiKey2, new ConnectionResult(16));
                                } else {
                                    zzaak.this.zzaAW.put(apiKey2, zza);
                                }
                            }
                        } else {
                            zzaak.this.zzaAW = zzb.zzvj();
                        }
                        zzaak.this.zzaAZ = zzaak.this.zzvX();
                    } else {
                        Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                        zzaak.this.zzaAW = Collections.emptyMap();
                        zzaak.this.zzaAZ = new ConnectionResult(8);
                    }
                    if (zzaak.this.zzaAX != null) {
                        zzaak.this.zzaAW.putAll(zzaak.this.zzaAX);
                        zzaak.this.zzaAZ = zzaak.this.zzvX();
                    }
                    if (zzaak.this.zzaAZ == null) {
                        zzaak.this.zzvV();
                        zzaak.this.zzvW();
                    } else {
                        zzaak.this.zzaAV = false;
                        zzaak.this.zzaAP.zzc(zzaak.this.zzaAZ);
                    }
                    zzaak.this.zzaAR.signalAll();
                    zzaak.this.zzaAG.unlock();
                }
            } finally {
                zzaak.this.zzaAG.unlock();
            }
        }
    }

    private class zzb implements OnCompleteListener<Void> {
        private zzabq zzaBb;

        zzb(zzabq zzabq) {
            this.zzaBb = zzabq;
        }

        /* access modifiers changed from: 0000 */
        public void cancel() {
            this.zzaBb.zzrq();
        }

        public void onComplete(Task<Void> task) {
            zzaak.this.zzaAG.lock();
            try {
                if (!zzaak.this.zzaAV) {
                    this.zzaBb.zzrq();
                    return;
                }
                if (task.isSuccessful()) {
                    zzaak.this.zzaAX = new ArrayMap(zzaak.this.zzaAN.size());
                    for (zzaaj apiKey : zzaak.this.zzaAN.values()) {
                        zzaak.this.zzaAX.put(apiKey.getApiKey(), ConnectionResult.zzayj);
                    }
                } else if (task.getException() instanceof com.google.android.gms.common.api.zzb) {
                    com.google.android.gms.common.api.zzb zzb = (com.google.android.gms.common.api.zzb) task.getException();
                    if (zzaak.this.zzaAT) {
                        zzaak.this.zzaAX = new ArrayMap(zzaak.this.zzaAN.size());
                        for (zzaaj zzaaj : zzaak.this.zzaAN.values()) {
                            zzzz apiKey2 = zzaaj.getApiKey();
                            ConnectionResult zza = zzb.zza(zzaaj);
                            if (zzaak.this.zza(zzaaj, zza)) {
                                zzaak.this.zzaAX.put(apiKey2, new ConnectionResult(16));
                            } else {
                                zzaak.this.zzaAX.put(apiKey2, zza);
                            }
                        }
                    } else {
                        zzaak.this.zzaAX = zzb.zzvj();
                    }
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    zzaak.this.zzaAX = Collections.emptyMap();
                }
                if (zzaak.this.isConnected()) {
                    zzaak.this.zzaAW.putAll(zzaak.this.zzaAX);
                    if (zzaak.this.zzvX() == null) {
                        zzaak.this.zzvV();
                        zzaak.this.zzvW();
                        zzaak.this.zzaAR.signalAll();
                    }
                }
                this.zzaBb.zzrq();
                zzaak.this.zzaAG.unlock();
            } finally {
                zzaak.this.zzaAG.unlock();
            }
        }
    }

    public zzaak(Context context, Lock lock, Looper looper, zze zze, Map<zzc<?>, Api.zze> map, zzg zzg, Map<Api<?>, Boolean> map2, com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zza2, ArrayList<zzaag> arrayList, zzaat zzaat, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzaAG = lock;
        this.zzrs = looper;
        this.zzaAR = lock.newCondition();
        this.zzaAQ = zze;
        this.zzaAP = zzaat;
        this.zzaAO = map2;
        this.zzaAL = zzg;
        this.zzaAS = z;
        HashMap hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzvg(), api);
        }
        HashMap hashMap2 = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zzaag zzaag = (zzaag) it.next();
            hashMap2.put(zzaag.zzaxf, zzaag);
        }
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = false;
        for (Entry entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.zze zze2 = (Api.zze) entry.getValue();
            if (zze2.zzvh()) {
                z2 = true;
                if (!((Boolean) this.zzaAO.get(api2)).booleanValue()) {
                    z3 = z5;
                    z4 = true;
                } else {
                    z3 = z5;
                    z4 = z6;
                }
            } else {
                z2 = z7;
                z3 = false;
                z4 = z6;
            }
            zzaaj zzaaj = new zzaaj(context, api2, looper, zze2, (zzaag) hashMap2.get(api2), zzg, zza2);
            this.zzaAM.put((zzc) entry.getKey(), zzaaj);
            if (zze2.zzrd()) {
                this.zzaAN.put((zzc) entry.getKey(), zzaaj);
            }
            z7 = z2;
            z5 = z3;
            z6 = z4;
        }
        this.zzaAT = z7 && !z5 && !z6;
        this.zzayX = zzaax.zzww();
    }

    /* access modifiers changed from: private */
    public boolean zza(zzaaj<?> zzaaj, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zzaAO.get(zzaaj.getApi())).booleanValue() && zzaaj.zzvU().zzvh() && this.zzaAQ.isUserResolvableError(connectionResult.getErrorCode());
    }

    private ConnectionResult zzb(zzc<?> zzc) {
        this.zzaAG.lock();
        try {
            zzaaj zzaaj = (zzaaj) this.zzaAM.get(zzc);
            if (this.zzaAW != null && zzaaj != null) {
                return (ConnectionResult) this.zzaAW.get(zzaaj.getApiKey());
            }
            this.zzaAG.unlock();
            return null;
        } finally {
            this.zzaAG.unlock();
        }
    }

    private <T extends com.google.android.gms.internal.zzaad.zza<? extends Result, ? extends com.google.android.gms.common.api.Api.zzb>> boolean zzd(T t) {
        zzc zzvg = t.zzvg();
        ConnectionResult zzb2 = zzb(zzvg);
        if (zzb2 == null || zzb2.getErrorCode() != 4) {
            return false;
        }
        t.zzB(new Status(4, null, this.zzayX.zza(((zzaaj) this.zzaAM.get(zzvg)).getApiKey(), this.zzaAP.getSessionId())));
        return true;
    }

    /* access modifiers changed from: private */
    public void zzvV() {
        if (this.zzaAL == null) {
            this.zzaAP.zzaBR = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzaAL.zzxL());
        Map zzxN = this.zzaAL.zzxN();
        for (Api api : zzxN.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((com.google.android.gms.common.internal.zzg.zza) zzxN.get(api)).zzakq);
            }
        }
        this.zzaAP.zzaBR = hashSet;
    }

    /* access modifiers changed from: private */
    public void zzvW() {
        while (!this.zzaAU.isEmpty()) {
            zzb((T) (com.google.android.gms.internal.zzaad.zza) this.zzaAU.remove());
        }
        this.zzaAP.zzo(null);
    }

    /* access modifiers changed from: private */
    public ConnectionResult zzvX() {
        ConnectionResult connectionResult;
        int i;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        int i3 = 0;
        ConnectionResult connectionResult3 = null;
        for (zzaaj zzaaj : this.zzaAM.values()) {
            Api api = zzaaj.getApi();
            ConnectionResult connectionResult4 = (ConnectionResult) this.zzaAW.get(zzaaj.getApiKey());
            if (!connectionResult4.isSuccess() && (!((Boolean) this.zzaAO.get(api)).booleanValue() || connectionResult4.hasResolution() || this.zzaAQ.isUserResolvableError(connectionResult4.getErrorCode()))) {
                if (connectionResult4.getErrorCode() != 4 || !this.zzaAS) {
                    int priority = api.zzve().getPriority();
                    if (connectionResult3 == null || i3 > priority) {
                        int i4 = priority;
                        connectionResult = connectionResult4;
                        i = i4;
                    } else {
                        i = i3;
                        connectionResult = connectionResult3;
                    }
                    i3 = i;
                    connectionResult3 = connectionResult;
                } else {
                    int priority2 = api.zzve().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        i2 = priority2;
                        connectionResult2 = connectionResult4;
                    }
                }
            }
        }
        return (connectionResult3 == null || connectionResult2 == null || i3 <= i2) ? connectionResult3 : connectionResult2;
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzaAR.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaAZ != null ? this.zzaAZ : new ConnectionResult(13, null);
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
                nanos = this.zzaAR.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzayj : this.zzaAZ != null ? this.zzaAZ : new ConnectionResult(13, null);
    }

    public void connect() {
        this.zzaAG.lock();
        try {
            if (!this.zzaAV) {
                this.zzaAV = true;
                this.zzaAW = null;
                this.zzaAX = null;
                this.zzaAY = null;
                this.zzaAZ = null;
                this.zzayX.zzvx();
                this.zzayX.zza((Iterable<? extends com.google.android.gms.common.api.zzc<?>>) this.zzaAM.values()).addOnCompleteListener(new zzadb(this.zzrs), new zza());
                this.zzaAG.unlock();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void disconnect() {
        this.zzaAG.lock();
        try {
            this.zzaAV = false;
            this.zzaAW = null;
            this.zzaAX = null;
            if (this.zzaAY != null) {
                this.zzaAY.cancel();
                this.zzaAY = null;
            }
            this.zzaAZ = null;
            while (!this.zzaAU.isEmpty()) {
                com.google.android.gms.internal.zzaad.zza zza2 = (com.google.android.gms.internal.zzaad.zza) this.zzaAU.remove();
                zza2.zza((zzb) null);
                zza2.cancel();
            }
            this.zzaAR.signalAll();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        return zzb(api.zzvg());
    }

    public boolean isConnected() {
        this.zzaAG.lock();
        try {
            return this.zzaAW != null && this.zzaAZ == null;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public boolean isConnecting() {
        this.zzaAG.lock();
        try {
            return this.zzaAW == null && this.zzaAV;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzaad.zza<R, A>> T zza(T t) {
        if (this.zzaAS && zzd(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zzaAU.add(t);
            return t;
        }
        this.zzaAP.zzaBW.zzb(t);
        return ((zzaaj) this.zzaAM.get(t.zzvg())).doRead(t);
    }

    /* JADX INFO: finally extract failed */
    public boolean zza(zzabq zzabq) {
        this.zzaAG.lock();
        try {
            if (!this.zzaAV || zzvN()) {
                this.zzaAG.unlock();
                return false;
            }
            this.zzayX.zzvx();
            this.zzaAY = new zzb(zzabq);
            this.zzayX.zza((Iterable<? extends com.google.android.gms.common.api.zzc<?>>) this.zzaAN.values()).addOnCompleteListener(new zzadb(this.zzrs), this.zzaAY);
            this.zzaAG.unlock();
            return true;
        } catch (Throwable th) {
            this.zzaAG.unlock();
            throw th;
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzaad.zza<? extends Result, A>> T zzb(T t) {
        zzc zzvg = t.zzvg();
        if (this.zzaAS && zzd(t)) {
            return t;
        }
        this.zzaAP.zzaBW.zzb(t);
        return ((zzaaj) this.zzaAM.get(zzvg)).doWrite(t);
    }

    public void zzvM() {
    }

    /* JADX INFO: finally extract failed */
    public boolean zzvN() {
        this.zzaAG.lock();
        try {
            if (!this.zzaAV || !this.zzaAS) {
                this.zzaAG.unlock();
                return false;
            }
            for (zzc zzb2 : this.zzaAN.keySet()) {
                ConnectionResult zzb3 = zzb(zzb2);
                if (zzb3 != null) {
                    if (!zzb3.isSuccess()) {
                    }
                }
                this.zzaAG.unlock();
                return false;
            }
            this.zzaAG.unlock();
            return true;
        } catch (Throwable th) {
            this.zzaAG.unlock();
            throw th;
        }
    }

    public void zzvn() {
        this.zzaAG.lock();
        try {
            this.zzayX.zzvn();
            if (this.zzaAY != null) {
                this.zzaAY.cancel();
                this.zzaAY = null;
            }
            if (this.zzaAX == null) {
                this.zzaAX = new ArrayMap(this.zzaAN.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzaaj apiKey : this.zzaAN.values()) {
                this.zzaAX.put(apiKey.getApiKey(), connectionResult);
            }
            if (this.zzaAW != null) {
                this.zzaAW.putAll(this.zzaAX);
            }
        } finally {
            this.zzaAG.unlock();
        }
    }
}
