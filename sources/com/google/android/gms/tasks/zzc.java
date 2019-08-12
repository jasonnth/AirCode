package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private final Executor zzbFP;
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> zzbNx;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzc(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzbFP = executor;
        this.zzbNx = onCompleteListener;
    }

    public void onComplete(final Task<TResult> task) {
        synchronized (this.zzrJ) {
            if (this.zzbNx != null) {
                this.zzbFP.execute(new Runnable() {
                    public void run() {
                        synchronized (zzc.this.zzrJ) {
                            if (zzc.this.zzbNx != null) {
                                zzc.this.zzbNx.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
