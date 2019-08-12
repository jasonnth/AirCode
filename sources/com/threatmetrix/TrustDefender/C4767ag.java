package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.threatmetrix.TrustDefender.ag */
class C4767ag {

    /* renamed from: a */
    private static volatile WebView f4201a = null;

    /* renamed from: b */
    private static final Lock f4202b = new ReentrantLock();

    /* renamed from: c */
    private static Context f4203c = null;

    /* renamed from: d */
    private static final String f4204d = C4834w.m2892a(C4767ag.class);

    private C4767ag() {
    }

    /* renamed from: a */
    public static WebView m2615a(Context context) {
        if (!C4814o.m2821a()) {
            return null;
        }
        if (f4203c != null && f4203c != context) {
            C4834w.m2894a(f4204d, "Mismatched context: Only application context should be used, and it should always be consistent between calls");
            return null;
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            return null;
        } else {
            if (f4201a == null) {
                try {
                    f4202b.lock();
                    if (f4201a == null) {
                        f4201a = new WebView(context);
                        f4203c = context;
                    }
                } catch (Throwable throwable) {
                    if (throwable instanceof Exception) {
                        C4834w.m2896a(f4204d, "WebView not available: {}", throwable.getMessage());
                    } else {
                        C4834w.m2896a(f4204d, "WebView not available: {}", throwable.getMessage());
                    }
                } finally {
                    f4202b.unlock();
                }
            } else {
                C4834w.m2901c(f4204d, "Reusing webview");
            }
            return f4201a;
        }
    }

    /* renamed from: a */
    public static boolean m2616a() {
        try {
            f4202b.lock();
            return f4201a != null;
        } finally {
            f4202b.unlock();
        }
    }

    /* renamed from: b */
    public static void m2617b() {
        if (C4814o.m2821a()) {
            try {
                f4202b.lock();
                if (f4201a != null) {
                    final WebView tempWebView = f4201a;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            tempWebView.removeAllViews();
                            tempWebView.destroy();
                        }
                    });
                }
                f4201a = null;
            } finally {
                f4202b.unlock();
            }
        }
    }
}
