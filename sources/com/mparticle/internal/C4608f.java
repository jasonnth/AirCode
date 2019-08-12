package com.mparticle.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

@TargetApi(14)
/* renamed from: com.mparticle.internal.f */
class C4608f implements ActivityLifecycleCallbacks {

    /* renamed from: a */
    private AppStateManager f3750a;

    public C4608f(AppStateManager appStateManager) {
        this.f3750a = appStateManager;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        this.f3750a.onActivityCreated(activity, savedInstanceState);
    }

    public void onActivityStarted(Activity activity) {
        this.f3750a.onActivityStarted(activity);
    }

    public void onActivityResumed(Activity activity) {
        this.f3750a.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.f3750a.onActivityPaused(activity);
    }

    public void onActivityStopped(Activity activity) {
        this.f3750a.onActivityStopped(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        this.f3750a.onActivitySaveInstanceState(activity, outState);
    }

    public void onActivityDestroyed(Activity activity) {
        this.f3750a.onActivityDestroyed(activity);
    }
}
