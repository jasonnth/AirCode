package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Executor;

final class zzh<TResult> extends Task<TResult> {
    private final zzg<TResult> zzbNH = new zzg<>();
    private boolean zzbNI;
    private TResult zzbNJ;
    private Exception zzbNK;
    private final Object zzrJ = new Object();

    zzh() {
    }

    private void zzTI() {
        zzac.zza(this.zzbNI, (Object) "Task is not yet complete");
    }

    private void zzTJ() {
        zzac.zza(!this.zzbNI, (Object) "Task is already complete");
    }

    private void zzTK() {
        synchronized (this.zzrJ) {
            if (this.zzbNI) {
                this.zzbNH.zza((Task<TResult>) this);
            }
        }
    }

    public Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    public Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzbNH.zza((zzf<TResult>) new zzc<TResult>(executor, onCompleteListener));
        zzTK();
        return this;
    }

    public Exception getException() {
        Exception exc;
        synchronized (this.zzrJ) {
            exc = this.zzbNK;
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.zzrJ) {
            zzTI();
            if (this.zzbNK != null) {
                throw new RuntimeExecutionException(this.zzbNK);
            }
            tresult = this.zzbNJ;
        }
        return tresult;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNI;
        }
        return z;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNI && this.zzbNK == null;
        }
        return z;
    }

    public void setException(Exception exc) {
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            zzTJ();
            this.zzbNI = true;
            this.zzbNK = exc;
        }
        this.zzbNH.zza((Task<TResult>) this);
    }

    public void setResult(TResult tresult) {
        synchronized (this.zzrJ) {
            zzTJ();
            this.zzbNI = true;
            this.zzbNJ = tresult;
        }
        this.zzbNH.zza((Task<TResult>) this);
    }

    public boolean trySetException(Exception exc) {
        boolean z = true;
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            if (this.zzbNI) {
                z = false;
            } else {
                this.zzbNI = true;
                this.zzbNK = exc;
                this.zzbNH.zza((Task<TResult>) this);
            }
        }
        return z;
    }
}
