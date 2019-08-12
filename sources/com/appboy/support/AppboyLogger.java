package com.appboy.support;

import android.util.Log;
import com.appboy.Constants;

public class AppboyLogger {
    public static int LogLevel = 4;

    public static void setLogLevel(int logLevel) {
        LogLevel = logLevel;
    }

    /* renamed from: d */
    public static int m1733d(String tag, String msg) {
        if (LogLevel <= 3) {
            return Log.d(tag, msg);
        }
        return 0;
    }

    /* renamed from: d */
    public static int m1734d(String tag, String msg, Throwable tr) {
        if (LogLevel <= 3) {
            return Log.d(tag, msg, tr);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m1737i(String tag, String msg) {
        if (LogLevel <= 4) {
            return Log.i(tag, msg);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m1738i(String tag, String msg, Throwable tr) {
        if (LogLevel <= 4) {
            return Log.i(tag, msg, tr);
        }
        return 0;
    }

    /* renamed from: w */
    public static int m1739w(String tag, String msg) {
        if (LogLevel <= 5) {
            return Log.w(tag, msg);
        }
        return 0;
    }

    /* renamed from: w */
    public static int m1740w(String tag, String msg, Throwable tr) {
        if (LogLevel <= 5) {
            return Log.w(tag, msg, tr);
        }
        return 0;
    }

    /* renamed from: e */
    public static int m1735e(String tag, String msg) {
        if (LogLevel <= 6) {
            return Log.e(tag, msg);
        }
        return 0;
    }

    /* renamed from: e */
    public static int m1736e(String tag, String msg, Throwable tr) {
        if (LogLevel <= 6) {
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

    public static String getAppboyLogTag(Class classForTag) {
        return Constants.APPBOY_LOG_TAG_PREFIX + "." + classForTag.getName();
    }
}
