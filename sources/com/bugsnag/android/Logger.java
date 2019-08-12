package com.bugsnag.android;

import android.util.Log;

class Logger {
    static void info(String message) {
        Log.i("Bugsnag", message);
    }

    static void warn(String message) {
        Log.w("Bugsnag", message);
    }

    static void warn(String message, Throwable e) {
        Log.w("Bugsnag", message, e);
    }
}
