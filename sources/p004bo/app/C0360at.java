package p004bo.app;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: bo.app.at */
public final class C0360at extends ThreadPoolExecutor {

    /* renamed from: a */
    private static final TimeUnit f106a = TimeUnit.MILLISECONDS;

    public C0360at(ThreadFactory threadFactory) {
        super(1, 1, 0, f106a, new LinkedBlockingQueue(), threadFactory);
    }
}
