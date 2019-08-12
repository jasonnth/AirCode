package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzvl.zza;

public final class zzvf extends zzl<zzvl> {
    private final AuthCredentialsOptions zzajQ;

    public zzvf(Context context, Looper looper, zzg zzg, AuthCredentialsOptions authCredentialsOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 68, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzajQ = authCredentialsOptions;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzaE */
    public zzvl zzh(IBinder iBinder) {
        return zza.zzaG(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        return this.zzajQ == null ? new Bundle() : this.zzajQ.zzqL();
    }
}
