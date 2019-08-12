package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.internal.zzaac;
import com.google.android.gms.internal.zzaac.zza;
import com.google.android.gms.internal.zzbth;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    private static final List<String> zzbWD = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzbWE = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzbWF = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzbWG = Arrays.asList(new String[0]);
    private static final Set<String> zzbWH = Collections.emptySet();
    static final Map<String, FirebaseApp> zzbhH = new ArrayMap();
    /* access modifiers changed from: private */
    public static final Object zztX = new Object();
    private final String mName;
    private final FirebaseOptions zzbWI;
    private final AtomicBoolean zzbWJ = new AtomicBoolean(false);
    private final AtomicBoolean zzbWK = new AtomicBoolean();
    private final List<Object> zzbWL = new CopyOnWriteArrayList();
    private final List<zzb> zzbWM = new CopyOnWriteArrayList();
    private final List<Object> zzbWN = new CopyOnWriteArrayList();
    private final Context zzwi;

    public interface zzb {
        void zzas(boolean z);
    }

    @TargetApi(24)
    private static class zzc extends BroadcastReceiver {
        private static AtomicReference<zzc> zzbWP = new AtomicReference<>();
        private final Context zzwi;

        public zzc(Context context) {
            this.zzwi = context;
        }

        /* access modifiers changed from: private */
        public static void zzcm(Context context) {
            if (zzbWP.get() == null) {
                zzc zzc = new zzc(context);
                if (zzbWP.compareAndSet(null, zzc)) {
                    context.registerReceiver(zzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zztX) {
                for (FirebaseApp zza : FirebaseApp.zzbhH.values()) {
                    zza.zzVa();
                }
            }
            unregister();
        }

        public void unregister() {
            this.zzwi.unregisterReceiver(this);
        }
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzwi = (Context) zzac.zzw(context);
        this.mName = zzac.zzdr(str);
        this.zzbWI = (FirebaseOptions) zzac.zzw(firebaseOptions);
    }

    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zztX) {
            firebaseApp = (FirebaseApp) zzbhH.get("[DEFAULT]");
            if (firebaseApp == null) {
                String valueOf = String.valueOf(zzu.zzzr());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(valueOf).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp initializeApp;
        synchronized (zztX) {
            if (zzbhH.containsKey("[DEFAULT]")) {
                initializeApp = getInstance();
            } else {
                FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
                initializeApp = fromResource == null ? null : initializeApp(context, fromResource);
            }
        }
        return initializeApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, "[DEFAULT]");
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzbth zzcx = zzbth.zzcx(context);
        zzcl(context);
        String zzis = zzis(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zztX) {
            zzac.zza(!zzbhH.containsKey(zzis), (Object) new StringBuilder(String.valueOf(zzis).length() + 33).append("FirebaseApp name ").append(zzis).append(" already exists!").toString());
            zzac.zzb(context, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, zzis, firebaseOptions);
            zzbhH.put(zzis, firebaseApp);
        }
        zzcx.zzg(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbWD);
        if (firebaseApp.zzUX()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbWE);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzbWF);
        }
        return firebaseApp;
    }

    private void zzUW() {
        zzac.zza(!this.zzbWK.get(), (Object) "FirebaseApp was deleted");
    }

    /* access modifiers changed from: private */
    public void zzVa() {
        zza(FirebaseApp.class, this, zzbWD);
        if (zzUX()) {
            zza(FirebaseApp.class, this, zzbWE);
            zza(Context.class, this.zzwi, zzbWF);
        }
    }

    private <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzwi);
        if (isDeviceProtectedStorage) {
            zzc.zzcm(this.zzwi);
        }
        for (String str : iterable) {
            if (isDeviceProtectedStorage) {
                try {
                    if (!zzbWG.contains(str)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (zzbWH.contains(str)) {
                        throw new IllegalStateException(String.valueOf(str).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(str).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(str).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (IllegalAccessException e4) {
                    String str2 = "FirebaseApp";
                    String str3 = "Failed to initialize ";
                    String valueOf = String.valueOf(str);
                    Log.wtf(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e4);
                }
            }
            Method method = Class.forName(str).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    private void zzaV(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zzb zzas : this.zzbWM) {
            zzas.zzas(z);
        }
    }

    public static void zzas(boolean z) {
        synchronized (zztX) {
            Iterator it = new ArrayList(zzbhH.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.zzbWJ.get()) {
                    firebaseApp.zzaV(z);
                }
            }
        }
    }

    @TargetApi(14)
    private static void zzcl(Context context) {
        zzt.zzzg();
        if (context.getApplicationContext() instanceof Application) {
            zzaac.zza((Application) context.getApplicationContext());
            zzaac.zzvB().zza((zza) new zza() {
                public void zzas(boolean z) {
                    FirebaseApp.zzas(z);
                }
            });
        }
    }

    private static String zzis(String str) {
        return str.trim();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        return this.mName.equals(((FirebaseApp) obj).getName());
    }

    public Context getApplicationContext() {
        zzUW();
        return this.zzwi;
    }

    public String getName() {
        zzUW();
        return this.mName;
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return zzaa.zzv(this).zzg("name", this.mName).zzg("options", this.zzbWI).toString();
    }

    public boolean zzUX() {
        return "[DEFAULT]".equals(getName());
    }
}
