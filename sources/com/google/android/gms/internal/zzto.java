package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzto {
    public static final zzf<zzuj> zzagX = new zzf<>();
    private static final zza<zzuj, NoOptions> zzagY = new zza<zzuj, NoOptions>() {
        public zzuj zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzuj(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Api<NoOptions> zzagZ = new Api<>("AppDataSearch.LIGHTWEIGHT_API", zzagY, zzagX);
    public static final zzuf zzaha = new zzul();
}
