package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzase.zza;

public class zzarv extends zzl<zzase> {
    private final String zzbkr;
    protected final zzaso<zzase> zzbks = new zzaso<zzase>() {
        /* renamed from: zzIn */
        public zzase zzxD() throws DeadObjectException {
            return (zzase) zzarv.this.zzxD();
        }

        public void zzxC() {
            zzarv.this.zzxC();
        }
    };

    public zzarv(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, 23, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzbkr = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzdf */
    public zzase zzh(IBinder iBinder) {
        return zza.zzdi(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    /* access modifiers changed from: protected */
    public Bundle zzqL() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzbkr);
        return bundle;
    }
}
