package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzaad.zza;

abstract class zzvd<R extends Result> extends zza<R, zzvf> {
    zzvd(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    public /* synthetic */ void setResult(Object obj) {
        super.zzb((Result) obj);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzvl zzvl) throws DeadObjectException, RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzvf zzvf) throws DeadObjectException, RemoteException {
        zza(zzvf.getContext(), (zzvl) zzvf.zzxD());
    }
}
