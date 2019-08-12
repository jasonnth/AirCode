package com.facebook.buck.android.support.exopackage;

import android.app.Application;
import android.content.res.Configuration;

public class DefaultApplicationLike implements ApplicationLike {
    public DefaultApplicationLike() {
    }

    public DefaultApplicationLike(Application application) {
    }

    public void onCreate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onTerminate() {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
