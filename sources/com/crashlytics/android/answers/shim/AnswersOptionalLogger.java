package com.crashlytics.android.answers.shim;

import android.util.Log;

public class AnswersOptionalLogger {
    private static final KitEventLogger logger;

    static {
        KitEventLogger theLogger = null;
        try {
            theLogger = AnswersKitEventLogger.create();
        } catch (IllegalStateException | NoClassDefFoundError e) {
        } catch (Throwable t) {
            Log.w("AnswersOptionalLogger", "Unexpected error creating AnswersKitEventLogger", t);
        }
        if (theLogger == null) {
            theLogger = NoopKitEventLogger.create();
        }
        logger = theLogger;
    }

    public static KitEventLogger get() {
        return logger;
    }
}
