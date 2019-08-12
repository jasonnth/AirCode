package p004bo.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: bo.app.ar */
public class C0358ar implements ThreadFactory {

    /* renamed from: a */
    private final AtomicInteger f102a = new AtomicInteger(1);

    /* renamed from: b */
    private UncaughtExceptionHandler f103b;

    /* renamed from: a */
    public void mo6751a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f103b = uncaughtExceptionHandler;
    }

    public Thread newThread(Runnable runnable) {
        if (this.f103b == null) {
            throw new IllegalStateException("No UncaughtExceptionHandler. You must call setUncaughtExceptionHandler before creating a new thread");
        }
        Thread thread = new Thread(runnable, String.format("%s #%d", new Object[]{C0358ar.class.getSimpleName(), Integer.valueOf(this.f102a.getAndIncrement())}));
        thread.setUncaughtExceptionHandler(this.f103b);
        return thread;
    }
}
