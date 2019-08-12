package com.airbnb.android.core;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public class AppForegroundDetector implements ActivityLifecycleCallbacks {
    private static final int BACKGROUND_DETECT_DELAY_MS = 5000;
    /* access modifiers changed from: private */
    public final List<AppForegroundListener> appForegroundListeners = new ArrayList();
    /* access modifiers changed from: private */
    public boolean appInForeground;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable markAppBackgroundRunnable = new Runnable() {
        public void run() {
            AppForegroundDetector.this.appInForeground = false;
            for (AppForegroundListener listener : AppForegroundDetector.this.appForegroundListeners) {
                listener.onAppBackgrounded();
            }
        }
    };

    public interface AppForegroundListener {
        void onAppBackgrounded();

        void onAppForegrounded(Activity activity);
    }

    public boolean isAppInForeground() {
        return this.appInForeground;
    }

    public void registerAppForegroundListener(AppForegroundListener listener) {
        this.appForegroundListeners.add(listener);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.handler.removeCallbacks(this.markAppBackgroundRunnable);
        if (!this.appInForeground) {
            this.appInForeground = true;
            for (AppForegroundListener listener : this.appForegroundListeners) {
                listener.onAppForegrounded(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        this.handler.postDelayed(this.markAppBackgroundRunnable, 5000);
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
