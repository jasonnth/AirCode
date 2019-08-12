package com.mparticle;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.mparticle.a */
public class C4586a implements UncaughtExceptionHandler {

    /* renamed from: a */
    private UncaughtExceptionHandler f3687a = null;

    public C4586a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler != null && !(uncaughtExceptionHandler instanceof C4586a)) {
            this.f3687a = uncaughtExceptionHandler;
        }
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            MParticle.getInstance().logUnhandledError(ex);
            if (this.f3687a != null) {
                this.f3687a.uncaughtException(thread, ex);
            }
        } catch (Exception e) {
            Log.e("mParticle SDK", "Failed to log error event for uncaught exception", e);
        }
    }

    /* renamed from: a */
    public UncaughtExceptionHandler mo44543a() {
        return this.f3687a;
    }
}
