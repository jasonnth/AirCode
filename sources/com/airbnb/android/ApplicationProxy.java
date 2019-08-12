package com.airbnb.android;

import android.app.Application;
import android.support.p002v7.app.AppCompatDelegate;
import android.util.Log;
import com.airbnb.android.core.net.OkHttpInitializerImpl;
import com.airbnb.android.core.net.StethoInitializerImpl;
import com.airbnb.android.lib.AirbnbApplication;
import com.facebook.buck.android.support.exopackage.DefaultApplicationLike;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.C1093Id;

public class ApplicationProxy extends DefaultApplicationLike {
    private static final String TAG = "ApplicationProxy";
    private static final C1092As ignore = C1092As.EXISTING_PROPERTY;
    private static final C1093Id these = C1093Id.CLASS;
    private final AirbnbApplication application;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public ApplicationProxy(Application application2) {
        this.application = buildApplication(application2);
    }

    private AirbnbApplication buildApplication(Application application2) {
        boolean isTestApplication = TestApplicationDetector.detect();
        Log.i(TAG, "isTestApplication=" + isTestApplication);
        if (!isTestApplication) {
            return new AirbnbApplication(application2, OkHttpInitializerImpl.INSTANCE, StethoInitializerImpl.INSTANCE);
        }
        return new InstrumentationAirbnbApplication(application2, OkHttpInitializerImpl.INSTANCE, StethoInitializerImpl.INSTANCE);
    }

    public void onCreate() {
        super.onCreate();
        this.application.onCreate();
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        this.application.onTrimMemory(level);
    }
}
