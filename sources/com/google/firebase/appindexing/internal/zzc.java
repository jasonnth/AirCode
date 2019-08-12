package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;

public class zzc extends zzl<zzl> {
    static final Api<NoOptions> API = new Api<>("AppIndexing.API", zzaie, zzaid);
    private static final zzf<zzc> zzaid = new zzf<>();
    private static final zza<zzc, NoOptions> zzaie = new zza<zzc, NoOptions>() {
        /* renamed from: zzw */
        public zzc zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzc(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };

    public zzc(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 113, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.firebase.appindexing.internal.IAppIndexingService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.icing.APP_INDEXING_SERVICE";
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfG */
    public zzl zzh(IBinder iBinder) {
        return zzl.zza.zzfI(iBinder);
    }
}
