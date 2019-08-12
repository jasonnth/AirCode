package com.airbnb.android;

import android.support.multidex.MultiDex;
import com.facebook.buck.android.support.exopackage.ExopackageApplication;

public class AppShell extends ExopackageApplication {
    private static final String APP_NAME = "com.airbnb.android.ApplicationProxy";
    private static final boolean IS_EXOPACKAGE = false;

    public AppShell() {
        super(APP_NAME, 0);
    }

    /* access modifiers changed from: protected */
    public void onBaseContextAttached() {
        MultiDex.install(this);
    }
}
