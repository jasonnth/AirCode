package com.jumio.commons.log;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;

public class Log {
    public static final String TAG = "JumioMobileSDK";
    private static FileAppender fileAppender = null;
    private static LogLevel logLevel = LogLevel.OFF;

    public enum LogLevel {
        OFF,
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    public static void allowFileLogging(Context context, boolean fileLoggingAllowed) {
        if (fileLoggingAllowed) {
            fileAppender = new FileAppender(context);
        } else if (fileAppender != null) {
            fileAppender.destroy();
            fileAppender = null;
        }
    }

    public static boolean isFileLoggingActivated() {
        return fileAppender != null;
    }

    public static void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public static LogLevel getLogLevel() {
        return logLevel;
    }

    public static boolean isLogEnabledForLevel(LogLevel level) {
        return !logLevel.equals(LogLevel.OFF) && logLevel.ordinal() <= level.ordinal();
    }

    /* renamed from: i */
    public static void m1917i(String string) {
        m1919i("JumioMobileSDK", string);
    }

    /* renamed from: i */
    public static void m1921i(String string, Throwable tr) {
        m1920i("JumioMobileSDK", string, tr);
    }

    /* renamed from: i */
    public static void m1919i(String tag, String string) {
        if (isLogEnabledForLevel(LogLevel.INFO)) {
            android.util.Log.i(tag, string);
        }
    }

    /* renamed from: i */
    public static void m1920i(String tag, String string, Throwable tr) {
        if (isLogEnabledForLevel(LogLevel.INFO)) {
            android.util.Log.i(tag, string, tr);
        }
    }

    /* renamed from: i */
    public static void m1918i(String string, File folder, String fileName) {
        m1919i("JumioMobileSDK", string);
        if (fileAppender != null && isLogEnabledForLevel(LogLevel.INFO)) {
            fileAppender.logString(string, folder, fileName);
        }
    }

    /* renamed from: e */
    public static void m1912e(String string) {
        m1914e("JumioMobileSDK", string);
    }

    /* renamed from: e */
    public static void m1916e(String string, Throwable tr) {
        m1915e("JumioMobileSDK", string, tr);
    }

    /* renamed from: e */
    public static void m1914e(String tag, String string) {
        if (isLogEnabledForLevel(LogLevel.ERROR)) {
            android.util.Log.e(tag, string);
        }
    }

    /* renamed from: e */
    public static void m1915e(String tag, String string, Throwable tr) {
        if (isLogEnabledForLevel(LogLevel.ERROR)) {
            android.util.Log.e(tag, string, tr);
        }
    }

    /* renamed from: e */
    public static void m1913e(String string, File folder, String fileName) {
        m1914e("JumioMobileSDK", string);
        if (fileAppender != null && isLogEnabledForLevel(LogLevel.ERROR)) {
            fileAppender.dumpStringToFile(string, folder, fileName);
        }
    }

    /* renamed from: d */
    public static void m1907d(String string) {
        m1909d("JumioMobileSDK", string);
    }

    /* renamed from: d */
    public static void m1911d(String string, Throwable tr) {
        m1910d("JumioMobileSDK", string, tr);
    }

    /* renamed from: d */
    public static void m1909d(String tag, String string) {
        if (isLogEnabledForLevel(LogLevel.DEBUG)) {
            android.util.Log.d(tag, string);
        }
    }

    /* renamed from: d */
    public static void m1910d(String tag, String string, Throwable tr) {
        if (isLogEnabledForLevel(LogLevel.DEBUG)) {
            android.util.Log.d(tag, string, tr);
        }
    }

    /* renamed from: d */
    public static void m1908d(String string, File folder, String fileName) {
        m1909d("JumioMobileSDK", string);
        if (fileAppender != null && isLogEnabledForLevel(LogLevel.DEBUG)) {
            fileAppender.dumpStringToFile(string, folder, fileName);
        }
    }

    /* renamed from: v */
    public static void m1922v(String string) {
        m1924v("JumioMobileSDK", string);
    }

    /* renamed from: v */
    public static void m1926v(String string, Throwable tr) {
        m1925v("JumioMobileSDK", string, tr);
    }

    /* renamed from: v */
    public static void m1924v(String tag, String string) {
        if (isLogEnabledForLevel(LogLevel.VERBOSE)) {
            android.util.Log.v(tag, string);
        }
    }

    /* renamed from: v */
    public static void m1925v(String tag, String string, Throwable tr) {
        if (isLogEnabledForLevel(LogLevel.VERBOSE)) {
            android.util.Log.v(tag, string, tr);
        }
    }

    /* renamed from: v */
    public static void m1923v(String string, File folder, String fileName) {
        m1924v("JumioMobileSDK", string);
        if (fileAppender != null && isLogEnabledForLevel(LogLevel.VERBOSE)) {
            fileAppender.dumpStringToFile(string, folder, fileName);
        }
    }

    /* renamed from: w */
    public static void m1927w(String string) {
        m1929w("JumioMobileSDK", string);
    }

    /* renamed from: w */
    public static void m1931w(String string, Throwable tr) {
        m1930w("JumioMobileSDK", string, tr);
    }

    /* renamed from: w */
    public static void m1929w(String tag, String string) {
        if (isLogEnabledForLevel(LogLevel.WARN)) {
            android.util.Log.w(tag, string);
        }
    }

    /* renamed from: w */
    public static void m1930w(String tag, String string, Throwable tr) {
        if (isLogEnabledForLevel(LogLevel.WARN)) {
            android.util.Log.w(tag, string, tr);
        }
    }

    /* renamed from: w */
    public static void m1928w(String string, File folder, String fileName) {
        m1929w("JumioMobileSDK", string);
        if (fileAppender != null && isLogEnabledForLevel(LogLevel.WARN)) {
            fileAppender.dumpStringToFile(string, folder, fileName);
        }
    }

    public static void printStackTrace(Throwable e) {
        if (!logLevel.equals(LogLevel.OFF)) {
            e.printStackTrace();
        }
    }

    public static void image(Bitmap bitmap, File folder, String fileName) {
        image(bitmap, folder, fileName, CompressFormat.JPEG, 80);
    }

    public static void image(Bitmap bitmap, File folder, String fileName, CompressFormat compressFormat, int quality) {
        if (logLevel.ordinal() <= LogLevel.DEBUG.ordinal() && fileAppender != null) {
            fileAppender.dumpBitmap(bitmap, folder, fileName, compressFormat, quality);
        }
    }

    public static void data(byte[] data, File folder, String fileName) {
        if (logLevel.ordinal() <= LogLevel.DEBUG.ordinal() && fileAppender != null) {
            fileAppender.dumpRawBuffer(data, folder, fileName, false);
        }
    }
}
