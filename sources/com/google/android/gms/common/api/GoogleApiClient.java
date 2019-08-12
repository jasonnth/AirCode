package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzg.zza;
import com.google.android.gms.internal.zzaaa;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaag;
import com.google.android.gms.internal.zzaat;
import com.google.android.gms.internal.zzabd;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzabq;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzbah;
import com.google.android.gms.internal.zzbai;
import com.google.android.gms.internal.zzbaj;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    public static final Set<GoogleApiClient> zzazc = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Account zzahh;
        private String zzaiq;
        private final Set<Scope> zzazd;
        private final Set<Scope> zzaze;
        private int zzazf;
        private View zzazg;
        private String zzazh;
        private final Map<Api<?>, zza> zzazi;
        private final Map<Api<?>, ApiOptions> zzazj;
        private zzabd zzazk;
        private int zzazl;
        private OnConnectionFailedListener zzazm;
        private GoogleApiAvailability zzazn;
        private Api.zza<? extends zzbai, zzbaj> zzazo;
        private final ArrayList<ConnectionCallbacks> zzazp;
        private final ArrayList<OnConnectionFailedListener> zzazq;
        private boolean zzazr;
        private Looper zzrs;

        public Builder(Context context) {
            this.zzazd = new HashSet();
            this.zzaze = new HashSet();
            this.zzazi = new ArrayMap();
            this.zzazj = new ArrayMap();
            this.zzazl = -1;
            this.zzazn = GoogleApiAvailability.getInstance();
            this.zzazo = zzbah.zzaie;
            this.zzazp = new ArrayList<>();
            this.zzazq = new ArrayList<>();
            this.zzazr = false;
            this.mContext = context;
            this.zzrs = context.getMainLooper();
            this.zzaiq = context.getPackageName();
            this.zzazh = context.getClass().getName();
        }

        public Builder(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzac.zzb(connectionCallbacks, (Object) "Must provide a connected listener");
            this.zzazp.add(connectionCallbacks);
            zzac.zzb(onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.zzazq.add(onConnectionFailedListener);
        }

        private static <C extends zze, O> C zza(Api.zza<C, O> zza, Object obj, Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzg, obj, connectionCallbacks, onConnectionFailedListener);
        }

        private Builder zza(zzabd zzabd, int i, OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(i >= 0, (Object) "clientId must be non-negative");
            this.zzazl = i;
            this.zzazm = onConnectionFailedListener;
            this.zzazk = zzabd;
            return this;
        }

        private <O extends ApiOptions> void zza(Api<O> api, O o, Scope... scopeArr) {
            HashSet hashSet = new HashSet(api.zzve().zzp(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.zzazi.put(api, new zza(hashSet));
        }

        private void zzf(GoogleApiClient googleApiClient) {
            zzaaa.zza(this.zzazk).zza(this.zzazl, googleApiClient, this.zzazm);
        }

        private GoogleApiClient zzvq() {
            zzg zzvp = zzvp();
            Api api = null;
            Map zzxN = zzvp.zzxN();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Api api2 : this.zzazj.keySet()) {
                Object obj = this.zzazj.get(api2);
                boolean z2 = zzxN.get(api2) != null;
                arrayMap.put(api2, Boolean.valueOf(z2));
                zzaag zzaag = new zzaag(api2, z2);
                arrayList.add(zzaag);
                Api.zza zzvf = api2.zzvf();
                zze zza = zza(zzvf, obj, this.mContext, this.zzrs, zzvp, zzaag, zzaag);
                arrayMap2.put(api2.zzvg(), zza);
                boolean z3 = zzvf.getPriority() == 1 ? obj != null : z;
                if (!zza.zzrr()) {
                    api2 = api;
                } else if (api != null) {
                    String valueOf = String.valueOf(api2.getName());
                    String valueOf2 = String.valueOf(api.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                z = z3;
                api = api2;
            }
            if (api != null) {
                if (z) {
                    String valueOf3 = String.valueOf(api.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf3).length() + 82).append("With using ").append(valueOf3).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                }
                zzac.zza(this.zzahh == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzac.zza(this.zzazd.equals(this.zzaze), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            return new zzaat(this.mContext, new ReentrantLock(), this.zzrs, zzvp, this.zzazn, this.zzazo, arrayMap, this.zzazp, this.zzazq, arrayMap2, this.zzazl, zzaat.zza(arrayMap2.values(), true), arrayList, false);
        }

        public Builder addApi(Api<? extends NotRequiredOptions> api) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.zzazj.put(api, null);
            List zzp = api.zzve().zzp(null);
            this.zzaze.addAll(zzp);
            this.zzazd.addAll(zzp);
            return this;
        }

        public <O extends HasOptions> Builder addApi(Api<O> api, O o) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.zzazj.put(api, o);
            List zzp = api.zzve().zzp(o);
            this.zzaze.addAll(zzp);
            this.zzazd.addAll(zzp);
            return this;
        }

        public <O extends HasOptions> Builder addApiIfAvailable(Api<O> api, O o, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.zzazj.put(api, o);
            zza(api, o, scopeArr);
            return this;
        }

        public Builder addApiIfAvailable(Api<? extends NotRequiredOptions> api, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.zzazj.put(api, null);
            zza(api, (O) null, scopeArr);
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            zzac.zzb(connectionCallbacks, (Object) "Listener must not be null");
            this.zzazp.add(connectionCallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(onConnectionFailedListener, (Object) "Listener must not be null");
            this.zzazq.add(onConnectionFailedListener);
            return this;
        }

        public Builder addScope(Scope scope) {
            zzac.zzb(scope, (Object) "Scope must not be null");
            this.zzazd.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            zzac.zzb(!this.zzazj.isEmpty(), (Object) "must call addApi() to add at least one API");
            GoogleApiClient zzvq = zzvq();
            synchronized (GoogleApiClient.zzazc) {
                GoogleApiClient.zzazc.add(zzvq);
            }
            if (this.zzazl >= 0) {
                zzf(zzvq);
            }
            return zzvq;
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, int i, OnConnectionFailedListener onConnectionFailedListener) {
            return zza(new zzabd(fragmentActivity), i, onConnectionFailedListener);
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public Builder setAccountName(String str) {
            this.zzahh = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int i) {
            this.zzazf = i;
            return this;
        }

        public Builder setHandler(Handler handler) {
            zzac.zzb(handler, (Object) "Handler must not be null");
            this.zzrs = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(View view) {
            zzac.zzb(view, (Object) "View must not be null");
            this.zzazg = view;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public Builder zze(Account account) {
            this.zzahh = account;
            return this;
        }

        public zzg zzvp() {
            zzbaj zzbaj = zzbaj.zzbEl;
            if (this.zzazj.containsKey(zzbah.API)) {
                zzbaj = (zzbaj) this.zzazj.get(zzbah.API);
            }
            return new zzg(this.zzahh, this.zzazd, this.zzazi, this.zzazf, this.zzazg, this.zzaiq, this.zzazh, zzbaj);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zzazc) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient googleApiClient : zzazc) {
                int i2 = i + 1;
                printWriter.append(str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(concat, fileDescriptor, printWriter, strArr);
                i = i2;
            }
        }
    }

    public static Set<GoogleApiClient> zzvm() {
        Set<GoogleApiClient> set;
        synchronized (zzazc) {
            set = zzazc;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract ConnectionResult getConnectionResult(Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public <C extends zze> C zza(zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(T t) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzabx zzabx) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzabq zzabq) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends zzaad.zza<? extends Result, A>> T zzb(T t) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzabx zzabx) {
        throw new UnsupportedOperationException();
    }

    public <L> zzabh<L> zzr(L l) {
        throw new UnsupportedOperationException();
    }

    public void zzvn() {
        throw new UnsupportedOperationException();
    }
}
