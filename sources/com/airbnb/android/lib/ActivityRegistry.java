package com.airbnb.android.lib;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class ActivityRegistry implements ActivityLifecycleCallbacks {
    private final List<WeakReference<Activity>> activities = new ArrayList();

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        this.activities.add(new WeakReference(activity));
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
        for (WeakReference<Activity> reference : this.activities) {
            if (activity.equals((Activity) reference.get())) {
                this.activities.remove(reference);
                return;
            }
        }
    }
}
