package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzm;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzaat extends GoogleApiClient implements com.google.android.gms.internal.zzabc.zza {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Lock zzaAG;
    final zzg zzaAL;
    final Map<Api<?>, Boolean> zzaAO;
    final Queue<com.google.android.gms.internal.zzaad.zza<?, ?>> zzaAU = new LinkedList();
    private final zzm zzaBJ;
    private zzabc zzaBK = null;
    private volatile boolean zzaBL;
    private long zzaBM = 120000;
    private long zzaBN = 5000;
    private final zza zzaBO;
    zzaaz zzaBP;
    final Map<zzc<?>, zze> zzaBQ;
    Set<Scope> zzaBR = new HashSet();
    private final zzabi zzaBS = new zzabi();
    private final ArrayList<zzaag> zzaBT;
    private Integer zzaBU = null;
    Set<zzabx> zzaBV = null;
    final zzaby zzaBW;
    private final com.google.android.gms.common.internal.zzm.zza zzaBX = new com.google.android.gms.common.internal.zzm.zza() {
        public boolean isConnected() {
            return zzaat.this.isConnected();
        }

        public Bundle zzuB() {
            return null;
        }
    };
    private final int zzazl;
    private final GoogleApiAvailability zzazn;
    final com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zzazo;
    private boolean zzazr;
    private final Looper zzrs;

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzaat.this.zzwn();
                    return;
                case 2:
                    zzaat.this.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    static class zzb extends com.google.android.gms.internal.zzaaz.zza {
        private WeakReference<zzaat> zzaCc;

        zzb(zzaat zzaat) {
            this.zzaCc = new WeakReference<>(zzaat);
        }

        public void zzvE() {
            zzaat zzaat = (zzaat) this.zzaCc.get();
            if (zzaat != null) {
                zzaat.resume();
            }
        }
    }

    public zzaat(Context context, Lock lock, Looper looper, zzg zzg, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zzbai, zzbaj> zza2, Map<Api<?>, Boolean> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<zzc<?>, zze> map2, int i, int i2, ArrayList<zzaag> arrayList, boolean z) {
        this.mContext = context;
        this.zzaAG = lock;
        this.zzazr = z;
        this.zzaBJ = new zzm(looper, this.zzaBX);
        this.zzrs = looper;
        this.zzaBO = new zza(looper);
        this.zzazn = googleApiAvailability;
        this.zzazl = i;
        if (this.zzazl >= 0) {
            this.zzaBU = Integer.valueOf(i2);
        }
        this.zzaAO = map;
        this.zzaBQ = map2;
        this.zzaBT = arrayList;
        this.zzaBW = new zzaby(this.zzaBQ);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzaBJ.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzaBJ.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzaAL = zzg;
        this.zzazo = zza2;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.zzaAG.lock();
        try {
            if (isResuming()) {
                zzwm();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public static int zza(Iterable<zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (zze zze : iterable) {
            if (zze.zzrd()) {
                z3 = true;
            }
            z2 = zze.zzrr() ? true : z2;
        }
        if (z3) {
            return (!z2 || !z) ? 1 : 2;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public void zza(final GoogleApiClient googleApiClient, final zzabt zzabt, final boolean z) {
        zzacf.zzaGM.zzg(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzp */
            public void onResult(Status status) {
                zzn.zzas(zzaat.this.mContext).zzrD();
                if (status.isSuccess() && zzaat.this.isConnected()) {
                    zzaat.this.reconnect();
                }
                zzabt.zzb(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private void zzb(zzabd zzabd) {
        if (this.zzazl >= 0) {
            zzaaa.zza(zzabd).zzcA(this.zzazl);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    private void zzcD(int i) {
        if (this.zzaBU == null) {
            this.zzaBU = Integer.valueOf(i);
        } else if (this.zzaBU.intValue() != i) {
            String valueOf = String.valueOf(zzcE(i));
            String valueOf2 = String.valueOf(zzcE(this.zzaBU.intValue()));
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(valueOf2).length()).append("Cannot use sign-in mode: ").append(valueOf).append(". Mode was already set to ").append(valueOf2).toString());
        }
        if (this.zzaBK == null) {
            boolean z = false;
            boolean z2 = false;
            for (zze zze : this.zzaBQ.values()) {
                if (zze.zzrd()) {
                    z2 = true;
                }
                z = zze.zzrr() ? true : z;
            }
            switch (this.zzaBU.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        if (this.zzazr) {
                            this.zzaBK = new zzaak(this.mContext, this.zzaAG, this.zzrs, this.zzazn, this.zzaBQ, this.zzaAL, this.zzaAO, this.zzazo, this.zzaBT, this, true);
                            return;
                        } else {
                            this.zzaBK = zzaai.zza(this.mContext, this, this.zzaAG, this.zzrs, this.zzazn, this.zzaBQ, this.zzaAL, this.zzaAO, this.zzazo, this.zzaBT);
                            return;
                        }
                    }
                    break;
            }
            if (!this.zzazr || z) {
                this.zzaBK = new zzaav(this.mContext, this, this.zzaAG, this.zzrs, this.zzazn, this.zzaBQ, this.zzaAL, this.zzaAO, this.zzazo, this.zzaBT, this);
            } else {
                this.zzaBK = new zzaak(this.mContext, this.zzaAG, this.zzrs, this.zzazn, this.zzaBQ, this.zzaAL, this.zzaAO, this.zzazo, this.zzaBT, this, false);
            }
        }
    }

    static String zzcE(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    private void zzwm() {
        this.zzaBJ.zzxY();
        this.zzaBK.connect();
    }

    /* access modifiers changed from: private */
    public void zzwn() {
        this.zzaAG.lock();
        try {
            if (zzwp()) {
                zzwm();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public ConnectionResult blockingConnect() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzaAG.lock();
        try {
            if (this.zzazl >= 0) {
                if (this.zzaBU == null) {
                    z = false;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzcD(this.zzaBU.intValue());
            this.zzaBJ.zzxY();
            return this.zzaBK.blockingConnect();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        zzac.zza(z, (Object) "blockingConnect must not be called on the UI thread");
        zzac.zzb(timeUnit, (Object) "TimeUnit must not be null");
        this.zzaAG.lock();
        try {
            if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzcD(this.zzaBU.intValue());
            this.zzaBJ.zzxY();
            return this.zzaBK.blockingConnect(j, timeUnit);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzac.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzac.zza(this.zzaBU.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzabt zzabt = new zzabt((GoogleApiClient) this);
        if (this.zzaBQ.containsKey(zzacf.zzaid)) {
            zza(this, zzabt, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new Builder(this.mContext).addApi(zzacf.API).addConnectionCallbacks(new ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                    zzaat.this.zza((GoogleApiClient) atomicReference.get(), zzabt, true);
                }

                public void onConnectionSuspended(int i) {
                }
            }).addOnConnectionFailedListener(new OnConnectionFailedListener(this) {
                public void onConnectionFailed(ConnectionResult connectionResult) {
                    zzabt.zzb(new Status(8));
                }
            }).setHandler(this.zzaBO).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzabt;
    }

    public void connect() {
        boolean z = false;
        this.zzaAG.lock();
        try {
            if (this.zzazl >= 0) {
                if (this.zzaBU != null) {
                    z = true;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzaBU == null) {
                this.zzaBU = Integer.valueOf(zza(this.zzaBQ.values(), false));
            } else if (this.zzaBU.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzaBU.intValue());
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void connect(int i) {
        boolean z = true;
        this.zzaAG.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzac.zzb(z, (Object) "Illegal sign-in mode: " + i);
            zzcD(i);
            zzwm();
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void disconnect() {
        this.zzaAG.lock();
        try {
            this.zzaBW.release();
            if (this.zzaBK != null) {
                this.zzaBK.disconnect();
            }
            this.zzaBS.release();
            for (com.google.android.gms.internal.zzaad.zza zza2 : this.zzaAU) {
                zza2.zza((zzb) null);
                zza2.cancel();
            }
            this.zzaAU.clear();
            if (this.zzaBK != null) {
                zzwp();
                this.zzaBJ.zzxX();
                this.zzaAG.unlock();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzaBL);
        printWriter.append(" mWorkQueue.size()=").print(this.zzaAU.size());
        this.zzaBW.dump(printWriter);
        if (this.zzaBK != null) {
            this.zzaBK.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        this.zzaAG.lock();
        try {
            if (!isConnected() && !isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zzaBQ.containsKey(api.zzvg())) {
                ConnectionResult connectionResult = this.zzaBK.getConnectionResult(api);
                if (connectionResult != null) {
                    this.zzaAG.unlock();
                } else if (isResuming()) {
                    connectionResult = ConnectionResult.zzayj;
                } else {
                    Log.w("GoogleApiClientImpl", zzwr());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.zzaAG.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzrs;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(Api<?> api) {
        if (!isConnected()) {
            return false;
        }
        zze zze = (zze) this.zzaBQ.get(api.zzvg());
        return zze != null && zze.isConnected();
    }

    public boolean isConnected() {
        return this.zzaBK != null && this.zzaBK.isConnected();
    }

    public boolean isConnecting() {
        return this.zzaBK != null && this.zzaBK.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        return this.zzaBJ.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzaBJ.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    /* access modifiers changed from: 0000 */
    public boolean isResuming() {
        return this.zzaBL;
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.zzaBJ.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzaBJ.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void stopAutoManage(FragmentActivity fragmentActivity) {
        zzb(new zzabd(fragmentActivity));
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.zzaBJ.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzaBJ.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public <C extends zze> C zza(zzc<C> zzc) {
        C c = (zze) this.zzaBQ.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzaad.zza<R, A>> T zza(T t) {
        zzac.zzb(t.zzvg() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzaBQ.containsKey(t.zzvg());
        String str = t.getApi() != null ? t.getApi().getName() : "the API";
        zzac.zzb(containsKey, (Object) new StringBuilder(String.valueOf(str).length() + 65).append("GoogleApiClient is not configured to use ").append(str).append(" required for this call.").toString());
        this.zzaAG.lock();
        try {
            if (this.zzaBK == null) {
                this.zzaAU.add(t);
            } else {
                t = this.zzaBK.zza(t);
                this.zzaAG.unlock();
            }
            return t;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zza(zzabx zzabx) {
        this.zzaAG.lock();
        try {
            if (this.zzaBV == null) {
                this.zzaBV = new HashSet();
            }
            this.zzaBV.add(zzabx);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public boolean zza(Api<?> api) {
        return this.zzaBQ.containsKey(api.zzvg());
    }

    public boolean zza(zzabq zzabq) {
        return this.zzaBK != null && this.zzaBK.zza(zzabq);
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzaad.zza<? extends Result, A>> T zzb(T t) {
        zzac.zzb(t.zzvg() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzaBQ.containsKey(t.zzvg());
        String str = t.getApi() != null ? t.getApi().getName() : "the API";
        zzac.zzb(containsKey, (Object) new StringBuilder(String.valueOf(str).length() + 65).append("GoogleApiClient is not configured to use ").append(str).append(" required for this call.").toString());
        this.zzaAG.lock();
        try {
            if (this.zzaBK == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (isResuming()) {
                this.zzaAU.add(t);
                while (!this.zzaAU.isEmpty()) {
                    com.google.android.gms.internal.zzaad.zza zza2 = (com.google.android.gms.internal.zzaad.zza) this.zzaAU.remove();
                    this.zzaBW.zzb(zza2);
                    zza2.zzB(Status.zzazz);
                }
            } else {
                t = this.zzaBK.zzb(t);
                this.zzaAG.unlock();
            }
            return t;
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzb(zzabx zzabx) {
        this.zzaAG.lock();
        try {
            if (this.zzaBV == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zzaBV.remove(zzabx)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzwq()) {
                this.zzaBK.zzvM();
            }
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public <C extends zze> C zzc(zzc<?> zzc) {
        C c = (zze) this.zzaBQ.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public void zzc(int i, boolean z) {
        if (i == 1 && !z) {
            zzwo();
        }
        this.zzaBW.zzxd();
        this.zzaBJ.zzcV(i);
        this.zzaBJ.zzxX();
        if (i == 2) {
            zzwm();
        }
    }

    public void zzc(ConnectionResult connectionResult) {
        if (!this.zzazn.zzd(this.mContext, connectionResult.getErrorCode())) {
            zzwp();
        }
        if (!isResuming()) {
            this.zzaBJ.zzn(connectionResult);
            this.zzaBJ.zzxX();
        }
    }

    public void zzo(Bundle bundle) {
        while (!this.zzaAU.isEmpty()) {
            zzb((T) (com.google.android.gms.internal.zzaad.zza) this.zzaAU.remove());
        }
        this.zzaBJ.zzq(bundle);
    }

    public <L> zzabh<L> zzr(L l) {
        this.zzaAG.lock();
        try {
            return this.zzaBS.zzb(l, this.zzrs);
        } finally {
            this.zzaAG.unlock();
        }
    }

    public void zzvn() {
        if (this.zzaBK != null) {
            this.zzaBK.zzvn();
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzwo() {
        if (!isResuming()) {
            this.zzaBL = true;
            if (this.zzaBP == null) {
                this.zzaBP = this.zzazn.zza(this.mContext.getApplicationContext(), (com.google.android.gms.internal.zzaaz.zza) new zzb(this));
            }
            this.zzaBO.sendMessageDelayed(this.zzaBO.obtainMessage(1), this.zzaBM);
            this.zzaBO.sendMessageDelayed(this.zzaBO.obtainMessage(2), this.zzaBN);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean zzwp() {
        if (!isResuming()) {
            return false;
        }
        this.zzaBL = false;
        this.zzaBO.removeMessages(2);
        this.zzaBO.removeMessages(1);
        if (this.zzaBP != null) {
            this.zzaBP.unregister();
            this.zzaBP = null;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean zzwq() {
        boolean z = false;
        this.zzaAG.lock();
        try {
            if (this.zzaBV != null) {
                if (!this.zzaBV.isEmpty()) {
                    z = true;
                }
                this.zzaAG.unlock();
            }
            return z;
        } finally {
            this.zzaAG.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public String zzwr() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
}
