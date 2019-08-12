package com.google.android.gms.tasks;

public class TaskCompletionSource<TResult> {
    private final zzh<TResult> zzbNF = new zzh<>();

    public Task<TResult> getTask() {
        return this.zzbNF;
    }

    public void setException(Exception exc) {
        this.zzbNF.setException(exc);
    }

    public void setResult(TResult tresult) {
        this.zzbNF.setResult(tresult);
    }

    public boolean trySetException(Exception exc) {
        return this.zzbNF.trySetException(exc);
    }
}
