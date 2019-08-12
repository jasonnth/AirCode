package com.facebook.buck.android.support.exopackage;

import android.content.res.Configuration;

public interface ApplicationLike {
    void onConfigurationChanged(Configuration configuration);

    void onCreate();

    void onLowMemory();

    void onTerminate();

    void onTrimMemory(int i);
}
