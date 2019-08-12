package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class GcmTaskService extends Service {
    public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
    public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    /* access modifiers changed from: private */
    public ComponentName componentName;
    private final Object lock = new Object();
    private final Set<String> zzbgv = new HashSet();
    private int zzbgw;
    private Messenger zzbgx;
    /* access modifiers changed from: private */
    public ExecutorService zzqp;

    @TargetApi(21)
    private class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        private void zzc(Message message) {
            Bundle data = message.getData();
            if (data != null) {
                Messenger messenger = message.replyTo;
                if (messenger != null) {
                    GcmTaskService.this.zzqp.execute(new zzb(data.getString("tag"), messenger, data.getBundle("extras"), (List<Uri>) data.getParcelableArrayList("triggered_uris")));
                }
            }
        }

        private void zzd(Message message) {
            if (Log.isLoggable("GcmTaskService", 3)) {
                String valueOf = String.valueOf(message);
                Log.d("GcmTaskService", new StringBuilder(String.valueOf(valueOf).length() + 45).append("ignoring unimplemented stop message for now: ").append(valueOf).toString());
            }
        }

        public void handleMessage(Message message) {
            if (!zzy.zzc(GcmTaskService.this, message.sendingUid, "com.google.android.gms")) {
                Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
                return;
            }
            switch (message.what) {
                case 1:
                    zzc(message);
                    return;
                case 2:
                    zzd(message);
                    return;
                case 4:
                    GcmTaskService.this.onInitializeTasks();
                    return;
                default:
                    String valueOf = String.valueOf(message);
                    Log.e("GcmTaskService", new StringBuilder(String.valueOf(valueOf).length() + 31).append("Unrecognized message received: ").append(valueOf).toString());
                    return;
            }
        }
    }

    private class zzb implements Runnable {
        private final Bundle mExtras;
        private final Messenger mMessenger;
        private final String mTag;
        private final List<Uri> zzbgA;
        private final zzb zzbgB;

        zzb(String str, IBinder iBinder, Bundle bundle, List<Uri> list) {
            this.mTag = str;
            this.zzbgB = com.google.android.gms.gcm.zzb.zza.zzcV(iBinder);
            this.mExtras = bundle;
            this.zzbgA = list;
            this.mMessenger = null;
        }

        zzb(String str, Messenger messenger, Bundle bundle, List<Uri> list) {
            this.mTag = str;
            this.mMessenger = messenger;
            this.mExtras = bundle;
            this.zzbgA = list;
            this.zzbgB = null;
        }

        private boolean zzGR() {
            return this.mMessenger != null;
        }

        private void zzjB(int i) throws RemoteException {
            if (zzGR()) {
                this.mMessenger.send(zzjC(i));
            } else {
                this.zzbgB.zzjD(i);
            }
        }

        private Message zzjC(int i) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.arg1 = i;
            Bundle bundle = new Bundle();
            bundle.putParcelable("component", GcmTaskService.this.componentName);
            bundle.putString("tag", this.mTag);
            obtain.setData(bundle);
            return obtain;
        }

        public void run() {
            try {
                zzjB(GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras, this.zzbgA)));
                if (!zzGR()) {
                    GcmTaskService.this.zzeD(this.mTag);
                }
            } catch (RemoteException e) {
                String str = "GcmTaskService";
                String str2 = "Error reporting result of operation to scheduler for ";
                String valueOf = String.valueOf(this.mTag);
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                if (!zzGR()) {
                    GcmTaskService.this.zzeD(this.mTag);
                }
            } catch (Throwable th) {
                if (!zzGR()) {
                    GcmTaskService.this.zzeD(this.mTag);
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzeD(String str) {
        synchronized (this.lock) {
            this.zzbgv.remove(str);
            if (this.zzbgv.isEmpty()) {
                stopSelf(this.zzbgw);
            }
        }
    }

    private void zzjA(int i) {
        synchronized (this.lock) {
            this.zzbgw = i;
            if (this.zzbgv.isEmpty()) {
                stopSelf(this.zzbgw);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        if (intent == null || !zzt.zzzo() || !SERVICE_ACTION_EXECUTE_TASK.equals(intent.getAction())) {
            return null;
        }
        return this.zzbgx.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.zzqp = zzGQ();
        this.zzbgx = new Messenger(new zza(Looper.getMainLooper()));
        this.componentName = new ComponentName(this, getClass());
    }

    public void onDestroy() {
        super.onDestroy();
        List shutdownNow = this.zzqp.shutdownNow();
        if (!shutdownNow.isEmpty()) {
            Log.e("GcmTaskService", "Shutting down, but not all tasks are finished executing. Remaining: " + shutdownNow.size());
        }
    }

    public void onInitializeTasks() {
    }

    public abstract int onRunTask(TaskParams taskParams);

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            zzjA(i2);
        } else {
            try {
                intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
                String action = intent.getAction();
                if (SERVICE_ACTION_EXECUTE_TASK.equals(action)) {
                    String stringExtra = intent.getStringExtra("tag");
                    Parcelable parcelableExtra = intent.getParcelableExtra("callback");
                    Bundle bundleExtra = intent.getBundleExtra("extras");
                    ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("triggered_uris");
                    if (!(parcelableExtra instanceof PendingCallback)) {
                        String valueOf = String.valueOf(getPackageName());
                        Log.e("GcmTaskService", new StringBuilder(String.valueOf(valueOf).length() + 47 + String.valueOf(stringExtra).length()).append(valueOf).append(" ").append(stringExtra).append(": Could not process request, invalid callback.").toString());
                    } else {
                        synchronized (this.lock) {
                            if (!this.zzbgv.add(stringExtra)) {
                                String valueOf2 = String.valueOf(getPackageName());
                                Log.w("GcmTaskService", new StringBuilder(String.valueOf(valueOf2).length() + 44 + String.valueOf(stringExtra).length()).append(valueOf2).append(" ").append(stringExtra).append(": Task already running, won't start another").toString());
                                zzjA(i2);
                            } else {
                                this.zzqp.execute(new zzb(stringExtra, ((PendingCallback) parcelableExtra).getIBinder(), bundleExtra, (List<Uri>) parcelableArrayListExtra));
                            }
                        }
                    }
                } else if (SERVICE_ACTION_INITIALIZE.equals(action)) {
                    onInitializeTasks();
                } else {
                    Log.e("GcmTaskService", new StringBuilder(String.valueOf(action).length() + 37).append("Unknown action received ").append(action).append(", terminating").toString());
                }
                zzjA(i2);
            } finally {
                zzjA(i2);
            }
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public ExecutorService zzGQ() {
        return Executors.newFixedThreadPool(2, new ThreadFactory(this) {
            private final AtomicInteger zzbgy = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "gcm-task#" + this.zzbgy.getAndIncrement());
                thread.setPriority(4);
                return thread;
            }
        });
    }
}
