package com.airbnb.android.core;

import android.content.Context;
import net.danlew.android.joda.JodaTimeAndroid;

public final class JodaTimeInitializer {
    private static boolean initialized;

    public static synchronized void initalize(Context context) {
        synchronized (JodaTimeInitializer.class) {
            if (!initialized) {
                JodaTimeAndroid.init(context);
                initialized = true;
            }
        }
    }
}
