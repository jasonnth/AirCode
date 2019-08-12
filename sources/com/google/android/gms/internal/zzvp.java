package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.zzb;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzvr.zza;

public final class zzvp extends zzl<zzvr> {
    private final Bundle zzaje;

    public zzvp(Context context, Looper looper, zzg zzg, zzb zzb, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 16, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzaje = zzb == null ? new Bundle() : zzb.zzqU();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzaH */
    public zzvr zzh(IBinder iBinder) {
        return zza.zzaJ(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.auth.service.START";
    }

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        return this.zzaje;
    }

    public boolean zzrd() {
        zzg zzxW = zzxW();
        return !TextUtils.isEmpty(zzxW.getAccountName()) && !zzxW.zzc(com.google.android.gms.auth.api.zza.API).isEmpty();
    }
}
