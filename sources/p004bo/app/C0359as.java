package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: bo.app.as */
public class C0359as implements UncaughtExceptionHandler {

    /* renamed from: a */
    private static final String f104a = AppboyLogger.getAppboyLogTag(C0359as.class);

    /* renamed from: b */
    private final C0343ac f105b;

    public C0359as(C0343ac acVar) {
        this.f105b = acVar;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        try {
            AppboyLogger.m1740w(f104a, "Uncaught exception from thread. Publishing as Throwable event", throwable);
            this.f105b.mo6736a(throwable, Throwable.class);
        } catch (Exception e) {
            AppboyLogger.m1740w(f104a, "Failed to log throwable.", e);
        }
    }
}
