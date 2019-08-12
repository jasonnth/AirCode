package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzg;

public final class zzbah {
    public static final Api<zzbaj> API = new Api<>("SignIn.API", zzaie, zzaid);
    public static final Api<zza> zzaKN = new Api<>("SignIn.INTERNAL_API", zzbEj, zzbEi);
    public static final zzf<zzbat> zzaid = new zzf<>();
    public static final com.google.android.gms.common.api.Api.zza<zzbat, zzbaj> zzaie = new com.google.android.gms.common.api.Api.zza<zzbat, zzbaj>() {
        public zzbat zza(Context context, Looper looper, zzg zzg, zzbaj zzbaj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzbat(context, looper, true, zzg, zzbaj == null ? zzbaj.zzbEl : zzbaj, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Scope zzakh = new Scope(Scopes.PROFILE);
    public static final Scope zzaki = new Scope("email");
    public static final zzf<zzbat> zzbEi = new zzf<>();
    static final com.google.android.gms.common.api.Api.zza<zzbat, zza> zzbEj = new com.google.android.gms.common.api.Api.zza<zzbat, zza>() {
        public zzbat zza(Context context, Looper looper, zzg zzg, zza zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzbat(context, looper, false, zzg, zza.zzPK(), connectionCallbacks, onConnectionFailedListener);
        }
    };

    public static class zza implements HasOptions {
        private final Bundle zzbEk;

        public Bundle zzPK() {
            return this.zzbEk;
        }
    }
}
