package com.appboy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageManager;

@TargetApi(14)
public class AppboyLifecycleCallbackListener implements ActivityLifecycleCallbacks {
    private final boolean mRegisterInAppMessageManager;
    private final boolean mSessionHandlingEnabled;

    public AppboyLifecycleCallbackListener() {
        this(true, true);
    }

    public AppboyLifecycleCallbackListener(boolean sessionHandlingEnabled, boolean registerInAppMessageManager) {
        this.mRegisterInAppMessageManager = registerInAppMessageManager;
        this.mSessionHandlingEnabled = sessionHandlingEnabled;
    }

    public void onActivityStarted(Activity activity) {
        if (this.mSessionHandlingEnabled) {
            Appboy.getInstance(activity.getApplicationContext()).openSession(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.mSessionHandlingEnabled) {
            Appboy.getInstance(activity.getApplicationContext()).closeSession(activity);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.mRegisterInAppMessageManager) {
            AppboyInAppMessageManager.getInstance().registerInAppMessageManager(activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.mRegisterInAppMessageManager) {
            AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(activity);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.mRegisterInAppMessageManager) {
            AppboyInAppMessageManager.getInstance().ensureSubscribedToInAppMessageEvents(activity.getApplicationContext());
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
