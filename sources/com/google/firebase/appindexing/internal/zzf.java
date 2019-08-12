package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zzabs;
import com.google.android.gms.internal.zzabv;
import com.google.android.gms.internal.zzto;
import com.google.android.gms.internal.zzug;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzuj;
import com.google.android.gms.internal.zzul.zzd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseUserActions;

public final class zzf extends FirebaseUserActions {
    private zza zzbXD;

    private static class zza extends zzc<NoOptions> {
        zza(Context context) {
            super(context, zzto.zzagZ, null, (zzabs) new com.google.firebase.zza());
        }
    }

    private static abstract class zzb extends zzabv<zzuj, Void> implements com.google.android.gms.internal.zzaad.zzb<Status> {
        protected TaskCompletionSource<Void> zzazE;

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzug zzug) throws RemoteException;

        /* access modifiers changed from: protected */
        public final void zza(zzuj zzuj, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
            this.zzazE = taskCompletionSource;
            zza((zzug) zzuj.zzxD());
        }

        /* renamed from: zzcd */
        public void setResult(Status status) {
            if (status.isSuccess()) {
                this.zzazE.setResult(null);
            } else {
                this.zzazE.setException(zzs.zzb(status, "User Action indexing error, please try again."));
            }
        }
    }

    public zzf(Context context) {
        this.zzbXD = new zza(context);
    }

    private Task<Void> zza(int i, Action action) {
        if (action == null) {
            return Tasks.forException(new NullPointerException("Action cannot be null."));
        }
        if (!(action instanceof zza)) {
            return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Action objects are not allowed. Please use the 'Actions' or 'ActionBuilder' class for creating Action objects."));
        }
        try {
            zza zza2 = (zza) action;
            zzs.zziu(zza2.zzVn());
            String zzVo = zza2.zzVo();
            if (zzVo != null) {
                zzs.zziv(zzVo);
            }
            final zza[] zzaArr = {(zza) action};
            zzaArr[0].zzVp().zzpY(i);
            return this.zzbXD.doWrite((zzabv<A, TResult>) new zzb(this) {
                /* access modifiers changed from: protected */
                public void zza(zzug zzug) throws RemoteException {
                    zzug.zza((zzuh) new zzd(this), zzaArr);
                }
            });
        } catch (FirebaseAppIndexingInvalidArgumentException e) {
            return Tasks.forException(e);
        }
    }

    public Task<Void> end(Action action) {
        return zza(2, action);
    }
}
