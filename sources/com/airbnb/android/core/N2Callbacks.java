package com.airbnb.android.core;

import android.util.Log;
import com.airbnb.android.core.glide.ImageLoadingAnalytics;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.C0977N2.Callbacks;

public class N2Callbacks implements Callbacks {
    private static final String TAG = "N2Callbacks";
    private final AirbnbPreferences preferences;

    public N2Callbacks(AirbnbPreferences preferences2) {
        this.preferences = preferences2;
    }

    public void onImageLoaded(long loadTime, boolean fromMemoryCache) {
        ImageLoadingAnalytics.maybeLogImageLoaded(loadTime, fromMemoryCache);
    }

    public void onImageLoadCleared(long loadTime) {
        ImageLoadingAnalytics.maybeLogImageLoadCleared(loadTime);
    }

    public void onImageLoadError(long loadTime, Exception exception) {
        Log.e(TAG, "onLoadFailed", exception);
        ImageLoadingAnalytics.maybeLogImageLoadError(loadTime, exception);
    }

    public boolean shouldOverrideFont() {
        return this.preferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_FONT_OVERRIDE, false);
    }

    public void onNotifyException(Exception exception) {
        BugsnagWrapper.notify((Throwable) exception);
    }

    public void onThrowOrNotify(RuntimeException exception) {
        BugsnagWrapper.throwOrNotify(exception);
    }

    public boolean isDevelopmentBuild() {
        return BuildHelper.isDevelopmentBuild();
    }
}
