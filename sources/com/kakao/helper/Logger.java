package com.kakao.helper;

import android.util.Log;

public class Logger {
    private static volatile Logger instance;
    private LogLevel logLevel = LogLevel.Debug;

    public enum LogLevel {
        Verbose,
        Debug,
        Info,
        Warn,
        Error,
        Release
    }

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /* renamed from: w */
    public void mo44170w(String msg) {
        mo44171w("kakao-android-sdk", msg);
    }

    /* renamed from: w */
    public void mo44171w(String tag, String msg) {
        switch (this.logLevel) {
            case Verbose:
            case Debug:
            case Info:
            case Warn:
                Log.w(tag, msg);
                return;
            default:
                return;
        }
    }
}
