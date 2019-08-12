package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzary;
import com.google.android.gms.internal.zzasb;
import com.google.android.gms.internal.zzash;
import com.google.android.gms.internal.zzasp;

public class LocationServices {
    public static final Api<NoOptions> API = new Api<>("LocationServices.API", zzaie, zzaid);
    public static final FusedLocationProviderApi FusedLocationApi = new zzary();
    public static final GeofencingApi GeofencingApi = new zzasb();
    public static final SettingsApi SettingsApi = new zzasp();
    private static final zzf<zzash> zzaid = new zzf<>();
    private static final com.google.android.gms.common.api.Api.zza<zzash, NoOptions> zzaie = new com.google.android.gms.common.api.Api.zza<zzash, NoOptions>() {
        /* renamed from: zzq */
        public zzash zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzash(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", zzg);
        }
    };

    public static abstract class zza<R extends Result> extends com.google.android.gms.internal.zzaad.zza<R, zzash> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.API, googleApiClient);
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }
    }

    public static zzash zzj(GoogleApiClient googleApiClient) {
        boolean z = true;
        zzac.zzb(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        zzash zzash = (zzash) googleApiClient.zza((zzc<C>) zzaid);
        if (zzash == null) {
            z = false;
        }
        zzac.zza(z, (Object) "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzash;
    }
}
