package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;

public class zzzo extends zzc<NoOptions> implements zzzl {

    static class zza extends com.google.android.gms.internal.zzzr.zza {
        zza() {
        }

        public void zza(Status status, long j) {
            throw new UnsupportedOperationException();
        }

        public void zzb(Status status, long j) {
            throw new UnsupportedOperationException();
        }

        public void zzv(Status status) {
            throw new UnsupportedOperationException();
        }

        public void zzw(Status status) {
            throw new UnsupportedOperationException();
        }

        public void zzx(Status status) {
            throw new UnsupportedOperationException();
        }
    }

    static final class zzb extends com.google.android.gms.internal.zzaad.zza<Status, zzzp> {
        private final zzzm zzaxS;

        zzb(zzzm zzzm, GoogleApiClient googleApiClient) {
            super(zzzk.API, googleApiClient);
            this.zzaxS = zzzm;
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Status) obj);
        }

        /* access modifiers changed from: protected */
        public void zza(zzzp zzzp) throws RemoteException {
            C42131 r0 = new zza() {
                public void zzv(Status status) {
                    zzb.this.zzb(status);
                }
            };
            try {
                zzzo.zzb(this.zzaxS);
                zzzp.zza(r0, this.zzaxS);
            } catch (RuntimeException e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
                zzB(new Status(10, "MessageProducer"));
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    zzzo(Context context) {
        super(context, zzzk.API, null, (zzabs) new zzzy());
    }

    public static zzzl zzaA(Context context) {
        return new zzzo(context);
    }

    static void zzb(zzzm zzzm) {
        if (zzzm.zzaxQ != null && zzzm.zzaxP.zzcxq.length == 0) {
            zzzm.zzaxP.zzcxq = zzzm.zzaxQ.zzuV();
        }
        if (zzzm.zzaxR != null && zzzm.zzaxP.zzcxx.length == 0) {
            zzzm.zzaxP.zzcxx = zzzm.zzaxR.zzuV();
        }
        zzzm.zzaxJ = zzbyj.zzf(zzzm.zzaxP);
    }

    public PendingResult<Status> zza(zzzm zzzm) {
        return doBestEffortWrite(new zzb(zzzm, asGoogleApiClient()));
    }
}
