package com.airbnb.android.core;

import android.util.Log;
import com.airbnb.android.core.utils.BuildHelper;

/* renamed from: com.airbnb.android.core.L */
public final class C0715L {
    /* renamed from: d */
    public static void m1189d(String tag, String msg) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.d(tag, msg);
        }
        BugsnagWrapper.leaveBreadcrumb("(d) " + tag + ": " + msg);
    }

    /* renamed from: d */
    public static void m1190d(String tag, String msg, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.d(tag, msg, tr);
        }
        BugsnagWrapper.leaveBreadcrumb("(d) " + tag + ": " + msg + 10 + Log.getStackTraceString(tr));
    }

    /* renamed from: e */
    public static void m1191e(String tag, String msg) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.e(tag, msg);
        }
        BugsnagWrapper.leaveBreadcrumb("(E) " + tag + ": " + msg);
    }

    /* renamed from: e */
    public static void m1193e(String tag, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.e(tag, Log.getStackTraceString(tr));
        }
        BugsnagWrapper.leaveBreadcrumb("(E) " + tag + ": " + Log.getStackTraceString(tr));
    }

    /* renamed from: e */
    public static void m1192e(String tag, String msg, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.e(tag, msg, tr);
        }
        BugsnagWrapper.leaveBreadcrumb("(E) " + tag + ": " + msg + 10 + Log.getStackTraceString(tr));
    }

    /* renamed from: i */
    public static void m1194i(String tag, String msg) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.i(tag, msg);
        }
        BugsnagWrapper.leaveBreadcrumb("(I) " + tag + ": " + msg);
    }

    /* renamed from: i */
    public static void m1195i(String tag, String msg, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.i(tag, msg, tr);
        }
        BugsnagWrapper.leaveBreadcrumb("(I) " + tag + ": " + msg + 10 + Log.getStackTraceString(tr));
    }

    /* renamed from: v */
    public static void m1196v(String tag, String msg) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.v(tag, msg);
        }
        BugsnagWrapper.leaveBreadcrumb("(V) " + tag + ": " + msg);
    }

    /* renamed from: v */
    public static void m1197v(String tag, String msg, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.v(tag, msg, tr);
        }
        BugsnagWrapper.leaveBreadcrumb("(V) " + tag + ": " + msg + 10 + Log.getStackTraceString(tr));
    }

    /* renamed from: w */
    public static void m1198w(String tag, String msg) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.w(tag, msg);
        }
        BugsnagWrapper.leaveBreadcrumb("(W) " + tag + ": " + msg);
    }

    /* renamed from: w */
    public static void m1200w(String tag, Throwable tr) {
        String stackTrace = Log.getStackTraceString(tr);
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.w(tag, stackTrace);
        }
        BugsnagWrapper.leaveBreadcrumb("(W) " + tag + ": " + stackTrace);
    }

    /* renamed from: w */
    public static void m1199w(String tag, String msg, Throwable tr) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Log.w(tag, msg, tr);
        }
        BugsnagWrapper.leaveBreadcrumb("(W) " + tag + ": " + msg + 10 + Log.getStackTraceString(tr));
    }
}
