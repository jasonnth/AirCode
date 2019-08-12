package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzbkx;
import com.google.android.gms.internal.zzbky;
import com.google.android.gms.internal.zzbla;
import com.google.android.gms.internal.zzblb;
import com.google.android.gms.wallet.firstparty.zzc;
import com.google.android.gms.wallet.wobs.zzr;
import java.util.Locale;

public final class Wallet {
    public static final Api<WalletOptions> API = new Api<>("Wallet.API", zzaie, zzaid);
    public static final C1458Payments Payments = new zzbkx();
    private static final zzf<zzbky> zzaid = new zzf<>();
    private static final com.google.android.gms.common.api.Api.zza<zzbky, WalletOptions> zzaie = new com.google.android.gms.common.api.Api.zza<zzbky, WalletOptions>() {
        public zzbky zza(Context context, Looper looper, zzg zzg, WalletOptions walletOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            if (walletOptions == null) {
                walletOptions = new WalletOptions();
            }
            return new zzbky(context, looper, zzg, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, walletOptions.theme, walletOptions.zzbRv);
        }
    };
    public static final zzr zzbRt = new zzblb();
    public static final zzc zzbRu = new zzbla();

    public static final class WalletOptions implements HasOptions {
        public final int environment;
        public final int theme;
        final boolean zzbRv;

        public static final class Builder {
            /* access modifiers changed from: private */
            public int mTheme = 0;
            /* access modifiers changed from: private */
            public int zzbRw = 3;
            /* access modifiers changed from: private */
            public boolean zzbRx = true;

            public WalletOptions build() {
                return new WalletOptions(this);
            }

            public Builder setEnvironment(int i) {
                if (i == 0 || i == 0 || i == 2 || i == 1 || i == 3) {
                    this.zzbRw = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(i)}));
            }

            public Builder setTheme(int i) {
                if (i == 0 || i == 1) {
                    this.mTheme = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(i)}));
            }
        }

        private WalletOptions() {
            this(new Builder());
        }

        private WalletOptions(Builder builder) {
            this.environment = builder.zzbRw;
            this.theme = builder.mTheme;
            this.zzbRv = builder.zzbRx;
        }
    }

    public static abstract class zza<R extends Result> extends com.google.android.gms.internal.zzaad.zza<R, zzbky> {
        public zza(GoogleApiClient googleApiClient) {
            super(Wallet.API, googleApiClient);
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzbky zzbky) throws RemoteException;
    }

    public static abstract class zzb extends zza<Status> {
        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }
}
