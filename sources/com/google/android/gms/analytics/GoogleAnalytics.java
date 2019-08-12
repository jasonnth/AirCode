package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzsc;
import com.google.android.gms.internal.zztn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzabG = new ArrayList();
    private Set<zza> zzabH = new HashSet();
    private boolean zzabI;
    private boolean zzabJ;
    private volatile boolean zzabK;
    private boolean zztZ;

    interface zza {
        void zzo(Activity activity);

        void zzp(Activity activity);
    }

    @TargetApi(14)
    class zzb implements ActivityLifecycleCallbacks {
        zzb() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zzm(activity);
        }

        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzn(activity);
        }
    }

    public GoogleAnalytics(zzsc zzsc) {
        super(zzsc);
    }

    public static GoogleAnalytics getInstance(Context context) {
        return zzsc.zzan(context).zzof();
    }

    private zztn zzmB() {
        return zzmn().zzmB();
    }

    public static void zzmx() {
        synchronized (GoogleAnalytics.class) {
            if (zzabG != null) {
                for (Runnable run : zzabG) {
                    run.run();
                }
                zzabG = null;
            }
        }
    }

    @TargetApi(14)
    public void enableAutoActivityReports(Application application) {
        int i = VERSION.SDK_INT;
        if (!this.zzabI) {
            application.registerActivityLifecycleCallbacks(new zzb());
            this.zzabI = true;
        }
    }

    public boolean getAppOptOut() {
        return this.zzabK;
    }

    public void initialize() {
        zzmw();
        this.zztZ = true;
    }

    public boolean isDryRunEnabled() {
        return this.zzabJ;
    }

    public boolean isInitialized() {
        return this.zztZ;
    }

    public Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzmn(), str, null);
            tracker.initialize();
        }
        return tracker;
    }

    public void setDryRun(boolean z) {
        this.zzabJ = z;
    }

    /* access modifiers changed from: 0000 */
    public void zza(zza zza2) {
        this.zzabH.add(zza2);
        Context context = zzmn().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzb(zza zza2) {
        this.zzabH.remove(zza2);
    }

    /* access modifiers changed from: 0000 */
    public void zzm(Activity activity) {
        for (zza zzo : this.zzabH) {
            zzo.zzo(activity);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zzmw() {
        zztn zzmB = zzmB();
        zzmB.zzpI();
        if (zzmB.zzpM()) {
            setDryRun(zzmB.zzpN());
        }
        zzmB.zzpI();
    }

    public String zzmy() {
        zzac.zzdk("getClientId can not be called from the main thread");
        return zzmn().zzoi().zzoQ();
    }

    /* access modifiers changed from: 0000 */
    public void zzn(Activity activity) {
        for (zza zzp : this.zzabH) {
            zzp.zzp(activity);
        }
    }
}
