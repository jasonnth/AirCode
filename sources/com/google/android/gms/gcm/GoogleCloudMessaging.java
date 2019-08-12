package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzc;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p005cn.jpush.android.JPushConstants.PushService;

public class GoogleCloudMessaging {
    public static int zzbgC = 5000000;
    public static int zzbgD = 6500000;
    public static int zzbgE = 7000000;
    static GoogleCloudMessaging zzbgF;
    private static final AtomicInteger zzbgI = new AtomicInteger(1);
    private PendingIntent zzbgG;
    private Map<String, Handler> zzbgH = Collections.synchronizedMap(new HashMap());
    /* access modifiers changed from: private */
    public final BlockingQueue<Intent> zzbgJ = new LinkedBlockingQueue();
    final Messenger zzbgK = new Messenger(new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message == null || !(message.obj instanceof Intent)) {
                Log.w("GCM", "Dropping invalid message");
            }
            Intent intent = (Intent) message.obj;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                GoogleCloudMessaging.this.zzbgJ.add(intent);
            } else if (!GoogleCloudMessaging.this.zzn(intent)) {
                intent.setPackage(GoogleCloudMessaging.this.zzqn.getPackageName());
                GoogleCloudMessaging.this.zzqn.sendBroadcast(intent);
            }
        }
    });
    /* access modifiers changed from: private */
    public Context zzqn;

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (zzbgF == null) {
                zzbgF = new GoogleCloudMessaging();
                zzbgF.zzqn = context.getApplicationContext();
            }
            googleCloudMessaging = zzbgF;
        }
        return googleCloudMessaging;
    }

    private String zzGS() {
        String valueOf = String.valueOf("google.rpc");
        String valueOf2 = String.valueOf(String.valueOf(zzbgI.getAndIncrement()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    static String zza(Intent intent, String str) throws IOException {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = intent.getStringExtra(str);
        if (stringExtra != null) {
            return stringExtra;
        }
        String stringExtra2 = intent.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    public static int zzbv(Context context) {
        String zzbA = zzc.zzbA(context);
        if (zzbA != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(zzbA, 0);
                if (packageInfo != null) {
                    return packageInfo.versionCode;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public boolean zzn(Intent intent) {
        String stringExtra = intent.getStringExtra("In-Reply-To");
        if (stringExtra == null && intent.hasExtra("error")) {
            stringExtra = intent.getStringExtra("google.message_id");
        }
        if (stringExtra != null) {
            Handler handler = (Handler) this.zzbgH.remove(stringExtra);
            if (handler != null) {
                Message obtain = Message.obtain();
                obtain.obj = intent;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }

    public void close() {
        zzbgF = null;
        zza.zzbgn = null;
        zzGT();
    }

    public String getMessageType(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra("message_type");
        return stringExtra == null ? "gcm" : stringExtra;
    }

    @Deprecated
    public synchronized String register(String... strArr) throws IOException {
        return zza(zzc.zzbz(this.zzqn), strArr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void zzGT() {
        if (this.zzbgG != null) {
            this.zzbgG.cancel();
            this.zzbgG = null;
        }
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public Intent zza(Bundle bundle, boolean z) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        } else if (zzbv(this.zzqn) < 0) {
            throw new IOException("Google Play Services missing");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Intent intent = new Intent(z ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(zzc.zzbA(this.zzqn));
            zzo(intent);
            intent.putExtra("google.message_id", zzGS());
            intent.putExtras(bundle);
            intent.putExtra("google.messenger", this.zzbgK);
            if (z) {
                this.zzqn.sendBroadcast(intent);
            } else {
                this.zzqn.startService(intent);
            }
            try {
                return (Intent) this.zzbgJ.poll(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    @Deprecated
    public synchronized String zza(boolean z, String... strArr) throws IOException {
        String zza;
        String zzbA = zzc.zzbA(this.zzqn);
        if (zzbA == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String zzf = zzf(strArr);
        Bundle bundle = new Bundle();
        if (zzbA.contains(".gsf")) {
            bundle.putString("legacy.sender", zzf);
            zza = InstanceID.getInstance(this.zzqn).getToken(zzf, "GCM", bundle);
        } else {
            bundle.putString("sender", zzf);
            zza = zza(zza(bundle, z), "registration_id");
        }
        return zza;
    }

    /* access modifiers changed from: 0000 */
    public String zzf(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',').append(strArr[i]);
        }
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public synchronized void zzo(Intent intent) {
        if (this.zzbgG == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.zzbgG = PendingIntent.getBroadcast(this.zzqn, 0, intent2, 0);
        }
        intent.putExtra(PushService.PARAM_APP, this.zzbgG);
    }
}
