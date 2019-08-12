package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzabb;
import com.google.android.gms.internal.zzabs;
import com.google.android.gms.internal.zzabv;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;
import java.util.concurrent.Executor;

public final class zzd extends FirebaseAppIndex {
    private zzc zzbXr;

    private static class zza extends com.google.android.gms.common.api.zzc<NoOptions> {
        public zza(Context context) {
            super(context, zzc.API, null, Looper.getMainLooper(), (zzabs) new com.google.firebase.zza());
        }
    }

    private static abstract class zzb extends zzabv<zzc, Status> {
        /* access modifiers changed from: private */
        public TaskCompletionSource<Status> zzazE;

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public zzabb zzVw() {
            return new com.google.android.gms.internal.zzabb.zza() {
                public void zzp(Status status) throws RemoteException {
                    zzb.this.zzazE.setResult(status);
                }
            };
        }

        /* access modifiers changed from: protected */
        public final void zza(zzc zzc, TaskCompletionSource<Status> taskCompletionSource) throws RemoteException {
            this.zzazE = taskCompletionSource;
            zza((zzl) zzc.zzxD());
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzl zzl) throws RemoteException;
    }

    static class zzc implements OnCompleteListener<Void>, Executor {
        public static int MAX_RETRIES = 10;
        public static long zzbXv = 250;
        public static double zzbXw = 1.5d;
        public static double zzbXx = 0.25d;
        /* access modifiers changed from: private */
        public final Handler mHandler;
        private final com.google.android.gms.common.api.zzc<?> zzaDe;
        private Task<Void> zzbXy = null;

        public zzc(com.google.android.gms.common.api.zzc<?> zzc) {
            this.zzaDe = zzc;
            this.mHandler = new Handler(zzc.getLooper());
        }

        /* access modifiers changed from: private */
        public void zza(final zzb zzb, final TaskCompletionSource<Void> taskCompletionSource, final int i) {
            this.zzaDe.doWrite((zzabv<A, TResult>) zzb).addOnCompleteListener(this, new OnCompleteListener<Status>() {
                public void onComplete(Task<Status> task) {
                    if (i < zzc.MAX_RETRIES && zzc.zzc(task)) {
                        C42741 r0 = new Runnable() {
                            public void run() {
                                zzc.this.zza(zzb, taskCompletionSource, i + 1);
                            }
                        };
                        long zzqa = zzc.zzqa(i);
                        if (zzc.this.mHandler.postDelayed(r0, zzqa)) {
                            zzm.zzit("Task will be retried in " + zzqa + " ms");
                            return;
                        }
                        zzm.zzit("Failed to schedule retry -- failing immediately!");
                    }
                    if (task.isSuccessful()) {
                        Status status = (Status) task.getResult();
                        if (status.isSuccess()) {
                            taskCompletionSource.setResult(null);
                        } else {
                            taskCompletionSource.setException(zzs.zzb(status, "Indexing error, please try again."));
                        }
                    } else {
                        taskCompletionSource.setException(task.getException());
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public static boolean zzc(Task<Status> task) {
            if (task.isSuccessful()) {
                return zze.zzqb(((Status) task.getResult()).getStatusCode());
            }
            return false;
        }

        static long zzqa(int i) {
            return (long) (((double) zzbXv) * Math.pow(zzbXw, (double) i) * ((((Math.random() * 2.0d) - 1.0d) * zzbXx) + 1.0d));
        }

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }

        public synchronized void onComplete(Task<Void> task) {
            if (task == this.zzbXy) {
                this.zzbXy = null;
            }
        }

        public Task<Void> zzb(final zzb zzb) {
            Task<Void> task;
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            Task<Void> task2 = taskCompletionSource.getTask();
            synchronized (this) {
                task = this.zzbXy;
                this.zzbXy = task2;
            }
            task2.addOnCompleteListener(this, this);
            if (task == null) {
                zza(zzb, taskCompletionSource, 0);
            } else {
                task.addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    public void onComplete(Task<Void> task) {
                        zzc.this.zza(zzb, taskCompletionSource, 0);
                    }
                });
            }
            return task2;
        }
    }

    public zzd(Context context) {
        this(context, new zza(context));
    }

    zzd(Context context, com.google.android.gms.common.api.zzc<NoOptions> zzc2) {
        this.zzbXr = new zzc(zzc2);
    }

    public Task<Void> remove(final String... strArr) {
        return this.zzbXr.zzb(new zzb(this) {
            /* access modifiers changed from: protected */
            public void zza(zzl zzl) throws RemoteException {
                zzl.zza(zzVw(), strArr);
            }
        });
    }

    public Task<Void> update(Indexable... indexableArr) {
        if (indexableArr == null) {
            return Tasks.forException(new NullPointerException("Indexables cannot be null."));
        }
        final Thing[] thingArr = new Thing[indexableArr.length];
        try {
            System.arraycopy(indexableArr, 0, thingArr, 0, indexableArr.length);
            return this.zzbXr.zzb(new zzb(this) {
                /* access modifiers changed from: protected */
                public void zza(zzl zzl) throws RemoteException {
                    zzl.zza(zzVw(), thingArr);
                }
            });
        } catch (ArrayStoreException e) {
            return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Indexable-objects are not allowed. Please use the 'Indexables'-class for creating the objects."));
        }
    }
}
